package com.reggarf.mods.underground_village;

import com.mojang.logging.LogUtils;
import com.reggarf.mods.underground_village.config.USConfigs;
import com.reggarf.mods.underground_village.register.USStructurePlacements;
import com.reggarf.mods.underground_village.register.USStructures;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;



public class Underground_village implements ModInitializer {
    public static final String MODID = "underground_village";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static USConfigs CONFIG;
    @Override
    public void onInitialize() {
        USStructures.registerStructureTypes();
        USStructurePlacements.registerStructurePlacementTypes();
        init();
    }
    public static void init() {
        AutoConfig.register(USConfigs.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        CONFIG = AutoConfig.getConfigHolder(USConfigs.class).getConfig();

    }
}
