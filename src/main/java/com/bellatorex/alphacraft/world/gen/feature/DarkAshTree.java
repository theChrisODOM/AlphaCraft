package com.bellatorex.alphacraft.world.gen.feature;

import com.bellatorex.alphacraft.util.BlockRegistry;
import com.bellatorex.alphacraft.world.AlphaBiomeFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class DarkAshTree extends Tree {


    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return Feature.field_236291_c_.withConfiguration(AlphaBiomeFeatures.DARK_TREE_CONFIG);
    }
}
