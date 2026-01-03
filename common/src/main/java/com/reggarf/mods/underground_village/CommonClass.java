package com.reggarf.mods.underground_village;

import com.reggarf.mods.better_lib.config.core.BetterConfigManager;
import com.reggarf.mods.underground_village.api.JoinPlugin;
import com.reggarf.mods.underground_village.config.USConfigs;


public class CommonClass {
    public static USConfigs CONFIG;
    public static void init() {
        JoinPlugin.register();
        CONFIG = BetterConfigManager.register(USConfigs.class);
    }
}