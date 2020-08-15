package com.bellatorex.alphacraft.recipes;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SmelterRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SmelterRecipe> {

    private final int cookingTime;

    public SmelterRecipeSerializer(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Override
    public SmelterRecipe read(ResourceLocation recipeId, JsonObject json) {
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        Ingredient primary = Ingredient.deserialize(JSONUtils.getJsonObject(json, "primary"));
        Ingredient secondary = Ingredient.deserialize(JSONUtils.getJsonObject(json, "secondary"));
        float f = JSONUtils.getFloat(json, "experience", 0.0F);
        int i = JSONUtils.getInt(json, "cookingtime", this.cookingTime);
        return new SmelterRecipe(recipeId, primary, secondary, output, f, i);
    }

    @Override
    public SmelterRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ItemStack output = buffer.readItemStack();
        Ingredient primary = Ingredient.read(buffer);
        Ingredient secondary = Ingredient.read(buffer);

        float f = buffer.readFloat();
        int i = buffer.readVarInt();
        return new SmelterRecipe(recipeId, primary, secondary, output,f,i);
    }

    @Override
    public void write(PacketBuffer buffer, SmelterRecipe recipe) {
        Ingredient primary = recipe.getPrimary();
        Ingredient secondary = recipe.getSecondary();
        primary.write(buffer);
        secondary.write(buffer);

        buffer.writeItemStack(recipe.getRecipeOutput(), false);
    }

}
