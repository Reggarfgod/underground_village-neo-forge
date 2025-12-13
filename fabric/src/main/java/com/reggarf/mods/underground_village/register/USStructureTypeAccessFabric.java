package com.reggarf.mods.underground_village.register;


import net.minecraft.world.level.levelgen.structure.StructureType;

public class USStructureTypeAccessFabric implements USStructureTypeAccess {

    @Override
    public StructureType<?> underground() {
        return USStructuresFabric.UNDERGROUND_STRUCTURE;
    }

    @Override
    public StructureType<?> underwater() {
        return USStructuresFabric.UNDERWATER_STRUCTURES;
    }
}
