package com.reggarf.mods.underground_village;

import com.reggarf.mods.better_lib.config.core.BetterConfigScreenFactory;
import com.reggarf.mods.better_lib.gui.screen.BetterConfigScreenHandler;
import com.reggarf.mods.underground_village.config.USConfigs;
import com.reggarf.mods.underground_village.register.USRegistryAccess;
import com.reggarf.mods.underground_village.register.USStructureTypeAccessForge;
import com.reggarf.mods.underground_village.register.USStructuresForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Underground_village_Common.MODID)
public class Underground_village_forge {

    public Underground_village_forge() {

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(Underground_village_forge::onClientSetup);
        USStructuresForge.register(modBus);
        USRegistryAccess.STRUCTURES = new USStructureTypeAccessForge();
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