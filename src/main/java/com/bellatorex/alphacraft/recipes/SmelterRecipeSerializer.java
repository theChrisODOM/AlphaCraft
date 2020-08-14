package com.bellatorex.alphacraft.recipes;

import com.bellatorex.alphacraft.util.RecipeSerializerRegistry;
import com.google.gson.JsonObject;
import com.sun.istack.internal.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SmelterRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SmelterRecipe> {

    @Override
    public SmelterRecipe read(ResourceLocation recipeId, JsonObject json) {
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        Ingredient primary = Ingredient.deserialize(JSONUtils.getJsonObject(json, "primary"));
        Ingredient secondary = Ingredient.deserialize(JSONUtils.getJsonObject(json, "secondary"));

        return new SmelterRecipe(recipeId, primary, secondary, output);
    }

    @Nullable
    @Override
    public SmelterRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ItemStack output = buffer.readItemStack();
        Ingredient primary = Ingredient.read(buffer);
        Ingredient secondary = Ingredient.read(buffer);
        return new SmelterRecipe(recipeId, primary, secondary, output);
    }

    @Override
    public void write(PacketBuffer buffer, SmelterRecipe recipe) {
        Ingredient primary = recipe.getIngredients().get(0);
        Ingredient secondary = recipe.getIngredients().get(1);
        primary.write(buffer);
        secondary.write(buffer);

        buffer.writeItemStack(recipe.getRecipeOutput(), false);
    }

}
