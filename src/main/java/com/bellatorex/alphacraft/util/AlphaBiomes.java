package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.world.biome.DarkRidge;
import com.bellatorex.alphacraft.world.biome.DarkRidgeTop;
import com.bellatorex.alphacraft.world.biome.DarkerForest;
import com.bellatorex.alphacraft.world.biome.NastySwamp;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AlphaBiomes
{
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, AlphaCraft.MOD_ID);
    public static void register(IEventBus modEventBus)
    {
        BIOMES.register(modEventBus);
    }

    //Biomes
    public static final RegistryObject<Biome> NASTY_SWAMP = BIOMES.register("nasty_swamp", NastySwamp::new);
    public static final RegistryObject<Biome> DARK_FOREST = BIOMES.register("dark_forest", DarkerForest::new);
    public static final RegistryObject<Biome> DARK_RIDGE = BIOMES.register("dark_ridge", DarkRidge::new);
    public static final RegistryObject<Biome> DARK_RIDGE_TOP = BIOMES.register("dark_ridge_top", DarkRidgeTop::new);

    public static void setupBiomes()
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(NASTY_SWAMP.get(), 10));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(DARK_FOREST.get(), 10));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(DARK_RIDGE.get(), 10));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(DARK_RIDGE_TOP.get(), 10));
        
        BiomeDictionary.addTypes(NASTY_SWAMP.get(), Type.SWAMP, Type.OVERWORLD);
        BiomeDictionary.addTypes(DARK_FOREST.get(), Type.FOREST, Type.OVERWORLD);
        BiomeDictionary.addTypes(DARK_RIDGE.get(), Type.RIVER, Type.OVERWORLD);
        BiomeDictionary.addTypes(DARK_RIDGE.get(), Type.RIVER, Type.OVERWORLD);

    }


}
