package com.reggarf.mods.underground_village.register;

import com.mojang.serialization.MapCodec;
import com.reggarf.mods.underground_village.structure.UndergroundStructure;
import com.reggarf.mods.underground_village.structure.UnderwaterStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.reggarf.mods.underground_village.Underground_village_Common.MODID;

public final class USStructuresForge {

    private static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, MODID);

    public static final RegistryObject<StructureType<UndergroundStructure>> UNDERGROUND_STRUCTURE =
            STRUCTURE_TYPES.register(
                    "underground_structures",
                    () -> wrap(UndergroundStructure.CODEC)
            );

    public static final RegistryObject<StructureType<UnderwaterStructures>> UNDERWATER_STRUCTURE =
            STRUCTURE_TYPES.register(
                    "underwater_structures",
                    () -> wrap(UnderwaterStructures.CODEC)
            );

    private static <T extends Structure> StructureType<T> wrap(MapCodec<T> codec) {
        return () -> codec;
    }

    public static void register(BusGroup busGroup) {
        STRUCTURE_TYPES.register(busGroup);
    }

    private USStructuresForge() {}
}
