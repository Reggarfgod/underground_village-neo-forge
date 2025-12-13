package com.reggarf.mods.underground_village;

import com.reggarf.mods.underground_village.config.USConfigs;
import com.reggarf.mods.underground_village.register.USRegistryAccess;
import com.reggarf.mods.underground_village.register.USStructureTypeAccessForge;
import com.reggarf.mods.underground_village.register.USStructuresForge;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Underground_village_Common.MODID)
public class Underground_village_forge {

    public Underground_village_forge() {

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        USStructuresForge.register(modBus);
        USRegistryAccess.STRUCTURES = new USStructureTypeAccessForge();
        /// /////////////////////////////////////////////////
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (client, parent) -> AutoConfig.getConfigScreen(USConfigs.class, parent).get()
                )
        );
        /// ///////////////////////////////////////////////////
        CommonClass.init();
    }
}