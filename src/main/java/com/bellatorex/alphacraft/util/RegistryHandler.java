package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.blocks.*;
import com.bellatorex.alphacraft.items.ItemBase;
import com.bellatorex.alphacraft.tools.AlphaMaterials;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlphaCraft.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AlphaCraft.MOD_ID);



    public static void init() {

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    //Items
    public static final RegistryObject<Item> BALL = ITEMS.register("ball", ItemBase::new);
    public static final RegistryObject<Item> ENDERITE_INGOT = ITEMS.register("enderite_ingot", ItemBase::new);
    public static final RegistryObject<Item> CASTLE_BRICK = ITEMS.register("castle_brick", ItemBase::new);
    public static final RegistryObject<Item> DARK_CLAY = ITEMS.register("dark_clay", ItemBase::new);
    public static final RegistryObject<Item> ENDERITE_DUST = ITEMS.register("enderite_dust", ItemBase::new);
    //Tools
    public static final RegistryObject<SwordItem> ENDERITE_SWORD = ITEMS.register("enderite_sword", () ->
            new SwordItem(AlphaMaterials.ENDERITE, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<PickaxeItem> ENDERITE_PICKAXE = ITEMS.register("enderite_pickaxe", () ->
            new PickaxeItem(AlphaMaterials.ENDERITE, 1, -2.4f, new Item.Properties().group(ItemGroup.TOOLS)));
    public static final RegistryObject<AxeItem> ENDERITE_AXE = ITEMS.register("enderite_axe", () ->
            new AxeItem(AlphaMaterials.ENDERITE, 5, -3.0f, new Item.Properties().group(ItemGroup.TOOLS)));
    public static final RegistryObject<ShovelItem> ENDERITE_SHOVEL = ITEMS.register("enderite_shovel", () ->
            new ShovelItem(AlphaMaterials.ENDERITE, 0, -2.4f, new Item.Properties().group(ItemGroup.TOOLS)));
    public static final RegistryObject<HoeItem> ENDERITE_HOE = ITEMS.register("enderite_hoe", () ->
            new HoeItem(AlphaMaterials.ENDERITE, 0, -2.0f, new Item.Properties().group(ItemGroup.TOOLS)));
    //Blocks
    public static final RegistryObject<Block> CASTLE_WALL_BLOCK = BLOCKS.register("castle_wall_block", CastleWallBlock::new);
    public static final RegistryObject<Block> ENDERITE_BLOCK = BLOCKS.register("enderite_block", EnderiteBlock::new);
    public static final RegistryObject<Block> ENDERITE_ORE_BLOCK = BLOCKS.register("enderite_ore_block", EnderiteOreBlock::new);
    public static final RegistryObject<Block> POOP_BLOCK = BLOCKS.register("poop_block", PoopBlock::new);
    //Block Items
    public static final RegistryObject<Item> CASTLE_WALL_BLOCK_ITEM = ITEMS.register("castle_wall_block", () -> new BlockItemBase(CASTLE_WALL_BLOCK.get()));
    public static final RegistryObject<Item> ENDERITE_BLOCK_ITEM = ITEMS.register("enderite_block", () -> new BlockItemBase(ENDERITE_BLOCK.get()));
    public static final RegistryObject<Item> ENDERITE_ORE_BLOCK_ITEM = ITEMS.register("enderite_ore_block", () -> new BlockItemBase(ENDERITE_ORE_BLOCK.get()));
    public static final RegistryObject<Item> POOP_BLOCK_ITEM = ITEMS.register("poop_block_item", () -> new BlockItemBase(POOP_BLOCK.get()));

}
