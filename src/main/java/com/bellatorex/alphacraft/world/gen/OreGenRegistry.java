package com.bellatorex.alphacraft.world.gen;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = AlphaCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)


public class OreGenRegistry {
    @SubscribeEvent
    public static void generateOres(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            // Nether Generation
            if (biome.getCategory() == Biome.Category.NETHER) {

            // End Generation
            } else if (biome.getCategory() == Biome.Category.THEEND) {

            // World Generation
            } else {
                genOre(biome, 20, 8, 5, 50, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockRegistry.ULTRA_ORE.get().getDefaultState(), 12);

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
