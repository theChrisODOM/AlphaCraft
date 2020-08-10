package com.bellatorex.alphacraft.world.dimension;



import com.bellatorex.alphacraft.AlphaCraft;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class AlphaDimension {

    public static final RegistryKey<DimensionType> THE_ALPHA = RegistryKey.func_240903_a_(Registry.DIMENSION_TYPE_KEY, new ResourceLocation(AlphaCraft.MOD_ID, "the_aplha"));
    public static final RegistryKey<World> THE_ALPHA_W = RegistryKey.func_240903_a_(Registry.WORLD_KEY, new ResourceLocation(AlphaCraft.MOD_ID, "the_alpha"));


}