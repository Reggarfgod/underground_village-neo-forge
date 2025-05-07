package com.reggarf.mods.underground_village.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "underground_village")
@Config.Gui.Background("minecraft:textures/block/mossy_cobblestone.png")
public class USConfigs extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("common")
    @ConfigEntry.Gui.TransitiveObject()
    public Common common = new Common();

    @Config(name = "common")
    public static final class Common implements ConfigData {

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        @Comment("Default size of the underground structure (from 1 to 30)")
        public int structureSize = 19;

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 128)
        @Comment("Maximum distance the structure pieces can be from the center (1 to 128)")
        public int maxDistanceFromCenter = 116;
    }
}

