package com.reggarf.mods.underground_village.register;

import com.mojang.serialization.MapCodec;
import com.reggarf.mods.underground_village.structure.UndergroundStructure;
import com.reggarf.mods.underground_village.structure.UnderwaterStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public final class USStructuresCommon {

    private static <T extends Structure> StructureType<T> create(MapCodec<T> codec) {
        return () -> codec;
    }

    public static StructureType<UndergroundStructure> underground() {
        return create(UndergroundStructure.CODEC);
    }

    public static StructureType<UnderwaterStructures> underwater() {
        return create(UnderwaterStructures.CODEC);
    }

    private USStructuresCommon() {}
}
