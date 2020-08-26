package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.world.biome.AshDunes;
import com.bellatorex.alphacraft.world.biome.BrightDunes;
import com.bellatorex.alphacraft.world.biome.DarkForest;
import com.bellatorex.alphacraft.world.biome.NastySwamp;
import com.bellatorex.alphacraft.world.gen.AlphaWorldCarver;
import com.bellatorex.alphacraft.world.gen.feature.SmoothObsidianSpike;
import com.bellatorex.alphacraft.world.gen.feature.structure.AlphaPortalCastlePieces;
import com.bellatorex.alphacraft.world.gen.feature.structure.AlphaPortalCastleStructure;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

public class AlphacraftBiomesManager
{
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, AlphaCraft.MOD_ID);
    public static final DeferredRegister<WorldCarver<?>> ALPHA_CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, AlphaCraft.MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, AlphaCraft.MOD_ID);


    public static void setupBiomes()
    {
        // set the weight to 0 so they do not spawn in the overworld
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(NASTY_SWAMP.get(), 0));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(DARK_FOREST.get(), 0));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BRIGHT_DUNES.get(), 0));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(ASH_DUNES.get(), 0));
        BiomeDictionary.addTypes(NASTY_SWAMP.get(), Type.SWAMP);
        BiomeDictionary.addTypes(DARK_FOREST.get(), Type.FOREST);
        BiomeDictionary.addTypes(BRIGHT_DUNES.get(), Type.DRY);
        BiomeDictionary.addTypes(ASH_DUNES.get(), Type.DRY);
        DARK_FOREST.get().init();
        NASTY_SWAMP.get().init();
        BRIGHT_DUNES.get().init();
        ASH_DUNES.get().init();

    }
    public static void init() {
        ALPHA_CARVERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event){
        registerStructure(new ResourceLocation(AlphaCraft.MOD_ID, "portal"), ALPHA_PORTAL_CASTLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(100, 50, 69121598), false);
        AlphacraftBiomesManager.registerAllPieces();

    }
    public static <F extends Structure<NoFeatureConfig>> void registerStructure(
            ResourceLocation resourceLocation,
            F structure,
            GenerationStage.Decoration stage,
            StructureSeparationSettings structureSeparationSettings,
            boolean transformSurroundingLand)
    {
        structure.setRegistryName(resourceLocation);
        addToStructureInfoMaps(resourceLocation.toString(), structure, stage);
        FlatGenerationSettings.STRUCTURES.put(structure, structure.func_236391_a_(IFeatureConfig.NO_FEATURE_CONFIG));
        /*
         * Will add land at the base of the structure like it does for Villages and Outposts.
         * Doesn't work well on structure that have pieces stacked vertically.
         */
        if(transformSurroundingLand){ Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder().addAll(Structure.field_236384_t_).add(structure).build(); }
        /*
         * Adds the structure's spacing into several places so that the structure's spacing remains
         * correct in any dimension or worldtype instead of defaulting to spawning every single chunk.
         */
        DimensionStructuresSettings.field_236191_b_ = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.field_236191_b_).put(structure, structureSeparationSettings).build();
        DimensionSettings.Preset.OVERWORLD.getSettings().getStructures().func_236195_a_().put(structure, structureSeparationSettings);
        DimensionSettings.Preset.NETHER.getSettings().getStructures().func_236195_a_().put(structure, structureSeparationSettings);
        DimensionSettings.Preset.END.getSettings().getStructures().func_236195_a_().put(structure, structureSeparationSettings);
        DimensionSettings.Preset.FLOATING_ISLANDS.getSettings().getStructures().func_236195_a_().put(structure, structureSeparationSettings);
        DimensionSettings.Preset.AMPLIFIED.getSettings().getStructures().func_236195_a_().put(structure, structureSeparationSettings);
        DimensionSettings.Preset.CAVES.getSettings().getStructures().func_236195_a_().put(structure, structureSeparationSettings);
        DimensionSettings.Preset.PRESET_MAP.forEach((presetResourceLocation, preset) -> preset.getSettings().getStructures().func_236195_a_().put(structure, structureSeparationSettings));
    }
    private static <F extends Structure<?>> F addToStructureInfoMaps(String name, F structure, GenerationStage.Decoration generationStage) {
        Structure.field_236365_a_.put(name.toLowerCase(Locale.ROOT), structure);
        Structure.field_236385_u_.put(structure, generationStage);
        return Registry.register(Registry.STRUCTURE_FEATURE, name.toLowerCase(Locale.ROOT), structure);
    }
    public static void registerAllPieces() { registerStructurePiece(APCP, "APCP"); }
    static IStructurePieceType registerStructurePiece(IStructurePieceType structurePiece, String key) { return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), structurePiece); }
    //Biomes
    public static final RegistryObject<NastySwamp> NASTY_SWAMP = BIOMES.register("nasty_swamp", NastySwamp::new);
    public static final RegistryObject<DarkForest> DARK_FOREST = BIOMES.register("dark_forest", DarkForest::new);
    public static final RegistryObject<BrightDunes> BRIGHT_DUNES = BIOMES.register("bright_dunes", BrightDunes::new);
    public static final RegistryObject<AshDunes> ASH_DUNES = BIOMES.register("ash_dunes", AshDunes::new);
    //Carvers
    public static final RegistryObject<WorldCarver<ProbabilityConfig>> ALPHA_CAVE = ALPHA_CARVERS.register("alpha_carver" ,() -> new AlphaWorldCarver(ProbabilityConfig.field_236576_b_, 256));
    //Features
    public static final RegistryObject<Feature<NoFeatureConfig>> SMOOTH_OBSIDIAN_SPIKE = FEATURES.register("smooth_obsidian_spike", ()-> new SmoothObsidianSpike(NoFeatureConfig.field_236558_a_));
    //Structures
    public static Structure<NoFeatureConfig> ALPHA_PORTAL_CASTLE = new AlphaPortalCastleStructure(NoFeatureConfig.field_236558_a_);
    public static IStructurePieceType APCP = AlphaPortalCastlePieces.AlphaPortalCastle::new;
}
