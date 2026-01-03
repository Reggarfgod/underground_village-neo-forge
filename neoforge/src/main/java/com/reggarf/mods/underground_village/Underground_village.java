package com.reggarf.mods.underground_village;


import com.reggarf.mods.better_lib.config.core.BetterConfigScreenFactory;
import com.reggarf.mods.better_lib.gui.screen.BetterConfigScreenHandler;
import com.reggarf.mods.underground_village.config.USConfigs;
import com.reggarf.mods.underground_village.register.USRegistryAccess;
import com.reggarf.mods.underground_village.register.USStructureTypeAccessNeoForge;
import com.reggarf.mods.underground_village.register.USStructuresNeoForge;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;


@Mod(Underground_village_Common.MODID)
public class Underground_village {

    public Underground_village(IEventBus eventBus) {

        eventBus.addListener(Underground_village::onClientSetup);
        USStructuresNeoForge.STRUCTURES.register(eventBus);
        USRegistryAccess.STRUCTURES = new USStructureTypeAccessNeoForge();
        Underground_village_Common.LOG.info("Hello NeoForge world!");
        CommonClass.init();
    }
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                BetterConfigScreenHandler.register(Underground_village_Common.MODID, parent ->
                        BetterConfigScreenFactory.from(USConfigs.class, CommonClass.CONFIG, parent)
                );
            });
    }
}