package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.world.biome.BrightDunes;
import com.bellatorex.alphacraft.world.biome.DarkForest;
import com.bellatorex.alphacraft.world.biome.NastySwamp;
import com.bellatorex.alphacraft.world.gen.AlphaWorldCarver;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AlphaCraftBiomesManager
{
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, AlphaCraft.MOD_ID);
    public static final DeferredRegister<WorldCarver<?>> ALPHA_CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, AlphaCraft.MOD_ID);
    public static void register(IEventBus modEventBus)
    {
        BIOMES.register(modEventBus);
    }
    public static void setupBiomes()
    {
        // set the weight to 0 so they do not spawn in the overworld
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(NASTY_SWAMP.get(), 0));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(DARK_FOREST.get(), 0));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BRIGHT_DUNES.get(), 0));
        BiomeDictionary.addTypes(NASTY_SWAMP.get(), Type.SWAMP);
        BiomeDictionary.addTypes(DARK_FOREST.get(), Type.FOREST);
        BiomeDictionary.addTypes(BRIGHT_DUNES.get(), Type.DRY);
        DARK_FOREST.get().init();
        NASTY_SWAMP.get().init();
        BRIGHT_DUNES.get().init();

    }
    public static void init() {
        ALPHA_CARVERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    //Biomes
    public static final RegistryObject<NastySwamp> NASTY_SWAMP = BIOMES.register("nasty_swamp", NastySwamp::new);
    public static final RegistryObject<DarkForest> DARK_FOREST = BIOMES.register("dark_forest", DarkForest::new);
    public static final RegistryObject<BrightDunes> BRIGHT_DUNES = BIOMES.register("bright_dunes", BrightDunes::new);
    //Carvers
    public static final RegistryObject<WorldCarver<ProbabilityConfig>> ALPHA_CAVE = ALPHA_CARVERS.register("alpha_carver" ,() -> new AlphaWorldCarver(ProbabilityConfig.field_236576_b_, 256));
}
