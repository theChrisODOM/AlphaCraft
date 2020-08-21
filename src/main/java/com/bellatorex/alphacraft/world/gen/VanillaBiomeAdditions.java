package com.bellatorex.alphacraft.world.gen;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.util.AlphacraftBiomesManager;
import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = AlphaCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VanillaBiomeAdditions {
    public static OreFeatureConfig.FillerBlockType END_STONE = OreFeatureConfig.FillerBlockType.create("END_STONE", "end_stone", new BlockMatcher(Blocks.END_STONE));
    @SubscribeEvent
    public static void generateOres(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome.getCategory() == Biome.Category.NETHER) { // the end

            } else if (biome.getCategory() == Biome.Category.THEEND) {  // the nether
                genOre(biome, 10, 8, 5, 20, END_STONE, BlockRegistry.ENDERITE_ORE.get().getDefaultState(), 4);
            } else if (biome.getCategory() == Biome.Category.NONE ) { // the alpha

            }else {
                genOre(biome, 15, 8, 5, 40, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockRegistry.ULTRA_ORE.get().getDefaultState(), 10);
                biome.func_235063_a_(AlphacraftBiomesManager.ALPHA_PORTAL_CASTLE.func_236391_a_(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
    }
    private static void genOre(Biome biome, int count, int bottomOffset, int topOffset, int max, OreFeatureConfig.FillerBlockType filler, BlockState defaultBlockstate, int size) {
        CountRangeConfig range = new CountRangeConfig(count, bottomOffset, topOffset, max);
        OreFeatureConfig feature = new OreFeatureConfig(filler, defaultBlockstate, size);
        ConfiguredPlacement config = Placement.COUNT_RANGE.configure(range);
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(feature).withPlacement(config));
    }
}
