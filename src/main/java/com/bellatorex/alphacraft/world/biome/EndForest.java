package com.bellatorex.alphacraft.world.biome;

import com.bellatorex.alphacraft.util.BlockRegistry;
import com.bellatorex.alphacraft.world.AlphaBiomeFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EndForest extends Biome{
    public static final BlockState END_GRASS_BLOCK = BlockRegistry.END_GRASS_BLOCK.get().getDefaultState();
    public static final BlockState END_STONE = Blocks.END_STONE.getDefaultState();

    public EndForest()
    {
        super((new Biome.Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(END_GRASS_BLOCK, END_STONE, END_STONE))
                .precipitation(RainType.NONE)
                .category(Category.THEEND)
                .depth(1.3F)
                .scale(0.4F)
                .temperature(0.6F)
                .downfall(1.0F))
                .func_235097_a_(new BiomeAmbience.Builder()
                        .setFogColor(1648698)
                        .setWaterColor(1648698)
                        .setWaterFogColor(1648721)
                        .setMoodSound(MoodSoundAmbience.field_235027_b_)
                        .setParticle(new net.minecraft.world.biome.ParticleEffectAmbience(ParticleTypes.END_ROD.getType(), 0.001f))
                        .build())
                .parent((String)null));
    }
    public void init() {
        this.func_235063_a_(DefaultBiomeFeatures.END_CITY);
        this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Feature.END_GATEWAY.withConfiguration(EndGatewayConfig.func_214702_a(ServerWorld.field_241108_a_, true)).withPlacement(Placement.END_GATEWAY.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.CHORUS_PLANT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHORUS_PLANT.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 4, 4));
    }

    @OnlyIn(Dist.CLIENT)
    public int getSkyColor() {
        return 0;
    }


}
