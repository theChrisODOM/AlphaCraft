package com.bellatorex.alphacraft.world.biome;

import com.bellatorex.alphacraft.util.BlockRegistry;
import com.bellatorex.alphacraft.world.AlphaBiomeFeatures;
import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class AshDunes extends Biome {
    public AshDunes() {
        super((new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlockRegistry.ASH.get().getDefaultState(), BlockRegistry.ASH.get().getDefaultState(), BlockRegistry.DARK_DIRT_BLOCK.get().getDefaultState()))
                .precipitation(RainType.RAIN)
                .category(Category.NONE)
                .depth(0.3F)
                .scale(0.4F)
                .temperature(1.6F)
                .downfall(1.0F))
                .func_235097_a_(new BiomeAmbience.Builder()
                        .setFogColor(14373781)
                        .setWaterColor(1648698)
                        .setWaterFogColor(1648721)
                        .setAmbientSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .setParticle(new net.minecraft.world.biome.ParticleEffectAmbience(ParticleTypes.CRIMSON_SPORE.getType(), 0.01f))
                        .build())
                .parent((String)null));
    }

    public void init(){
        AlphaBiomeFeatures.addSmoothObsidianSpikes(this);
        DefaultBiomeFeatures.addExtraKelp(this);
        AlphaBiomeFeatures.addAlphaOres(this);
        AlphaBiomeFeatures.addAlphaCarvers(this);

        AlphaBiomeFeatures.addMoltenTerrain(this);

        //Monsters
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.PHANTOM, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.STRAY, 50, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.BLAZE, 10, 1, 3));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.GHAST, 10, 2, 2));
    }
}
