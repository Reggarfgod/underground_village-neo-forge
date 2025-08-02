package com.reggarf.mods.underground_village.register;

import com.mojang.serialization.MapCodec;
import com.reggarf.mods.underground_village.structure.UnderwaterStructures;
import com.reggarf.mods.underground_village.structure.UndergroundStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.reggarf.mods.underground_village.Underground_village.MODID;

public class USStructures {

    /**
     * We are using the Deferred Registry system to register our structure as this is the preferred way on NeoForge.
     * This will handle registering the base structure for us at the correct time so we don't have to handle it ourselves.
     */
    public static final DeferredRegister<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(Registries.STRUCTURE_TYPE, MODID);

    /**
     * Registers the base structure itself and sets what its path is. In this case,
     * this base structure will have the resourcelocation of structure_tutorial:sky_structures.
     */
  public static final DeferredHolder<StructureType<?>, StructureType<UndergroundStructure>> UNDERGROUND_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("underground_structures", () -> explicitStructureTypeTyping(UndergroundStructure.CODEC));
    public static final DeferredHolder<StructureType<?>, StructureType<UnderwaterStructures>> UNDERWATER_STRUCTURES = DEFERRED_REGISTRY_STRUCTURE.register("underwater_structures", () -> explicitStructureTypeTyping(UnderwaterStructures.CODEC));
    /**
     * Originally, I had a double lambda ()->()-> for the RegistryObject line above, but it turns out that
     * some IDEs cannot resolve the typing correctly. This method explicitly states what the return type
     * is so that the IDE can put it into the DeferredRegistry properly.
     */
    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(MapCodec<T> structureCodec) {
        return () -> structureCodec;
    }
}
