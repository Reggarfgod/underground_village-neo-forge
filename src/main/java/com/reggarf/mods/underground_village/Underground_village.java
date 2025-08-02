package com.reggarf.mods.underground_village;

import com.mojang.logging.LogUtils;
import com.reggarf.mods.underground_village.config.USConfigs;
import com.reggarf.mods.underground_village.register.USMessageHandler;
import com.reggarf.mods.underground_village.register.USStructurePlacements;
import com.reggarf.mods.underground_village.register.USStructures;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;


@Mod(Underground_village.MODID)
public class Underground_village {
    public static final String MODID = "underground_village";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static USConfigs CONFIG;
    public Underground_village(IEventBus modEventBus, ModContainer modContainer) {
        USStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
        USStructurePlacements.DEFERRED_REGISTRY_STRUCTURE_PLACEMENT_TYPE.register(modEventBus);

        /// /////////////////////////////////////////////////
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (container, parent) -> {
            return AutoConfig.getConfigScreen(USConfigs.class, parent).get();
        });

        AutoConfig.register(USConfigs.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        CONFIG = AutoConfig.getConfigHolder(USConfigs.class).getConfig();
        /// ///////////////////////////////////////////////////
        NeoForge.EVENT_BUS.register(USMessageHandler.class);
    }
}
