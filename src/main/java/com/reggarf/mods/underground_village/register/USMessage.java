package com.reggarf.mods.underground_village.register;

import com.reggarf.mods.underground_village.Underground_village;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.network.chat.TextColor.fromRgb;

@Mod.EventBusSubscriber
public class USMessage {

    //public static boolean enabled = true;
    public static String titleColor = "DDA0FF";
    public static String zapColor = "00FFFF";
    public static String discordColor = "5599FF";
    public static String hostingColor = "00FFAA";
    public static String disableColor = "00FF66";
    public static String githubColor = "A9A9A9";
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !Underground_village.CONFIG.common.ENABLE_IN_GAME_MESSAGE) return;

        CompoundTag persistentData = player.getPersistentData();
        CompoundTag igData = persistentData.getCompound(ServerPlayer.PERSISTED_NBT_TAG);

        // Check if player has already joined before
        if (!igData.getBoolean("create_hard_mod_hasJoinedBefore")) {
            sendStyledMessages(player); // Send welcome + links
            igData.putBoolean("create_hard_mod_hasJoinedBefore", true);
            persistentData.put(ServerPlayer.PERSISTED_NBT_TAG, igData);
        }
    }

    private static void sendStyledMessages(ServerPlayer player) {
        // Title message
        Component title = Component.literal("Hello! Join our Discord for the full changelog, or check it out on GitHub. And Thank you for downloading! ")
                .append(Component.literal("Underground Villages, Stoneholm")
                        .setStyle(Style.EMPTY.withColor(parseTextColor(titleColor))));

        // Blank line for spacing
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

        // Disable Issue CTA
        Component issueTracker = Component.literal(" - ")
                .append(Component.literal("Issue Tracker")
                        .setStyle(Style.EMPTY
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Reggarfgod/underground_village-neo-forge/issues"))
                                .withColor(parseTextColor(githubColor))
                                .withUnderlined(true)))
                .append(Component.literal(" (github/wiki)"));

        // Send the messages to the player
        player.sendSystemMessage(title);
        player.sendSystemMessage(blankLine);
        player.sendSystemMessage(discord);
        player.sendSystemMessage(zap);
        player.sendSystemMessage(issueTracker);
        player.sendSystemMessage(disable);

    }

    // ===================== Hex Color Parser =====================
    private static TextColor parseTextColor(String hex) {
        try {
            if (hex.startsWith("#")) hex = hex.substring(1);
            int rgb = Integer.parseInt(hex, 16);
            return fromRgb(rgb);
        } catch (NumberFormatException e) {
            System.err.println("Invalid color format: " + hex);
            return fromRgb(0xFFFFFF); // Default to white
        }
    }
}
