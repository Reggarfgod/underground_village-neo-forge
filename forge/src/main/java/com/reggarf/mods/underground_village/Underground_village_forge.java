package com.reggarf.mods.underground_village;

import com.reggarf.mods.better_lib.config.core.BetterConfigScreenFactory;
import com.reggarf.mods.better_lib.gui.screen.BetterConfigScreenHandler;
import com.reggarf.mods.underground_village.config.USConfigs;
import com.reggarf.mods.underground_village.register.StructurePlacements;
import com.reggarf.mods.underground_village.register.USRegistryAccess;
import com.reggarf.mods.underground_village.register.USStructureTypeAccessForge;
import com.reggarf.mods.underground_village.register.USStructuresForge;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Underground_village_Common.MODID)
public class Underground_village_forge {

    public Underground_village_forge(FMLJavaModLoadingContext context) {

        BusGroup busGroup = context.getModBusGroup();
        USStructuresForge.register(busGroup);
        StructurePlacements.register(busGroup);
        USRegistryAccess.STRUCTURES = new USStructureTypeAccessForge();
        CommonClass.init();
    }
    @Mod.EventBusSubscriber(modid = Underground_village_Common.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                BetterConfigScreenHandler.register(Underground_village_Common.MODID, parent ->
                        BetterConfigScreenFactory.from(USConfigs.class, CommonClass.CONFIG, parent)
                );
            });
        }
    }
}