package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.containers.SmelterContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AlphaContainerRegistry {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, AlphaCraft.MOD_ID);

    public static void init() {
        CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<ContainerType<SmelterContainer>> SMELTER = CONTAINER_TYPES.register("smelter", () -> IForgeContainerType.create(SmelterContainer::new));
}
