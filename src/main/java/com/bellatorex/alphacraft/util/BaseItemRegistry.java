package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.items.ItemBase;
import com.bellatorex.alphacraft.items.PoopItem;
import com.bellatorex.alphacraft.items.TrophyItem;
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
    public static final RegistryObject<Item> INFECTED_ENDER_PEARL = ITEMS.register("infected_ender_pearl", TrophyItem::new);
    public static final RegistryObject<Item> DARK_BRICK = ITEMS.register("dark_brick", ItemBase::new);
    public static final RegistryObject<Item> DARK_CLAY = ITEMS.register("dark_clay", ItemBase::new);
    public static final RegistryObject<Item> ULTRA_INGOT = ITEMS.register("ultra_ingot", ItemBase::new);
    public static final RegistryObject<Item> ENDERITE_DUST = ITEMS.register("enderite_dust", ItemBase::new);
    public static final RegistryObject<Item> ENDERITE_PLATE = ITEMS.register("enderite_plate", ItemBase::new);
    public static final RegistryObject<Item> ENDERITE_TRASH = ITEMS.register("enderite_trash", ItemBase::new);
    public static final RegistryObject<Item> ENDERITE_INGOT = ITEMS.register("enderite_ingot", ItemBase::new);
    public static final RegistryObject<PoopItem> POOP = ITEMS.register("poop", PoopItem::new);
}
