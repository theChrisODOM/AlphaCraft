package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.tools.AlphaMaterials;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ToolRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlphaCraft.MOD_ID);

    public static void init() {

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //Tools
    public static final RegistryObject<SwordItem> ENDERITE_SWORD = ITEMS.register("enderite_sword", () ->
            new SwordItem(AlphaMaterials.ENDERITE, 5, -2.4f, new Item.Properties().group(AlphaCraft.ALPHA_TOOLS)));
    public static final RegistryObject<PickaxeItem> ENDERITE_PICKAXE = ITEMS.register("enderite_pickaxe", () ->
            new PickaxeItem(AlphaMaterials.ENDERITE, 2, -2.4f, new Item.Properties().group(AlphaCraft.ALPHA_TOOLS)));
    public static final RegistryObject<AxeItem> ENDERITE_AXE = ITEMS.register("enderite_axe", () ->
            new AxeItem(AlphaMaterials.ENDERITE, 6, -3.0f, new Item.Properties().group(AlphaCraft.ALPHA_TOOLS)));
    public static final RegistryObject<ShovelItem> ENDERITE_SHOVEL = ITEMS.register("enderite_shovel", () ->
            new ShovelItem(AlphaMaterials.ENDERITE, 1, -2.4f, new Item.Properties().group(AlphaCraft.ALPHA_TOOLS)));
    public static final RegistryObject<HoeItem> ENDERITE_HOE = ITEMS.register("enderite_hoe", () ->
            new HoeItem(AlphaMaterials.ENDERITE, 1, -2.0f, new Item.Properties().group(AlphaCraft.ALPHA_TOOLS)));
}
