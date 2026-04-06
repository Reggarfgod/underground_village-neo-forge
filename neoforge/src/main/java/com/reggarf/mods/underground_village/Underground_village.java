package com.reggarf.mods.underground_village;


import com.reggarf.mods.better_lib.config.core.BetterConfigScreenFactory;
import com.reggarf.mods.better_lib.gui.screen.BetterConfigScreenHandler;
import com.reggarf.mods.underground_village.config.USConfigs;
import com.reggarf.mods.underground_village.register.StructurePlacements;
import com.reggarf.mods.underground_village.register.USRegistryAccess;
import com.reggarf.mods.underground_village.register.USStructureTypeAccessNeoForge;
import com.reggarf.mods.underground_village.register.USStructuresNeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;


@Mod(Constants.MODID)
public class Underground_village {

    public Underground_village(IEventBus eventBus) {

        eventBus.addListener(Underground_village::onClientSetup);
        USStructuresNeoForge.STRUCTURES.register(eventBus);
        StructurePlacements.register(eventBus);
        USRegistryAccess.STRUCTURES = new USStructureTypeAccessNeoForge();
        Constants.LOG.info("Hello NeoForge world!");
        CommonClass.init();
    }
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                BetterConfigScreenHandler.register(Constants.MODID, parent ->
                        BetterConfigScreenFactory.from(USConfigs.class, CommonClass.CONFIG, parent)
                );
            });
    }
}