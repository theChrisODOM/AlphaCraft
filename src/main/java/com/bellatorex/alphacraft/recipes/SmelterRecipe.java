package com.bellatorex.alphacraft.recipes;

import com.bellatorex.alphacraft.util.RecipeSerializerRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class SmelterRecipe implements ISmelterRecipe{


    private final ResourceLocation id;
    private Ingredient primary;
    private Ingredient secondary;
    private final ItemStack output;

    public SmelterRecipe(ResourceLocation id, Ingredient primary, Ingredient secondary, ItemStack output){
        this.id = id;
        this.primary = primary;
        this.secondary = secondary;
        this.output = output;
    }


    @Override
    public boolean matches(RecipeWrapper inv, World worldIn) {
        if(this.primary.test(inv.getStackInSlot(0)) && this.secondary.test(inv.getStackInSlot(1))){
            return true;
        }return false;
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return this.output;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializerRegistry.SMELTER_SERIALIZER.get();
    }

    @Override
    public Ingredient getInput() {
        return this.primary;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(null, this.primary);
    }
}
