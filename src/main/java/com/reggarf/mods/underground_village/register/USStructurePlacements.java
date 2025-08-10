package com.reggarf.mods.underground_village.register;

import com.reggarf.mods.underground_village.Underground_village;
import com.reggarf.mods.underground_village.structureplacement.DistanceBasedStructurePlacement;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

public class USStructurePlacements {

    public static StructurePlacementType<DistanceBasedStructurePlacement> DISTANCE_BASED_STRUCTURE_PLACEMENT;


    public static void registerStructurePlacementTypes() {
        DISTANCE_BASED_STRUCTURE_PLACEMENT  = Registry.register(net.minecraft.registry.Registries.STRUCTURE_PLACEMENT, Identifier.of(Underground_village.MODID, "distance_based_structure_placement"), () -> DistanceBasedStructurePlacement.CODEC);
    }
}
