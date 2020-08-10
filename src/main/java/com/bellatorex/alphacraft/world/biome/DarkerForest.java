package com.bellatorex.alphacraft.world.biome;

import com.bellatorex.alphacraft.util.BlockRegistry;
import com.bellatorex.alphacraft.world.AlphaBiomeFeatures;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import static net.minecraft.world.biome.DefaultBiomeFeatures.STRONGHOLD;

public class DarkerForest extends Biome
{

    public static final BlockState DARK_DIRT_BLOCK = BlockRegistry.DARK_DIRT_BLOCK.get().getDefaultState();
    public static final BlockState DARK_GRASS_BLOCK = BlockRegistry.DARK_GRASS_BLOCK.get().getDefaultState();
    public static final BlockState DARK_COBBLESTONE = BlockRegistry.DARK_COBBLESTONE.get().getDefaultState();
    public DarkerForest()
    {

        super((new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(DARK_GRASS_BLOCK, DARK_DIRT_BLOCK, DARK_DIRT_BLOCK))
                .precipitation(RainType.RAIN)
                .category(Category.FOREST)
                .depth(1.2F)
                .scale(0.4F)
                .temperature(0.6F)
                .downfall(1.0F))
                .func_235097_a_(new BiomeAmbience.Builder()
                        .setFogColor(1648698)
                        .setWaterColor(1648698)
                        .setWaterFogColor(1648721)
                        .setAmbientSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .setParticle(new net.minecraft.world.biome.ParticleEffectAmbience(ParticleTypes.SOUL.getType(), 0.001f))
                        .build())
                .parent((String)null));

        //Passives
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SALMON, 25, 2, 8));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COD, 25, 2, 8));;
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 25, 2, 8));

        //Monsters
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 30, 2, 8));

        //Ground Features
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.FOREST_ROCK.withConfiguration(new BlockBlobConfig(DARK_COBBLESTONE, 0)).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(4))));

        //Trees
        AlphaBiomeFeatures.addDarkTrees(this);
        // Vegetation
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(DefaultBiomeFeatures.BLUE_ORCHID_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(5))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.DEAD_BUSH_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(3))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LILY_PAD_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(6))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.TALL_GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(15))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new SeaGrassConfig(80, 0.3D)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

        //Structures
        this.func_235063_a_(STRONGHOLD);


    }
}
