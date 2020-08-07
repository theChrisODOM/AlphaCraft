package com.bellatorex.alphacraft.world.biome;

import com.bellatorex.alphacraft.util.RegistryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class NastyPlains extends Biome
{

    public static final BlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
    public static final BlockState COARSE_DIRT = Blocks.COARSE_DIRT.getDefaultState();
    public static final BlockState POOP_BLOCK = RegistryHandler.POOP_BLOCK.get().getDefaultState();
    public NastyPlains()
    {

        super((new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(POOP_BLOCK, COARSE_DIRT, GRAVEL))
                .precipitation(RainType.RAIN)
                .category(Category.PLAINS)
                .depth(0.1F)
                .scale(0.2F)
                .temperature(0.7F)
                .downfall(0.8F))
                .func_235097_a_(new BiomeAmbience.Builder()
                        .setFogColor(16627961)
                        .setWaterColor(7098023)
                        .setWaterFogColor(14733798)
                        .setMoodSound(MoodSoundAmbience.field_235027_b_)
                        .build())
                .parent((String)null));

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SALMON, 12, 2, 8));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COD, 12, 2, 8));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SLIME, 12, 2, 8));

    }
}
