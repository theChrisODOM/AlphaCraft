package com.bellatorex.alphacraft.inventory.containers;

import com.bellatorex.alphacraft.inventory.containers.slots.SmelterFuelSlot;
import com.bellatorex.alphacraft.inventory.containers.slots.SmelterResultSlot;
import com.bellatorex.alphacraft.recipes.IAlphaRecipeType;
import com.bellatorex.alphacraft.recipes.SmelterRecipe;
import com.bellatorex.alphacraft.tileentity.SmelterTileEntity;
import com.bellatorex.alphacraft.util.AlphaContainerRegistry;
import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;
import java.util.Objects;

public class SmelterContainer extends RecipeBookContainer<IInventory> {

    private final IIntArray furnaceData;
    protected final World world;
    public final SmelterTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;
    private final IRecipeType<AbstractCookingRecipe> recipeType = IAlphaRecipeType.SMELTER; // hard coding to the smelter recipe type

    public SmelterContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data){
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }
    public SmelterContainer(final int windowId, final PlayerInventory playerInventoryIn, final SmelterTileEntity tileEntity) {
        super(AlphaContainerRegistry.SMELTER.get(), windowId);
        this.tileEntity = tileEntity;
        this.furnaceData = tileEntity.furnaceData;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
        assertInventorySize(tileEntity, 4);
        assertIntArraySize(furnaceData, 4);
        this.world = playerInventoryIn.player.world;
        // Smelter Slots
        this.addSlot(new Slot(tileEntity, 0, 33, 17));
        this.addSlot(new Slot(tileEntity, 1, 59, 17));
        this.addSlot(new SmelterFuelSlot(this, tileEntity, 2, 46, 53));
        this.addSlot(new SmelterResultSlot(playerInventoryIn.player, tileEntity, 3, 116, 35));
        // Player Inventory
        int playerInventoryY = 84;
        for(int row = 0; row < 3; row++){
            for(int column = 0; column < 9; column++){
                this.addSlot(new Slot(playerInventoryIn, 9+(row*9)+column, 8+(column*18), playerInventoryY+(row*18)));
            }
        }
        // Player Hotbar
        int hotbarY = 142;
        for(int column = 0; column < 9; column++){
            this.addSlot(new Slot(playerInventoryIn, column, 8+(column*18), hotbarY));
        }
        this.trackIntArray(this.furnaceData);
    }
    @Override
    public void clear() {
        this.tileEntity.clear();
    }
    @Override
    public void fillStackedContents(RecipeItemHelper itemHelperIn) {

    }
    @Override
    public boolean matches(IRecipe<? super IInventory> recipeIn) {
        return recipeIn.matches(this.tileEntity, this.world);
    }
    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockRegistry.SMELTER.get());
    }
    private static SmelterTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data){
        Objects.requireNonNull(playerInventory, "player inv cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if(tileAtPos instanceof SmelterTileEntity){
            return (SmelterTileEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct!" +tileAtPos); // this should never be thrown
    }
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 3) { // if you shift click the result index
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != 1 && index != 0 && index != 2) { // if you are not shift clicking in the smelter, ie if you are shift clicking from inventory into the smelter
                if (this.hasRecipe(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 4 && index < 31) {
                    if (!this.mergeItemStack(itemstack1, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 31 && index < 40 && !this.mergeItemStack(itemstack1, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
    protected boolean hasRecipe(ItemStack stack) {
        return this.world.getRecipeManager().getRecipe((IRecipeType)this.recipeType, new Inventory(stack), this.world).isPresent();
    }
    @Override
    public int getOutputSlot() { return 3; }
    @Override
    public int getWidth() { return 1; }
    @Override
    public int getHeight() { return 1; }
    @Override
    public int getSize() { return 4; }

    public boolean isFuel(ItemStack stack) {
        return SmelterTileEntity.isFuel(stack);
    }

    @OnlyIn(Dist.CLIENT)
    public int getCookProgressionScaled() {
        int i = this.furnaceData.get(2);
        int j = this.furnaceData.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }
    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled() {
        int i = this.furnaceData.get(1);
        if (i == 0) {
            i = 200;
        }
        return this.furnaceData.get(0) * 13 / i;
    }
    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return this.furnaceData.get(0) > 0;
    }
}
