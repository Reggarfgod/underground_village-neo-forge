package com.reggarf.mods.underground_village.structureplacement;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reggarf.mods.underground_village.register.USStructurePlacements;
import net.minecraft.core.Vec3i;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

import java.util.Optional;

public class DistanceBasedStructurePlacement extends RandomSpreadStructurePlacement {

    public static final Codec<DistanceBasedStructurePlacement> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Vec3i.offsetCodec(16).optionalFieldOf("locate_offset", Vec3i.ZERO).forGetter(DistanceBasedStructurePlacement::locateOffset),
            StructurePlacement.FrequencyReductionMethod.CODEC.optionalFieldOf("frequency_reduction_method", StructurePlacement.FrequencyReductionMethod.DEFAULT).forGetter(DistanceBasedStructurePlacement::frequencyReductionMethod),
            Codec.floatRange(0.0F, 1.0F).optionalFieldOf("frequency", 1.0F).forGetter(DistanceBasedStructurePlacement::frequency),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("salt").forGetter(DistanceBasedStructurePlacement::salt),
            StructurePlacement.ExclusionZone.CODEC.optionalFieldOf("exclusion_zone").forGetter(DistanceBasedStructurePlacement::exclusionZone),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("spacing").forGetter(DistanceBasedStructurePlacement::spacing),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("separation").forGetter(DistanceBasedStructurePlacement::separation),
            RandomSpreadType.CODEC.optionalFieldOf("spread_type", RandomSpreadType.LINEAR).forGetter(DistanceBasedStructurePlacement::spreadType),
            Codec.intRange(0, Integer.MAX_VALUE).optionalFieldOf("min_distance_from_world_origin").forGetter(DistanceBasedStructurePlacement::minDistanceFromWorldOrigin)
    ).apply(instance, instance.stable(DistanceBasedStructurePlacement::new)));

    private final Optional<Integer> minDistanceFromWorldOrigin;

    public DistanceBasedStructurePlacement(Vec3i locationOffset,
                                           StructurePlacement.FrequencyReductionMethod frequencyReductionMethod,
                                           float frequency,
                                           int salt,
                                           Optional<ExclusionZone> exclusionZone,
                                           int spacing,
                                           int separation,
                                           RandomSpreadType spreadType,
                                           Optional<Integer> minDistanceFromWorldOrigin
    ) {
        super(locationOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, spreadType);
        this.minDistanceFromWorldOrigin = minDistanceFromWorldOrigin;

        // Helpful validation to ensure that spacing value is always greater than separation value
        if (spacing <= separation) {
            throw new RuntimeException("""
                Spacing cannot be less or equal to separation.
                Please correct this error as there's no way to spawn this structure properly
                    Spacing: %s
                    Separation: %s.
            """.formatted(spacing, separation));
        }
    }

    public Optional<Integer> minDistanceFromWorldOrigin() {
        return this.minDistanceFromWorldOrigin;
    }

    @Override
    protected boolean isPlacementChunk(ChunkGeneratorStructureState chunkGeneratorStructureState, int x, int z) {
        if (minDistanceFromWorldOrigin.isPresent()) {
            // Convert chunk position to block position.
            long xBlockPos = x * 16L;
            long zBlockPos = z * 16L;

            // Simple fast distance check without needing to do a square root. The threshold is circular around world origin.
            if ((xBlockPos * xBlockPos) + (zBlockPos * zBlockPos) < (((long) minDistanceFromWorldOrigin.get()) * minDistanceFromWorldOrigin.get())) {
                return false;
            }
        }

        ChunkPos chunkpos = this.getPotentialStructureChunk(chunkGeneratorStructureState.getLevelSeed(), x, z);
        return chunkpos.x == x && chunkpos.z == z;
    }

    @Override
    public StructurePlacementType<?> type() {
        return USStructurePlacements.DISTANCE_BASED_STRUCTURE_PLACEMENT.get();
    }

}