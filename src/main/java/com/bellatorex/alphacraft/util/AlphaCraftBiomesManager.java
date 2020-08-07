package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.world.biome.NastyPlains;
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

    public static final RegistryObject<Biome> NASTY_PLAINS = BIOMES.register("nasty_plains", NastyPlains::new);

    public static void register(IEventBus modEventBus)
    {
        BIOMES.register(modEventBus);
    }

    public static void setupBiomes()
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(NASTY_PLAINS.get(), 10));

        BiomeDictionary.addTypes(NASTY_PLAINS.get(), Type.PLAINS, Type.OVERWORLD);
    }
}
