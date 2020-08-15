package com.bellatorex.alphacraft.recipes;

import com.bellatorex.alphacraft.util.RecipeSerializerRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class SmelterRecipe implements IRecipe<IInventory> {


    protected final IRecipeType<?> type = IAlphaRecipeType.SMELTER;
    private final ResourceLocation id;
    private Ingredient primary;
    private Ingredient secondary;
    private final ItemStack output;
    protected final float experience;
    protected final int cookTime;

    public SmelterRecipe(ResourceLocation id, Ingredient primary, Ingredient secondary, ItemStack output, float experience, int cookTime){
        this.id = id;
        this.primary = primary;
        this.secondary = secondary;
        this.output = output;
        this.cookTime = cookTime;
        this.experience = experience;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        if(this.primary.test(inv.getStackInSlot(0)) && this.secondary.test(inv.getStackInSlot(1))){
            return true;
        }return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) { return this.output; }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    public Ingredient getPrimary() { return this.primary; }
    public Ingredient getSecondary() { return this.secondary; }

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
    public IRecipeType<?> getType() {
        return type;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(null, this.primary);
    }
}
