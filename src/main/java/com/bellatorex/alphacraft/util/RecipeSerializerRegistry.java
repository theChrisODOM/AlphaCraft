package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.recipes.ISmelterRecipe;
import com.bellatorex.alphacraft.recipes.SmelterRecipeSerializer;
import com.bellatorex.alphacraft.recipes.SmelterRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerRegistry {
    public static final IRecipeSerializer<SmelterRecipe> SMELTER_RECIPE_SERIALIZER = new SmelterRecipeSerializer();
    public static final IRecipeType<ISmelterRecipe> SMELTER_TYPE = registerType(ISmelterRecipe.RECIPE_TYPE_ID);
    public static void init() {
        RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AlphaCraft.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<?>> SMELTER_SERIALIZER = RECIPE_SERIALIZERS.register("smelter", ()-> SMELTER_RECIPE_SERIALIZER);

    public static class RegistryType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString(){
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

    private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId){
        return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RegistryType<>());
    }
}
