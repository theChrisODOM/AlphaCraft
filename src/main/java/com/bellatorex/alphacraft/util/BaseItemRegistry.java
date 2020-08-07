package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BaseItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlphaCraft.MOD_ID);

    public static void init() {

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }

    //Items
    public static final RegistryObject<Item> BALL = ITEMS.register("ball", ItemBase::new);
    public static final RegistryObject<Item> ENDERITE_INGOT = ITEMS.register("enderite_ingot", ItemBase::new);
    public static final RegistryObject<Item> CASTLE_BRICK = ITEMS.register("castle_brick", ItemBase::new);
    public static final RegistryObject<Item> DARK_CLAY = ITEMS.register("dark_clay", ItemBase::new);
    public static final RegistryObject<Item> ENDERITE_DUST = ITEMS.register("enderite_dust", ItemBase::new);
    public static final RegistryObject<Item> POOP = ITEMS.register("poop", ItemBase::new);
}
