package com.bellatorex.alphacraft.world.biome;


import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SeaGrassConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class DarkRidge extends Biome {


    public static final BlockState DARK_DIRT_BLOCK = BlockRegistry.DARK_DIRT_BLOCK.get().getDefaultState();
    public static final BlockState DARK_GRASS_BLOCK = BlockRegistry.DARK_GRASS_BLOCK.get().getDefaultState();
    public static final BlockState DARK_COBBLESTONE = BlockRegistry.DARK_COBBLESTONE.get().getDefaultState();

    public DarkRidge() {
        super((new Biome.Builder()).
                surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(DARK_GRASS_BLOCK, DARK_DIRT_BLOCK, DARK_DIRT_BLOCK))
                .precipitation(Biome.RainType.RAIN)
                .category(Biome.Category.OCEAN)
                .depth(-1.5F)
                .scale(0.1F)
                .temperature(0.4F)
                .downfall(0.5F)
                .func_235097_a_((new BiomeAmbience.Builder())
                        .setWaterColor(1648698)
                        .setWaterFogColor(1648721)
                        .setFogColor(1648698)
                        .setAmbientSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .setParticle(new net.minecraft.world.biome.ParticleEffectAmbience(ParticleTypes.WARPED_SPORE.getType(), 0.005f))
                        .build())
                .parent((String)null));

        DefaultBiomeFeatures.addOceanCarvers(this);
        DefaultBiomeFeatures.addSprings(this);
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new SeaGrassConfig(64, 0.4D)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        DefaultBiomeFeatures.addSeagrass(this);
        DefaultBiomeFeatures.addExtraKelp(this);
        this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(EntityType.SQUID, 3, 1, 4));
        this.addSpawn(EntityClassification.WATER_AMBIENT, new Biome.SpawnListEntry(EntityType.COD, 15, 3, 6));
    }
}