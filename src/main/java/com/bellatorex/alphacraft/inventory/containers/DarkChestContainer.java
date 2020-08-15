package com.bellatorex.alphacraft.inventory.containers;

import com.bellatorex.alphacraft.tileentity.DarkChestTileEntity;
import com.bellatorex.alphacraft.util.AlphaContainerRegistry;
import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class DarkChestContainer extends Container {

    public final DarkChestTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public DarkChestContainer(final int windowId, final PlayerInventory playerInventory, final DarkChestTileEntity tileEntity) {
        super(AlphaContainerRegistry.DARK_CHEST.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        // Main Inventory
        int startX = 8;  // X and Y values for first ingredient, second ingredient, fuel, then result
        int startY = 18;   // the index for this is primary ingredient = 0, secondary ingredient = 1, fuel = 2, result = 3

        for(int row = 0; row < 4; row++){
            for(int column = 0; column < 9; column++){
                this.addSlot(new Slot(tileEntity, (row*9)+column, startX+(column*18), startY+(row*18)));
            }
        }

        // Player Inventory
        int playerInventoryY = startY * 5 +12;
        for(int row = 0; row < 3; row++){
            for(int column = 0; column < 9; column++){
                this.addSlot(new Slot(playerInventory, 9+(row*9)+column, 8+(column*18), playerInventoryY+(row*18)));
            }
        }

        // Player Hotbar
        int hotbarY = playerInventoryY + (playerInventoryY / 2) +7;
        for(int column = 0; column < 9; column++){
            this.addSlot(new Slot(playerInventory, column, 8+(column*18), hotbarY));
        }

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockRegistry.DARK_CHEST.get());
    }

    private static DarkChestTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data){
        Objects.requireNonNull(playerInventory, "player inv cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if(tileAtPos instanceof DarkChestTileEntity){
            return (DarkChestTileEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct!" +tileAtPos); // this should never be thrown
    }

    public DarkChestContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data){
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack itemStack1 = slot.getStack();
            if(index <36){
                if(!this.mergeItemStack(itemStack1, 36, this.inventorySlots.size(), true)){
                    return ItemStack.EMPTY;
                }
            }else if (!this.mergeItemStack(itemStack1, 0, 36, false)){
                return ItemStack.EMPTY;
            }
            if(itemStack1.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            }else{
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }
}
