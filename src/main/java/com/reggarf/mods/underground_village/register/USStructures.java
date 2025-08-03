package com.reggarf.mods.underground_village.register;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.reggarf.mods.underground_village.structure.UndergroundStructure;
import com.reggarf.mods.underground_village.structure.UnderwaterStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.reggarf.mods.underground_village.Underground_village.MODID;

public class USStructures {

    /**
     * We are using the Deferred Registry system to register our structure as this is the preferred way on NeoForge.
     * This will handle registering the base structure for us at the correct time so we don't have to handle it ourselves.
     */
    public static final DeferredRegister<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(Registries.STRUCTURE_TYPE, MODID);

//  public static final RegistryObject<StructureType<?>, StructureType<UndergroundStructure>> UNDERGROUND_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("underground_structures", () -> explicitStructureTypeTyping(UndergroundStructure.CODEC));
    public static final RegistryObject<StructureType<UndergroundStructure>> UNDERGROUND_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("underground_structures", () -> explicitStructureTypeTyping(UndergroundStructure.CODEC));
    public static final RegistryObject<StructureType<UnderwaterStructures>> UNDERWATER_STRUCTURES = DEFERRED_REGISTRY_STRUCTURE.register("underwater_structures", () -> explicitStructureTypeTyping(UnderwaterStructures.CODEC));

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(Codec<T> structureCodec) {
        return () -> structureCodec;
    }
}
