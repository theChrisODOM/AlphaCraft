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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

public class NastySwamp extends Biome
{


    public static final BlockState DARK_DIRT_BLOCK = BlockRegistry.DARK_DIRT_BLOCK.get().getDefaultState();
    public static final BlockState POOP_BLOCK = BlockRegistry.POOP_BLOCK.get().getDefaultState();
    public static final BlockState DARK_CLAY_BLOCK = BlockRegistry.DARK_CLAY_BLOCK.get().getDefaultState();
    public NastySwamp()
    {

        super((new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(DARK_DIRT_BLOCK, DARK_DIRT_BLOCK, DARK_DIRT_BLOCK))
                .precipitation(RainType.RAIN)
                .category(Category.NONE)
                .depth(0.0F)
                .scale(0.2F)
                .temperature(0.7F)
                .downfall(0.8F))
                .func_235097_a_(new BiomeAmbience.Builder()
                        .setFogColor(3091772)
                        .setWaterColor(3108412)
                        .setWaterFogColor(1196810)
                        .setAmbientSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .setParticle(new net.minecraft.world.biome.ParticleEffectAmbience(ParticleTypes.MYCELIUM.getType(), 0.02f))
                        .build())
                .parent((String)null));
    }

    public void init(){
        //Passives
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SALMON, 12, 2, 8));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COD, 12, 2, 8));
        //Monsters
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.PHANTOM, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.STRAY, 50, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.RAVAGER, 5, 1, 2));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.GHAST, 10, 2, 2));
        //Ground Features
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.withConfiguration(new SphereReplaceConfig(DARK_CLAY_BLOCK, 4, 2, Lists.newArrayList(DARK_DIRT_BLOCK, DARK_CLAY_BLOCK))).withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(2))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.withConfiguration(new SphereReplaceConfig(Blocks.CLAY.getDefaultState(), 4, 1, Lists.newArrayList(DARK_DIRT_BLOCK, DARK_DIRT_BLOCK))).withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(1))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.withConfiguration(new SphereReplaceConfig(Blocks.GRAVEL.getDefaultState(), 6, 2, Lists.newArrayList(DARK_DIRT_BLOCK, DARK_DIRT_BLOCK))).withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(1))));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.FOREST_ROCK.withConfiguration(new BlockBlobConfig(POOP_BLOCK, 0)).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(4))));
        //Trees
        AlphaBiomeFeatures.addNastyTrees(this);
        // Vegetation
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(5))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LILY_PAD_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(6))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.TALL_GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(15))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new SeaGrassConfig(80, 0.8D)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        DefaultBiomeFeatures.addExtraKelp(this);
        // Giant Mushrooms
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR.withConfiguration(new TwoFeatureChoiceConfig(Feature.HUGE_RED_MUSHROOM.withConfiguration(DefaultBiomeFeatures.BIG_RED_MUSHROOM), Feature.HUGE_BROWN_MUSHROOM.withConfiguration(DefaultBiomeFeatures.BIG_BROWN_MUSHROOM))).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.BROWN_MUSHROOM_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP.configure(new HeightWithChanceConfig(1, 0.25F))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.RED_MUSHROOM_CONFIG).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new HeightWithChanceConfig(1, 0.125F))));
        // Ore Generation
        AlphaBiomeFeatures.addAlphaOres(this);
        AlphaBiomeFeatures.addAlphaCarvers(this);
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public int getSkyColor() {
        return new Color(57,53,53).getRGB();
    }
}
