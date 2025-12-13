package com.reggarf.mods.underground_village.register;

import com.reggarf.mods.underground_village.Underground_village;
import com.reggarf.mods.underground_village.structure.UndergroundStructure;
import com.reggarf.mods.underground_village.structure.UnderwaterStructures;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureType;

import static com.reggarf.mods.underground_village.Underground_village_Common.MODID;

public final class USStructuresFabric {

    public static StructureType<UndergroundStructure> UNDERGROUND_STRUCTURE;
    public static StructureType<UnderwaterStructures> UNDERWATER_STRUCTURES;

    public static void register() {

        UNDERGROUND_STRUCTURE = Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                ResourceLocation.fromNamespaceAndPath(MODID, "underground_structures"),
                () -> UndergroundStructure.CODEC
        );

        UNDERWATER_STRUCTURES = Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                ResourceLocation.fromNamespaceAndPath(MODID, "underwater_structures"),
                () -> UnderwaterStructures.CODEC
        );
    }

    private USStructuresFabric() {}
}
