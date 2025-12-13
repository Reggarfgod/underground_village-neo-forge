package com.reggarf.mods.underground_village;

import com.reggarf.mods.underground_village.config.USConfigs;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

public class CommonClass {
    public static USConfigs CONFIG;
    public static void init() {
        //USStructures.register();
        /// /////////////////////////////////////////////////
        AutoConfig.register(USConfigs.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        CONFIG = AutoConfig.getConfigHolder(USConfigs.class).getConfig();
        /// ///////////////////////////////////////////////////

    }
}