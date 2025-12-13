package com.reggarf.mods.underground_village.util;

import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;

import static net.minecraft.network.chat.TextColor.fromRgb;

public class USMessageUtil {

    public static String titleColor = "FEF250";
    public static String zapColor = "00FFFF";
    public static String discordColor = "5599FF";
    public static String hostingColor = "00FFAA";
    public static String disableColor = "00FF66";
    public static String githubColor = "A9A9A9";

    public static void sendStyledMessages(ServerPlayer player) {
        // Title message
        Component title = Component.literal("Hello! Join our Discord for the full changelog, or check it out on GitHub. And Thank you for downloading! ")
                .append(Component.literal("Underground Village,StoneHolm")
                        .setStyle(Style.EMPTY.withColor(parseTextColor(titleColor))));

        Component blankLine = Component.literal("");

        // Discord CTA
        Component discord = Component.literal(" - ")
                .append(Component.literal("Changelog/Discord ")
                        .setStyle(Style.EMPTY
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/JBFNW3wdTm"))
                                .withColor(parseTextColor(discordColor))
                                .withUnderlined(true)))
                .append(Component.literal(" (support, updates)"));

        // ZAP Hosting CTA
        Component zap = Component.literal(" - ")
                .append(Component.literal("ZAP-Hosting ")
                        .setStyle(Style.EMPTY
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://zap-hosting.com/reggarf"))
                                .withColor(parseTextColor(zapColor))
                                .withUnderlined(true)))
                .append(Component.literal(" (20% off with code Reggarf-1047)"));

        // Disable message CTA
        Component disable = Component.literal(" - ")
                .append(Component.literal("Disable this message")
                        .setStyle(Style.EMPTY
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/underground-villages-stoneholm"))
                                .withColor(parseTextColor(disableColor))
                                .withUnderlined(true)))
                .append(Component.literal(" (Mod config)"));

        // Issue tracker CTA
        Component issueTracker = Component.literal(" - ")
                .append(Component.literal("Issue Tracker")
                        .setStyle(Style.EMPTY
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Reggarfgod/underground_village-neo-forge/issues"))
                                .withColor(parseTextColor(githubColor))
                                .withUnderlined(true)))
                .append(Component.literal(" (github/wiki)"));

        // Send the messages
        player.sendSystemMessage(title);
        player.sendSystemMessage(blankLine);
        player.sendSystemMessage(discord);
        player.sendSystemMessage(zap);
        player.sendSystemMessage(issueTracker);
        player.sendSystemMessage(disable);
    }

    public static TextColor parseTextColor(String hex) {
        try {
            if (hex.startsWith("#")) hex = hex.substring(1);
            int rgb = Integer.parseInt(hex, 16);
            return fromRgb(rgb);
        } catch (NumberFormatException e) {
            System.err.println("Invalid color format: " + hex);
            return fromRgb(0xFFFFFF);
        }
    }
}
