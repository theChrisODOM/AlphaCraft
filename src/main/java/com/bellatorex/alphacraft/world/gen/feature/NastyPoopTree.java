package com.bellatorex.alphacraft.world.gen.feature;


import com.bellatorex.alphacraft.world.AlphaBiomeFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class NastyPoopTree extends Tree {


    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return Feature.field_236291_c_.withConfiguration(AlphaBiomeFeatures.NASTY_TREE_CONFIG);
    }
}
