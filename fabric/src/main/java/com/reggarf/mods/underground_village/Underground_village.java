package com.reggarf.mods.underground_village;

import com.reggarf.mods.underground_village.register.USRegistryAccess;
import com.reggarf.mods.underground_village.register.USStructurePlacements;
import com.reggarf.mods.underground_village.register.USStructureTypeAccessFabric;
import com.reggarf.mods.underground_village.register.USStructuresFabric;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class Underground_village implements ModInitializer {

    @Override
    public void onInitialize() {

        USStructuresFabric.register();
        USRegistryAccess.STRUCTURES = new USStructureTypeAccessFabric();
        CommonClass.init();

        Registry.register(
                BuiltInRegistries.STRUCTURE_PLACEMENT,
                Identifier.fromNamespaceAndPath(
                        Constants.MODID,
                        "distance_based_structure_placement"
                ),
                USStructurePlacements.DISTANCE_BASED_STRUCTURE_PLACEMENT
        );
    }

}
