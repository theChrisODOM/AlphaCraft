package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.blocks.*;
import com.bellatorex.alphacraft.world.gen.feature.DarkAshTree;
import com.bellatorex.alphacraft.world.gen.feature.NastyPoopTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
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

    // Blocks
    public static final RegistryObject<Block> DARK_STONE = BLOCKS.register("dark_stone", DarkStoneBlock::new);
    public static final RegistryObject<Block> DARK_DIRT_BLOCK = BLOCKS.register("dark_dirt_block", DarkEarthBlock::new);
    public static final RegistryObject<Block> DARK_GRASS_BLOCK = BLOCKS.register("dark_grass_block", DarkGrassBlock::new);
    public static final RegistryObject<Block> NASTY_STEM = BLOCKS.register("nasty_stem", DarkStemBlock::new);
    public static final RegistryObject<Block> NASTY_PLANKS = BLOCKS.register("nasty_planks", DarkPlankBlock::new);
    public static final RegistryObject<Block> NASTY_LEAVES = BLOCKS.register("nasty_leaves", DarkLeavesBlock::new);
    public static final RegistryObject<Block> DARK_LOG = BLOCKS.register("dark_log", DarkStemBlock::new);
    public static final RegistryObject<Block> DARK_PLANKS = BLOCKS.register("dark_planks", DarkPlankBlock::new);
    public static final RegistryObject<Block> DARK_PLANKS_STAIRS = BLOCKS.register("dark_planks_stairs", () -> new StairsBlock(() -> DARK_PLANKS.get().getDefaultState(), Block.Properties.from(DARK_PLANKS.get())));
    public static final RegistryObject<Block> DARK_PLANKS_FENCE = BLOCKS.register("dark_planks_fence", () -> new FenceBlock(Block.Properties.from(DARK_PLANKS.get())));
    public static final RegistryObject<Block> DARK_PLANKS_SLAB = BLOCKS.register("dark_planks_slab", () -> new SlabBlock(Block.Properties.from(DARK_PLANKS.get())));
    public static final RegistryObject<Block> NASTY_PLANKS_STAIRS = BLOCKS.register("nasty_planks_stairs", () -> new StairsBlock(() -> NASTY_PLANKS.get().getDefaultState(), Block.Properties.from(NASTY_PLANKS.get())));
    public static final RegistryObject<Block> NASTY_PLANKS_FENCE = BLOCKS.register("nasty_planks_fence", () -> new FenceBlock(Block.Properties.from(NASTY_PLANKS.get())));
    public static final RegistryObject<Block> NASTY_PLANKS_SLAB = BLOCKS.register("nasty_planks_slab", () -> new SlabBlock(Block.Properties.from(NASTY_PLANKS.get())));
    public static final RegistryObject<Block> DARK_LEAVES = BLOCKS.register("dark_leaves", DarkLeavesBlock::new);
    public static final RegistryObject<Block> DARK_COBBLESTONE = BLOCKS.register("dark_cobblestone", DarkStoneBlock::new);
    public static final RegistryObject<Block> ENDERITE_ORE = BLOCKS.register("enderite_ore_block", EnderiteOreBlock::new);
    public static final RegistryObject<Block> DARK_CLAY_BLOCK = BLOCKS.register("dark_clay_block", DarkEarthBlock::new);
    public static final RegistryObject<Block> POOP_BLOCK = BLOCKS.register("poop_block", PoopBlock::new);
    public static final RegistryObject<Block> DARK_BRICKS = BLOCKS.register("dark_bricks", DarkBricks::new);
    public static final RegistryObject<Block> DARK_BRICKS_STAIRS = BLOCKS.register("dark_bricks_stairs", () -> new StairsBlock(() -> DARK_BRICKS.get().getDefaultState(), Block.Properties.from(DARK_BRICKS.get())));
    public static final RegistryObject<Block> DARK_BRICKS_FENCE = BLOCKS.register("dark_bricks_fence", () -> new FenceBlock(Block.Properties.from(DARK_BRICKS.get())));
    public static final RegistryObject<Block> DARK_BRICKS_SLAB = BLOCKS.register("dark_bricks_slab", () -> new SlabBlock(Block.Properties.from(DARK_BRICKS.get())));
    public static final RegistryObject<Block> ENDERITE_BLOCK = BLOCKS.register("enderite_block", EnderiteBlock::new);
    public static final RegistryObject<Block> ULTRA_BLOCK = BLOCKS.register("ultra_block", UltraBlock::new);
    public static final RegistryObject<Block> ULTRA_ORE = BLOCKS.register("ultra_ore", UltraOre::new);
    public static final RegistryObject<Block> PINK_DIAMOND_ORE = BLOCKS.register("pink_diamond_ore", AlphaOreBlock::new);
    public static final RegistryObject<Block> ANTHRACITE_ORE = BLOCKS.register("anthracite_ore", AlphaOreBlock::new);
    public static final RegistryObject<Block> ALPHA_PORTAL_BLOCK = BLOCKS.register("alpha_portal_block", AlphaPortalBlock::new);
    public static final RegistryObject<Block> DARK_TREE_SAPLING = BLOCKS.register("dark_tree_sapling", () -> new DarkSapling(DarkAshTree::new, Block.Properties.from(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> NASTY_TREE_SAPLING = BLOCKS.register("nasty_tree_sapling", () -> new DarkSapling(NastyPoopTree::new, Block.Properties.from(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> SMELTER = BLOCKS.register("smelter", SmelterBlock::new);
    public static final RegistryObject<Block> DARK_CHEST = BLOCKS.register("dark_chest", DarkChestBlock::new);
    public static final RegistryObject<Block> DARK_GRASS = BLOCKS.register("dark_grass", TallDarkGrassBlock::new);
    public static final RegistryObject<Block> END_GRASS = BLOCKS.register("end_grass", TallDarkGrassBlock::new);
    public static final RegistryObject<Block> BRIGHT_FLOWER = BLOCKS.register("bright_flower", () -> new FlowerBlock(Effects.GLOWING, 30, AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).setLightLevel((p_235470_0_) -> { return 14;})));
    public static final RegistryObject<Block> BRIGHT_SAND = BLOCKS.register("bright_sand", () -> new BrightSand(14373781, AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND).harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> BRIGHT_SANDSTONE = BLOCKS.register("bright_sandstone", DarkStoneBlock::new);
    public static final RegistryObject<Block> SMOOTH_OBSIDIAN = BLOCKS.register("smooth_obsidian", () -> new CryingObsidianBlock(Block.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> ASH = BLOCKS.register("ash", () -> new BrightSand(14373781, AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND).harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> DARK_PODZOL = BLOCKS.register("dark_podzol", DarkEarthBlock::new);
    public static final RegistryObject<Block> PORTAL_FRAME = BLOCKS.register("alpha_portal_frame", AlphaPortalFrameBlock::new);
    public static final RegistryObject<Block> END_GRASS_BLOCK = BLOCKS.register("end_grass_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).sound(SoundType.NYLIUM).setRequiresTool().harvestTool(ToolType.SHOVEL).hardnessAndResistance(2.0F, 7.0F)));

    // Block Items  ======== This order determines order of appearance in creative tabs

    //Building Blocks

    // Naturals
    public static final RegistryObject<Item> DARK_STONE_ITEM = ITEMS.register("dark_stone", () -> new BlockItem(DARK_STONE.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> DARK_DIRT_BLOCK_ITEM = ITEMS.register("dark_dirt_block", () -> new BlockItem(DARK_DIRT_BLOCK.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> DARK_GRASS_BLOCK_ITEM = ITEMS.register("dark_grass_block", () -> new BlockItem(DARK_GRASS_BLOCK.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> DARK_PODZOL_ITEM = ITEMS.register("dark_podzol", () -> new BlockItem(DARK_PODZOL.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> DARK_COBBLESTONE_ITEM = ITEMS.register("dark_cobblestone", () -> new BlockItem(DARK_COBBLESTONE.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> DARK_CLAY_BLOCK_ITEM = ITEMS.register("dark_clay_block", () -> new BlockItem(DARK_CLAY_BLOCK.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> ASH_ITEM = ITEMS.register("ash", () -> new BlockItem(ASH.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> SMOOTH_OBSIDIAN_ITEM = ITEMS.register("smooth_obsidian", () -> new BlockItem(SMOOTH_OBSIDIAN.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> BRIGHT_SAND_ITEM = ITEMS.register("bright_sand", () -> new BlockItem(BRIGHT_SAND.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> BRIGHT_SANDSTONE_ITEM = ITEMS.register("bright_sandstone", () -> new BlockItem(BRIGHT_SANDSTONE.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> END_GRASS_BLOCK_ITEM = ITEMS.register("end_grass_block", () -> new BlockItem(END_GRASS_BLOCK.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    // Woods
    public static final RegistryObject<Item> DARK_PLANKS_ITEM = ITEMS.register("dark_planks", () -> new BlockItem(DARK_PLANKS.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> NASTY_PLANKS_ITEM = ITEMS.register("nasty_planks", () -> new BlockItem(NASTY_PLANKS.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> DARK_LOG_ITEM = ITEMS.register("dark_log", () -> new BlockItem(DARK_LOG.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> NASTY_STEM_ITEM = ITEMS.register("nasty_stem", () -> new BlockItem(NASTY_STEM.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    // Building Blocks
    public static final RegistryObject<Item> DARK_BRICKS_ITEM = ITEMS.register("dark_bricks", () -> new BlockItem(DARK_BRICKS.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    // Ores
    public static final RegistryObject<Item> ENDERITE_ORE_BLOCK_ITEM = ITEMS.register("enderite_ore_block", () -> new BlockItem(ENDERITE_ORE.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> ULTRA_ORE_ITEM = ITEMS.register("ultra_ore", () -> new BlockItem(ULTRA_ORE.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> PINK_DIAMOND_ORE_ITEM = ITEMS.register("pink_diamond_ore", () -> new BlockItem(PINK_DIAMOND_ORE.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> ANTHRACITE_ORE_ITEM = ITEMS.register("anthracite_ore", () -> new BlockItem(ANTHRACITE_ORE.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    // Stairs
    public static final RegistryObject<Item> DARK_PLANKS_STAIRS_ITEM = ITEMS.register("dark_planks_stairs", () -> new BlockItem(DARK_PLANKS_STAIRS.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> NASTY_PLANKS_STAIRS_ITEM = ITEMS.register("nasty_planks_stairs", () -> new BlockItem(NASTY_PLANKS_STAIRS.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> DARK_BRICKS_STAIRS_ITEM = ITEMS.register("dark_bricks_stairs", () -> new BlockItem(DARK_BRICKS_STAIRS.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    // Slabs
    public static final RegistryObject<Item> DARK_PLANKS_SLABS_ITEM = ITEMS.register("dark_planks_slab", () -> new BlockItem(DARK_PLANKS_SLAB.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> NASTY_PLANKS_SLABS_ITEM = ITEMS.register("nasty_planks_slab", () -> new BlockItem(NASTY_PLANKS_SLAB.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> DARK_BRICKS_SLABS_ITEM = ITEMS.register("dark_bricks_slab", () -> new BlockItem(DARK_BRICKS_SLAB.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    // Gem Blocks
    public static final RegistryObject<Item> ENDERITE_BLOCK_ITEM = ITEMS.register("enderite_block", () -> new BlockItem(ENDERITE_BLOCK.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> ULTRA_BLOCK_ITEM = ITEMS.register("ultra_block", () -> new BlockItem(ULTRA_BLOCK.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    // Misc
    public static final RegistryObject<Item> POOP_BLOCK_ITEM = ITEMS.register("poop_block", () -> new BlockItem(POOP_BLOCK.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));
    public static final RegistryObject<Item> ALPHA_PORTAL_BLOCK_ITEM = ITEMS.register("alpha_portal_block", () -> new BlockItem(ALPHA_PORTAL_BLOCK.get(), new Item.Properties().group(AlphaCraft.ALPHA_BLOCKS)));

    //Decoration Blocks

    // Saplings
    public static final RegistryObject<Item> DARK_TREE_SAPLING_ITEM = ITEMS.register("dark_tree_sapling", () -> new BlockItem(DARK_TREE_SAPLING.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    public static final RegistryObject<Item> NASTY_TREE_SAPLING_ITEM = ITEMS.register("nasty_tree_sapling", () -> new BlockItem(NASTY_TREE_SAPLING.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    // Leaves
    public static final RegistryObject<Item> DARK_LEAVES_ITEM = ITEMS.register("dark_leaves", () -> new BlockItem(DARK_LEAVES.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    public static final RegistryObject<Item> NASTY_LEAVES_ITEM = ITEMS.register("nasty_leaves", () -> new BlockItem(NASTY_LEAVES.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    // Bushes
    public static final RegistryObject<Item> DARK_GRASS_ITEM = ITEMS.register("dark_grass", () -> new BlockItem(DARK_GRASS.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    public static final RegistryObject<Item> END_GRASS_TEM = ITEMS.register("end_grass", () -> new BlockItem(END_GRASS.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    public static final RegistryObject<Item> BRIGHT_FLOWER_ITEM = ITEMS.register("bright_flower", () -> new BlockItem(BRIGHT_FLOWER.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    // Containers
    public static final RegistryObject<Item> SMELTER_ITEM = ITEMS.register("smelter", () -> new BlockItem(SMELTER.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    public static final RegistryObject<Item> DARK_CHEST_ITEM = ITEMS.register("dark_chest", () -> new BlockItem(DARK_CHEST.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    public static final RegistryObject<Item> ALPHA_PORTAL_FRAME_ITEM = ITEMS.register("alpha_portal_frame", () -> new BlockItem(PORTAL_FRAME.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    // Fences
    public static final RegistryObject<Item> DARK_PLANKS_FENCE_ITEM = ITEMS.register("dark_planks_fence", () -> new BlockItem(DARK_PLANKS_FENCE.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    public static final RegistryObject<Item> NASTY_PLANKS_FENCE_ITEM = ITEMS.register("nasty_planks_fence", () -> new BlockItem(NASTY_PLANKS_FENCE.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
    public static final RegistryObject<Item> DARK_BRICKS_FENCE_ITEM = ITEMS.register("dark_bricks_fence", () -> new BlockItem(DARK_BRICKS_FENCE.get(), new Item.Properties().group(AlphaCraft.ALPHA_DECOR)));
}
