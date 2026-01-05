package com.reggarf.mods.underground_village.register;

import com.reggarf.mods.underground_village.Underground_village_Common;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;


/**
 * NeoForge loader-only structure placement registration.
 * Common code must NEVER reference DeferredRegister.
 */
public class StructurePlacements {

    public static final DeferredRegister<StructurePlacementType<?>> STRUCTURE_PLACEMENTS =
            DeferredRegister.create(
                    Registries.STRUCTURE_PLACEMENT,
                    Underground_village_Common.MODID
            );

    public static void register(BusGroup busGroup) {

        STRUCTURE_PLACEMENTS.register(
                "distance_based_structure_placement",
                () -> USStructurePlacements.DISTANCE_BASED_STRUCTURE_PLACEMENT
        );

        STRUCTURE_PLACEMENTS.register(busGroup);
    }
}
