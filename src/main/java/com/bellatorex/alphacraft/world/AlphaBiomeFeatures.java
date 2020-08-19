package com.bellatorex.alphacraft.world;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.util.AlphaCraftBiomesManager;
import com.bellatorex.alphacraft.util.BlockRegistry;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.DarkOakFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunkplacer.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.OptionalInt;

public class AlphaBiomeFeatures {

    public static OreFeatureConfig.FillerBlockType DARK_STONE = OreFeatureConfig.FillerBlockType.create("DARK_STONE", "dark_stone", new BlockMatcher(BlockRegistry.DARK_STONE.get()));
    public static final BaseTreeFeatureConfig NASTY_TREE_SAPLING_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.NASTY_STEM.get().getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.NASTY_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(5, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build();
    public static final BaseTreeFeatureConfig DARK_TREE_SAPLING_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.DARK_LOG.get().getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.DARK_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(3, 0, 0, 0, 3), new StraightTrunkPlacer(8, 4, 1), new TwoLayerFeature(1, 0, 1))).build();
    public static final BaseTreeFeatureConfig NASTY_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.NASTY_STEM.get().getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.NASTY_LEAVES.get().getDefaultState()), new DarkOakFoliagePlacer(0, 0, 0, 0), new DarkOakTrunkPlacer(6, 2, 1), new ThreeLayerFeature(1, 1, 0, 1, 2, OptionalInt.empty()))).func_236701_a_(Integer.MAX_VALUE).func_236702_a_(Heightmap.Type.MOTION_BLOCKING).setIgnoreVines().build();
    public static final BaseTreeFeatureConfig MEGA_DARK_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.DARK_LOG.get().getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.DARK_LEAVES.get().getDefaultState()), new MegaPineFoliagePlacer(0, 0, 0, 0, 5, 12), new GiantTrunkPlacer(13, 2, 14), new TwoLayerFeature(1, 1, 2))).func_236703_a_(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(BlockRegistry.DARK_GRASS_BLOCK.get().getDefaultState())))).build();
    public static final BlockClusterFeatureConfig BRIGHT_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.BRIGHT_FLOWER.get().getDefaultState()), SimpleBlockPlacer.field_236447_c_)).tries(16).build();
    public static final BlockClusterFeatureConfig DARK_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.DARK_GRASS.get().getDefaultState()), SimpleBlockPlacer.field_236447_c_)).tries(32).build();

    public static void addNastyTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_236291_c_.withConfiguration(NASTY_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(3, 0.1F, 1))));
    }

    public static void addDarkTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_236291_c_.withConfiguration(MEGA_DARK_TREE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(7))));
    }

    public static void addDenseBlueGrass(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DARK_GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(10))));
    }

    public static void addBrightFlowers(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(BRIGHT_FLOWER_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(10))));
    }
    public static void addAlphaOres(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DARK_STONE, BlockRegistry.PINK_DIAMOND_ORE.get().getDefaultState(), 6)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(5, 8, 0, 20))));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(DARK_STONE, BlockRegistry.ANTHRACITE_ORE.get().getDefaultState(), 12)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 8, 0, 80))));
    }
    public static void addAlphaCarvers(Biome biomeIn) {
        biomeIn.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(AlphaCraftBiomesManager.ALPHA_CAVE.get(), new ProbabilityConfig(0.169F)));
    }
    public static void addSmoothObsidianSpikes(Biome biomeIn){
        biomeIn.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, AlphaCraftBiomesManager.SMOOTH_OBSIDIAN_SPIKE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(1))));
    }

}