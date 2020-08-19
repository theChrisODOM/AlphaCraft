package com.bellatorex.alphacraft.world.biome;

import com.bellatorex.alphacraft.util.BlockRegistry;
import com.bellatorex.alphacraft.world.AlphaBiomeFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BrightDunes extends Biome {
    public BrightDunes() {
        super((new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlockRegistry.BRIGHT_SAND.get().getDefaultState(), BlockRegistry.BRIGHT_SAND.get().getDefaultState(), BlockRegistry.DARK_DIRT_BLOCK.get().getDefaultState()))
                .precipitation(RainType.RAIN)
                .category(Category.ICY)
                .depth(0.3F)
                .scale(0.4F)
                .temperature(1.6F)
                .downfall(1.0F))
                .func_235097_a_(new BiomeAmbience.Builder()
                        .setFogColor(14373781)
                        .setWaterColor(14373781)
                        .setWaterFogColor(14373781)
                        .setAmbientSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .setParticle(new net.minecraft.world.biome.ParticleEffectAmbience(ParticleTypes.CRIMSON_SPORE.getType(), 0.01f))
                        .build())
                .parent((String)null));
    }

    public void init(){
        this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Feature.ICE_SPIKE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(3))));
        DefaultBiomeFeatures.addExtraKelp(this);
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.CACTUS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(50))));
        AlphaBiomeFeatures.addAlphaOres(this);
        AlphaBiomeFeatures.addAlphaCarvers(this);
        AlphaBiomeFeatures.addSmoothObsidianSpikes(this);
    }
}
