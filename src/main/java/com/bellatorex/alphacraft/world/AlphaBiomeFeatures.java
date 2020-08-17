package com.bellatorex.alphacraft.world;

import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class AlphaBiomeFeatures {

    public static final BaseTreeFeatureConfig NASTY_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.NASTY_STEM.get().getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.NASTY_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(5, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build();
    public static final BaseTreeFeatureConfig DARK_TREE_SAPLING_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.DARK_LOG.get().getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.DARK_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(5, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build();
    public static final HugeFungusConfig DARK_TREE_CONFIG = new HugeFungusConfig(BlockRegistry.DARK_GRASS_BLOCK.get().getDefaultState(), BlockRegistry.DARK_LOG.get().getDefaultState(), BlockRegistry.DARK_LEAVES.get().getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(), false);
    public static final BlockClusterFeatureConfig DARK_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.DARK_GRASS.get().getDefaultState()), SimpleBlockPlacer.field_236447_c_)).tries(32).build();
    public static void addNastyTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_236291_c_.withConfiguration(NASTY_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(4, 0.1F, 1))));
    }

    public static void addDarkTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_236291_c_.withConfiguration(DARK_TREE_SAPLING_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(7))));
    }

    public static void addDenseBlueGrass(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DARK_GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(10))));
    }


}
