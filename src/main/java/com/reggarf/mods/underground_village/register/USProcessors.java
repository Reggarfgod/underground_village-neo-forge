package com.reggarf.mods.underground_village.register;

import com.reggarf.mods.underground_village.Underground_village;
import com.reggarf.mods.underground_village.processors.NoWaterProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class USProcessors {

    public static final DeferredRegister<StructureProcessorType<?>> PROCESSORS =
            DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, Underground_village.MODID);

    public static final RegistryObject<StructureProcessorType<NoWaterProcessor>> NOWATER_PROCESSOR =
            PROCESSORS.register("nowater_processor",
                    () -> () -> NoWaterProcessor.CODEC);
}
