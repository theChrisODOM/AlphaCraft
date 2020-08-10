package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlphaCraft.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AlphaCraft.MOD_ID);

    public static void init() {

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //Blocks
    public static final RegistryObject<Block> DARK_STONE = BLOCKS.register("dark_stone", DarkStoneBlock::new);
    public static final RegistryObject<Block> DARK_DIRT_BLOCK = BLOCKS.register("dark_dirt_block", DarkEarthBlock::new);
    public static final RegistryObject<Block> DARK_GRASS_BLOCK = BLOCKS.register("dark_grass_block", DarkGrassBlock::new);
    public static final RegistryObject<Block> NASTY_STEM = BLOCKS.register("nasty_stem", DarkStemBlock::new);
    public static final RegistryObject<Block> NASTY_PLANKS = BLOCKS.register("nasty_planks", DarkStemBlock::new);
    public static final RegistryObject<Block> DARK_COBBLESTONE = BLOCKS.register("dark_cobblestone", DarkStoneBlock::new);
    public static final RegistryObject<Block> ENDERITE_ORE_BLOCK = BLOCKS.register("enderite_ore_block", EnderiteOreBlock::new);
    public static final RegistryObject<Block> DARK_CLAY_BLOCK = BLOCKS.register("dark_clay_block", DarkEarthBlock::new);
    public static final RegistryObject<Block> POOP_BLOCK = BLOCKS.register("poop_block", PoopBlock::new);
    public static final RegistryObject<Block> DARK_BRICKS = BLOCKS.register("dark_bricks", DarkBricks::new);
    public static final RegistryObject<Block> ENDERITE_BLOCK = BLOCKS.register("enderite_block", EnderiteBlock::new);
    public static final RegistryObject<Block> ULTRA_ORE = BLOCKS.register("ultra_ore", UltraOre::new);

    //Block Items
    public static final RegistryObject<Item> DARK_STONE_ITEM = ITEMS.register("dark_stone", () -> new BlockItemBase(DARK_STONE.get()));

    public static final RegistryObject<Item> DARK_DIRT_BLOCK_ITEM = ITEMS.register("dark_dirt_block", () -> new BlockItemBase(DARK_DIRT_BLOCK.get()));
    public static final RegistryObject<Item> DARK_GRASS_BLOCK_ITEM = ITEMS.register("dark_grass_block", () -> new BlockItemBase(DARK_GRASS_BLOCK.get()));

    public static final RegistryObject<Item> NASTY_STEM_ITEM = ITEMS.register("nasty_stem", () -> new BlockItemBase(NASTY_STEM.get()));
    public static final RegistryObject<Item> NASTY_PLANKS_ITEM = ITEMS.register("nasty_planks", () -> new BlockItemBase(NASTY_PLANKS.get()));

    public static final RegistryObject<Item> DARK_COBBLESTONE_ITEM = ITEMS.register("dark_cobblestone", () -> new BlockItemBase(DARK_COBBLESTONE.get()));

    public static final RegistryObject<Item> ENDERITE_ORE_BLOCK_ITEM = ITEMS.register("enderite_ore_block", () -> new BlockItemBase(ENDERITE_ORE_BLOCK.get()));
    public static final RegistryObject<Item> DARK_CLAY_BLOCK_ITEM = ITEMS.register("dark_clay_block", () -> new BlockItemBase(DARK_CLAY_BLOCK.get()));

    public static final RegistryObject<Item> POOP_BLOCK_ITEM = ITEMS.register("poop_block", () -> new BlockItemBase(POOP_BLOCK.get()));

    public static final RegistryObject<Item> DARK_BRICKS_ITEM = ITEMS.register("dark_bricks", () -> new BlockItemBase(DARK_BRICKS.get()));
    public static final RegistryObject<Item> ENDERITE_BLOCK_ITEM = ITEMS.register("enderite_block", () -> new BlockItemBase(ENDERITE_BLOCK.get()));
    public static final RegistryObject<Item> ULTRA_ORE_ITEM = ITEMS.register("ultra_ore", () -> new BlockItemBase(ULTRA_ORE.get()));

}
