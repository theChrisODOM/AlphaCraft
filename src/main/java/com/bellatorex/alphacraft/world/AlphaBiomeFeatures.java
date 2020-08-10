package com.bellatorex.alphacraft.world;

import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class AlphaBiomeFeatures {

    public static final BaseTreeFeatureConfig NASTY_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.NASTY_STEM.get().getDefaultState()), new SimpleBlockStateProvider(Blocks.SPRUCE_LEAVES.getDefaultState()), new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(9, 2, 0), new TwoLayerFeature(1, 0, 1))).build();
    public static final BaseTreeFeatureConfig DARK_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.NASTY_STEM.get().getDefaultState()), new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()), new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(5, 2, 0), new TwoLayerFeature(1, 0, 1))).build();

    public static final StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> STRONGHOLD = Structure.field_236375_k_.func_236391_a_(NoFeatureConfig.field_236559_b_);

    public static void addNastyTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_236291_c_.withConfiguration(NASTY_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
    }

    public static void addDarkTrees(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_236291_c_.withConfiguration(DARK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(12, 0.3F, 1))));
    }
}
