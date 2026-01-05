package com.reggarf.mods.underground_village.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reggarf.mods.underground_village.CommonClass;
import com.reggarf.mods.underground_village.register.USRegistryAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Optional;

public class UndergroundStructure extends Structure {

    public static final MapCodec<UndergroundStructure> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(
                            UndergroundStructure.settingsCodec(instance),
                            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(s -> s.startPool),
                            Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(s -> s.startJigsawName),

                            // Accepted from JSON but IGNORED (config used instead)
                            Codec.intRange(0, 30).fieldOf("size").forGetter(s -> 0),

                            HeightProvider.CODEC.fieldOf("start_height").forGetter(s -> s.startHeight),
                            Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(s -> s.projectStartToHeightmap),

                            // Accepted from JSON but IGNORED (config used instead)
                            Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(s -> 0),

                            DimensionPadding.CODEC.optionalFieldOf(
                                    "dimension_padding",
                                    JigsawStructure.DEFAULT_DIMENSION_PADDING
                            ).forGetter(s -> s.dimensionPadding),

                            LiquidSettings.CODEC.optionalFieldOf(
                                    "liquid_settings",
                                    JigsawStructure.DEFAULT_LIQUID_SETTINGS
                            ).forGetter(s -> s.liquidSettings)
                    ).apply(instance, UndergroundStructure::new)
            );

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<Identifier> startJigsawName;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final DimensionPadding dimensionPadding;
    private final LiquidSettings liquidSettings;

    public UndergroundStructure(
            StructureSettings settings,
            Holder<StructureTemplatePool> startPool,
            Optional<Identifier> startJigsawName,
            int ignoredSize,
            HeightProvider startHeight,
            Optional<Heightmap.Types> projectStartToHeightmap,
            int ignoredMaxDistance,
            DimensionPadding dimensionPadding,
            LiquidSettings liquidSettings
    ) {
        super(settings);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.dimensionPadding = dimensionPadding;
        this.liquidSettings = liquidSettings;
    }

    private static boolean extraSpawningChecks(GenerationContext context) {
        ChunkPos chunkpos = context.chunkPos();

        return context.chunkGenerator().getFirstOccupiedHeight(
                chunkpos.getMinBlockX(),
                chunkpos.getMinBlockZ(),
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                context.heightAccessor(),
                context.randomState()
        ) > context.chunkGenerator().getMinY();
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {

        if (!extraSpawningChecks(context)) {
            return Optional.empty();
        }

        int startY = startHeight.sample(
                context.random(),
                new WorldGenerationContext(
                        context.chunkGenerator(),
                        context.heightAccessor()
                )
        );

        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(
                chunkPos.getMinBlockX(),
                startY,
                chunkPos.getMinBlockZ()
        );

        //CONFIG VALUES USED HERE
        int actualSize = CommonClass.CONFIG.structureSize;
        int actualMaxDistance = CommonClass.CONFIG.maxDistanceFromCenter;

        return JigsawPlacement.addPieces(
                context,
                startPool,
                startJigsawName,
                actualSize,
                blockPos,
                false,
                projectStartToHeightmap,
                new JigsawStructure.MaxDistance(actualMaxDistance),
                PoolAliasLookup.EMPTY,
                dimensionPadding,
                liquidSettings
        );
    }

    @Override
    public StructureType<?> type() {
        return USRegistryAccess.STRUCTURES.underground();
    }
}
