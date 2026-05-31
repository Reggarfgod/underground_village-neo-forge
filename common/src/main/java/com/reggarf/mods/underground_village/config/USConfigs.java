package com.reggarf.mods.underground_village.config;

import com.reggarf.mods.underground_village.Underground_village_Common;
import com.reggarf.mods.better_lib.config.annotation.Config;
import com.reggarf.mods.better_lib.config.annotation.ConfigEntry.*;
import com.reggarf.mods.better_lib.config.api.ConfigData;

@Config(modid = Underground_village_Common.MODID,name = "common")
public class USConfigs implements ConfigData {

    @Category("Generation")
    @Description("Default size of underground village structures.")
    @BoundedDiscrete(min = 1, max = 30)
    public int structureSize = 10;

    @Category("Generation")
    @Description("Maximum distance structure pieces can generate from the village center.")
    @BoundedDiscrete(min = 1, max = 128)
    public int maxDistanceFromCenter = 116;

    @Category("Water Structures")
    @Description("Default size of water-based underground village structures.")
    @BoundedDiscrete(min = 1, max = 30)
    public int waterStructureSize = 10;

    @Category("Water Structures")
    @Description("Maximum distance water structure pieces can generate from the center.")
    @BoundedDiscrete(min = 1, max = 128)
    public int waterMaxDistanceFromCenter = 116;

    @Category("Messages")
    @Description("Show the welcome message with Discord, GitHub, and support links on first join. (Requires restart)")
    public boolean enableInGameMessage = true;

}
