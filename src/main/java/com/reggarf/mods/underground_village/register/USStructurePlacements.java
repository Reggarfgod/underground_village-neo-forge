package com.reggarf.mods.underground_village.register;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

import com.reggarf.mods.underground_village.structureplacement.DistanceBasedStructurePlacement;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


import static com.reggarf.mods.underground_village.Underground_village.MODID;

public class USStructurePlacements {

    public static final DeferredRegister<StructurePlacementType<?>> DEFERRED_REGISTRY_STRUCTURE_PLACEMENT_TYPE = DeferredRegister.create(Registries.STRUCTURE_PLACEMENT, MODID);

    //public static final DeferredHolder<StructurePlacementType<?>, StructurePlacementType<DistanceBasedStructurePlacement>> DISTANCE_BASED_STRUCTURE_PLACEMENT = DEFERRED_REGISTRY_STRUCTURE_PLACEMENT_TYPE.register("distance_based_structure_placement", () -> explicitStructureTypeTyping(DistanceBasedStructurePlacement.CODEC));
    public static final RegistryObject<StructurePlacementType<DistanceBasedStructurePlacement>> DISTANCE_BASED_STRUCTURE_PLACEMENT = DEFERRED_REGISTRY_STRUCTURE_PLACEMENT_TYPE.register("distance_based_structure_placement", () -> explicitStructureTypeTyping(DistanceBasedStructurePlacement.CODEC));

    private static <T extends StructurePlacement> StructurePlacementType<T> explicitStructureTypeTyping(Codec<T> structurePlacementTypeCodec) {
        return () -> structurePlacementTypeCodec;
    }
}
