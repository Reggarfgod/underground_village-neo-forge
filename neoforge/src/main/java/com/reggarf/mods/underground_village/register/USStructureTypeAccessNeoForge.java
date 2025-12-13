package com.reggarf.mods.underground_village.register;

import net.minecraft.world.level.levelgen.structure.StructureType;

public class USStructureTypeAccessNeoForge implements USStructureTypeAccess {

    @Override
    public StructureType<?> underground() {
        return USStructuresNeoForge.UNDERGROUND_STRUCTURE.get();
    }

    @Override
    public StructureType<?> underwater() {
        return USStructuresNeoForge.UNDERWATER_STRUCTURE.get();
    }
}
