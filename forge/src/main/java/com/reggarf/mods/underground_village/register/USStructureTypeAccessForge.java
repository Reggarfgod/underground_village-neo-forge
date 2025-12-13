package com.reggarf.mods.underground_village.register;


import net.minecraft.world.level.levelgen.structure.StructureType;

/**
 * Forge implementation of COMMON registry bridge
 */
public class USStructureTypeAccessForge implements USStructureTypeAccess {

    @Override
    public StructureType<?> underground() {
        return USStructuresForge.UNDERGROUND_STRUCTURE.get();
    }

    @Override
    public StructureType<?> underwater() {
        return USStructuresForge.UNDERWATER_STRUCTURE.get();
    }
}
