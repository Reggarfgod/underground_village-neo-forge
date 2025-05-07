package com.reggarf.mods.underground_village;

import com.mojang.logging.LogUtils;
import com.reggarf.mods.underground_village.config.USConfigs;
import com.reggarf.mods.underground_village.register.USStructurePlacements;
import com.reggarf.mods.underground_village.register.USStructures;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(Underground_village.MODID)
public class Underground_village {
    public static final String MODID = "underground_village";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static USConfigs CONFIG;
    public Underground_village() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        USStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
        USStructurePlacements.DEFERRED_REGISTRY_STRUCTURE_PLACEMENT_TYPE.register(modEventBus);
        init();
        registerConfig();
    }
    private void registerConfig() {
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (client, parent) -> AutoConfig.getConfigScreen(USConfigs.class, parent).get()
                )
        );
    }
    public static void init() {
        AutoConfig.register(USConfigs.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        CONFIG = AutoConfig.getConfigHolder(USConfigs.class).getConfig();

    }
}
