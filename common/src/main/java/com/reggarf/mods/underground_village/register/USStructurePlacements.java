package com.reggarf.mods.underground_village.register;

import com.mojang.serialization.MapCodec;
import com.reggarf.mods.underground_village.structureplacement.DistanceBasedStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

public class USStructurePlacements {

    /**
     * Common instance reference.
     * Loader-specific code must register this.
     */
    public static final StructurePlacementType<DistanceBasedStructurePlacement>
            DISTANCE_BASED_STRUCTURE_PLACEMENT =
            explicitStructureTypeTyping(DistanceBasedStructurePlacement.CODEC);

    /**
     * Helper to avoid generic inference issues.
     */
    private static <T extends StructurePlacement>
    StructurePlacementType<T> explicitStructureTypeTyping(MapCodec<T> codec) {
        return () -> codec;
    }

    private USStructurePlacements() {}
}
