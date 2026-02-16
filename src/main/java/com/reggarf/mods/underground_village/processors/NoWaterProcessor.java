package com.reggarf.mods.underground_village.processors;

import com.mojang.serialization.Codec;
import com.reggarf.mods.underground_village.register.USProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import javax.annotation.Nullable;

public class NoWaterProcessor extends StructureProcessor {

    public static final Codec<NoWaterProcessor> CODEC =
            Codec.unit(NoWaterProcessor::new);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(
            LevelReader level,
            BlockPos pos,
            BlockPos pivot,
            StructureTemplate.StructureBlockInfo localInfo,
            StructureTemplate.StructureBlockInfo worldInfo,
            StructurePlaceSettings settings,
            @Nullable StructureTemplate template) {

        BlockPos worldPos = worldInfo.pos();
        BlockState worldState = worldInfo.state();
        ChunkAccess chunk = level.getChunk(worldPos);

        if (worldState.hasProperty(BlockStateProperties.WATERLOGGED)
                && !chunk.getFluidState(worldPos).isEmpty()) {

            boolean waterlog = localInfo.state().hasProperty(BlockStateProperties.WATERLOGGED)
                    && localInfo.state().getValue(BlockStateProperties.WATERLOGGED);

            BlockState newState = worldState
                    .rotate(settings.getRotation())
                    .setValue(BlockStateProperties.WATERLOGGED, waterlog);

            chunk.setBlockState(worldPos, newState, false);
        }

        return worldInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return USProcessors.NOWATER_PROCESSOR.get();
    }

}
