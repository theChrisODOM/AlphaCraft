package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class AlphaDimensionsRegistry {

        public static final RegistryKey<DimensionType> the_alpha = RegistryKey.func_240903_a_(Registry.DIMENSION_TYPE_KEY, new ResourceLocation(AlphaCraft.MOD_ID, "the_alpha"));
        public static final RegistryKey<World> the_alpha_w = RegistryKey.func_240903_a_(Registry.WORLD_KEY, new ResourceLocation(AlphaCraft.MOD_ID, "the_alpha"));



}
