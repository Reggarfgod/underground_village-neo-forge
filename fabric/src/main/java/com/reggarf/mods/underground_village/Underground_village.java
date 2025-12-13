package com.reggarf.mods.underground_village;

import com.reggarf.mods.underground_village.register.USRegistryAccess;
import com.reggarf.mods.underground_village.register.USStructureTypeAccessFabric;
import com.reggarf.mods.underground_village.register.USStructuresFabric;
import net.fabricmc.api.ModInitializer;

public class Underground_village implements ModInitializer {

    @Override
    public void onInitialize() {

        USStructuresFabric.register();
        USRegistryAccess.STRUCTURES = new USStructureTypeAccessFabric();
        CommonClass.init();
    }
}
