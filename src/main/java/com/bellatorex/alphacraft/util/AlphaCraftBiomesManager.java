package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.world.biome.NastySwamp;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AlphaCraftBiomesManager
{
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, AlphaCraft.MOD_ID);
    public static void register(IEventBus modEventBus)
    {
        BIOMES.register(modEventBus);
    }

    public static void setupBiomes()
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(NASTY_SWAMP.get(), 10));

        BiomeDictionary.addTypes(NASTY_SWAMP.get(), Type.FOREST, Type.OVERWORLD);
    }

    //Biomes
    public static final RegistryObject<Biome> NASTY_SWAMP = BIOMES.register("nasty_swamp", NastySwamp::new);




}
