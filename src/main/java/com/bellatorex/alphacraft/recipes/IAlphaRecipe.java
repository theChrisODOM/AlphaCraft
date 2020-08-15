package com.bellatorex.alphacraft.recipes;

import com.bellatorex.alphacraft.AlphaCraft;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;

public interface IAlphaRecipe extends IRecipe<RecipeWrapper> {

    ResourceLocation RECIPE_TYPE_ID = new ResourceLocation(AlphaCraft.MOD_ID, "smelter");

    @Nonnull
    @Override
    default IRecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getValue(RECIPE_TYPE_ID).get();
    }

    @Override
    boolean canFit(int width, int height);

    Ingredient[] getInputs();
}
