package com.reggarf.mods.underground_village.register;

import com.reggarf.mods.underground_village.Underground_village;
import com.reggarf.mods.underground_village.structure.UndergroundStructures;
import com.reggarf.mods.underground_village.structure.UnderwaterStructures;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;

public class USStructures {


    public static StructureType<UndergroundStructures> UNDERGROUND_STRUCTURE;
    public static StructureType<UnderwaterStructures> UNDERWATER_STRUCTURES ;

    /**
     * Registers the structure itself and sets what its path is. In this case, the
     * structure will have the Identifier of structure_tutorial:sky_structures.
     *
     * It is always a good idea to register your Structures so that other mods and datapacks can
     * use them too directly from the registries. It's great for mod/datapacks compatibility.
     */
    public static void registerStructureTypes() {
        UNDERGROUND_STRUCTURE = Registry.register(net.minecraft.registry.Registries.STRUCTURE_TYPE, Identifier.of(Underground_village.MODID, "underground_structures"), () -> UndergroundStructures.CODEC);
        UNDERWATER_STRUCTURES  = Registry.register(net.minecraft.registry.Registries.STRUCTURE_TYPE, Identifier.of(Underground_village.MODID, "underwater_structures"), () -> UnderwaterStructures.CODEC);
    }

}
