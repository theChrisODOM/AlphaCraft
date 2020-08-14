package com.bellatorex.alphacraft.containers;

import com.bellatorex.alphacraft.tileentity.SmelterTileEntity;
import com.bellatorex.alphacraft.util.AlphaContainerRegistry;
import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.ICollisionReader;
import net.minecraft.world.IWorld;

import java.util.Objects;

public class SmelterContainer extends Container {

    public final SmelterTileEntity tileEntity;
    private final IWorldPosCallable canIteractWithCallable;

    public SmelterContainer(final int windowId, final PlayerInventory playerInventory, final SmelterTileEntity tileEntity) {
        super(AlphaContainerRegistry.SMELTER.get(), windowId);
        this.tileEntity = tileEntity;
        this.canIteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        // Smelter Slots
        int smelterSlotsX[] = {33,59,46,116};  // X and Y values for first ingredient, second ingredient, fuel, then result
        int smelterSlotsY[] = {17,17,53,35};   // the index for this is primary ingredient = 0, secondary ingredient = 1, fuel = 2, result = 3

        for(int i = 0; i < smelterSlotsX.length; i ++){
            this.addSlot(new Slot(tileEntity, i, smelterSlotsX[i], smelterSlotsY[i]));
        }

        // Player Inventory
        int playerInventoryY = 84;
        for(int row = 0; row < 3; row++){
            for(int column = 0; column < 9; column++){
                this.addSlot(new Slot(playerInventory, 9+(row*9)+column, 8+(column*18), playerInventoryY+(row*18)));
            }
        }

        // Player Hotbar
        int hotbarY = 142;
        for(int column = 0; column < 9; column++){
            this.addSlot(new Slot(playerInventory, column, 8+(column*18), hotbarY));
        }

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canIteractWithCallable, playerIn, BlockRegistry.SMELTER.get());
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

    public SmelterContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data){
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack itemStack1 = slot.getStack();
            if(index <4){
                if(!this.mergeItemStack(itemStack1, 4, this.inventorySlots.size(), true)){
                    return ItemStack.EMPTY;
                }
            }else if (!this.mergeItemStack(itemStack1, 0, 4, false)){
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
