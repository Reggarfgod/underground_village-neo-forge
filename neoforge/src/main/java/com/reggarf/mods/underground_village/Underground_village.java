package com.reggarf.mods.underground_village;


import com.reggarf.mods.underground_village.config.USConfigs;
import com.reggarf.mods.underground_village.register.USRegistryAccess;
import com.reggarf.mods.underground_village.register.USStructureTypeAccessNeoForge;
import com.reggarf.mods.underground_village.register.USStructuresNeoForge;
import me.shedaniel.autoconfig.AutoConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(Underground_village_Common.MODID)
public class Underground_village {

    public Underground_village(IEventBus eventBus) {


        USStructuresNeoForge.STRUCTURES.register(eventBus);
        USRegistryAccess.STRUCTURES = new USStructureTypeAccessNeoForge();

        Underground_village_Common.LOG.info("Hello NeoForge world!");
        CommonClass.init();
        /// /////////////////////////////////////////////////
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (container, parent) -> {
            return AutoConfig.getConfigScreen(USConfigs.class, parent).get();
        });
        /// ///////////////////////////////////////////////////
    }
}