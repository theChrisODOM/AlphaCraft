package com.bellatorex.alphacraft.tileentity;

import com.bellatorex.alphacraft.blocks.DarkChestBlock;
import com.bellatorex.alphacraft.inventory.containers.DarkChestContainer;
import com.bellatorex.alphacraft.util.AlphaTileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import java.util.stream.IntStream;
import net.minecraft.inventory.ItemStackHelper;

public class DarkChestTileEntity extends LockableLootTileEntity {
    private static final int[] SLOTS = IntStream.range(0, 36).toArray();
    private NonNullList<ItemStack> chestItems = NonNullList.withSize(36, ItemStack.EMPTY);
    private int openCount;

    public DarkChestTileEntity() {
        super(AlphaTileEntityRegistry.DARK_CHEST.get());
    }
    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory() {
        return this.chestItems.size();
    }
    public void openInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.openCount < 0) {
                this.openCount = 0;
            }
            ++this.openCount;
            BlockState blockstate = this.getBlockState();
            boolean flag = blockstate.get(DarkChestBlock.PROPERTY_OPEN);
            if (!flag) {
                this.world.playSound(null, this.pos, SoundEvents.BLOCK_SHULKER_BOX_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
                this.setOpenProperty(blockstate, true);
            }
        }
    }
    public void closeInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.openCount;
        }
        this.world.playSound(null, this.pos, SoundEvents.BLOCK_SHULKER_BOX_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
        this.setOpenProperty(this.getBlockState(), false);
    }
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("Dark Chest");
    }
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.chestItems);
        }
        return compound;
    }
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.chestItems = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.chestItems);
        }
    }
    public NonNullList<ItemStack> getItems() {
        return this.chestItems;
    }
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.chestItems = itemsIn;
    }
    public int[] getSlotsForFace(Direction side) {
        return SLOTS;
    }
    private void setOpenProperty(BlockState p_213963_1_, boolean p_213963_2_) {
        this.world.setBlockState(this.getPos(), p_213963_1_.with(DarkChestBlock.PROPERTY_OPEN, Boolean.valueOf(p_213963_2_)), 3);
    }
    protected Container createMenu(int id, PlayerInventory player) {
        return new DarkChestContainer(id, player, this);
    }
}

