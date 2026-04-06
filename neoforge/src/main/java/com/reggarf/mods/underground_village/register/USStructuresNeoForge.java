package com.reggarf.mods.underground_village.register;


import com.reggarf.mods.underground_village.structure.UndergroundStructure;
import com.reggarf.mods.underground_village.structure.UnderwaterStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.reggarf.mods.underground_village.Constants.MODID;


public final class USStructuresNeoForge {

    public static final DeferredRegister<StructureType<?>> STRUCTURES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, MODID);

    public static final DeferredHolder<StructureType<?>, StructureType<UndergroundStructure>>
            UNDERGROUND_STRUCTURE =
            STRUCTURES.register(
                    "underground_structures",
                    () -> USStructuresCommon.underground()
            );

    public static final DeferredHolder<StructureType<?>, StructureType<UnderwaterStructures>>
            UNDERWATER_STRUCTURE =
            STRUCTURES.register(
                    "underwater_structures",
                    () -> USStructuresCommon.underwater()
            );

    private USStructuresNeoForge() {}
}
