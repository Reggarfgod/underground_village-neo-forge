package com.reggarf.mods.underground_village.api;


import com.reggarf.mods.better_lib.message.api.JoinMessagePlugin;
import com.reggarf.mods.better_lib.message.api.JoinMessagePlugins;
import com.reggarf.mods.better_lib.message.api.JoinMessageSet;
import com.reggarf.mods.underground_village.CommonClass;
import com.reggarf.mods.underground_village.Underground_village_Common;



import java.util.List;


public class JoinPlugin implements JoinMessagePlugin {
    @Override
    public String getModId() {
        return Underground_village_Common.MODID;
    }

    @Override
    public boolean enabled() {
        return CommonClass.CONFIG.enableInGameMessage;
    }

    @Override
    public List<JoinMessageSet> getMessageSets() {
        return List.of(
                new JoinMessageSet()
                        .addBlankLine()
                        .addText(
                                "Underground Villages! adds naturally generated villages deep below the surface.",
                                0xFFFFFF
                        )
                        .addBlankLine()
                        .addText(
                                "Need help, found a bug, or missing a recipe or structure? Use the links below to get support and stay updated:",
                                0xCCCCCC
                        )
                        .addLink(
                                "(Discord Support & Updates)",
                                "https://discord.gg/kb6BntpcYq",
                                0x7289DA,
                                "Get help, report issues, and see upcoming features"
                        )
                        .addLink(
                                "(GitHub Issue Tracker)",
                                "https://github.com/Reggarfgod/Underground-Village/issues",
                                0xA9A9A9,
                                "Report bugs, crashes, or missing content"
                        )
                        .addBlankLine()
                        .addText(
                                "Want to support development and keep the mod updated?",
                                0xFFFFFF
                        )
                        .addLink(
                                "(ZAP-Hosting – Support Reggarf)",
                                "https://zap-hosting.com/reggarf",
                                0x00FFFF,
                                "20% off with code Reggarf-1047"
                        )
                        .addBlankLine()
                        .addLink(
                                "(Config Settings)",
                                "https://www.curseforge.com/minecraft/mc-mods/underground-villages-stoneholm",
                                0xF7C742,
                                "This message appears once and can be disabled in the config"
                        )
        );
    }


    public static void register() {
        JoinMessagePlugins.register(new JoinPlugin());
    }
}