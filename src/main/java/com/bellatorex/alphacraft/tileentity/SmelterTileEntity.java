package com.bellatorex.alphacraft.tileentity;

import com.bellatorex.alphacraft.blocks.SmelterBlock;
import com.bellatorex.alphacraft.inventory.containers.SmelterContainer;
import com.bellatorex.alphacraft.recipes.IAlphaRecipeType;
import com.bellatorex.alphacraft.recipes.SmelterRecipe;
import com.bellatorex.alphacraft.util.AlphaTileEntityRegistry;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public class SmelterTileEntity extends LockableLootTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {


    private NonNullList<ItemStack> itemsInSmelter = NonNullList.withSize(4, ItemStack.EMPTY);
    private int burnTime;
    private int recipesUsed;
    private int cookTime;
    private int cookTimeTotal;
    public final IIntArray furnaceData = new IIntArray() {
        public int get(int index) { switch(index) {
                case 0:
                    return SmelterTileEntity.this.burnTime;
                case 1:
                    return SmelterTileEntity.this.recipesUsed;
                case 2:
                    return SmelterTileEntity.this.cookTime;
                case 3:
                    return SmelterTileEntity.this.cookTimeTotal;
                default:
                    return 0;
            }
        }
        public void set(int index, int value) { switch(index) {
                case 0:
                    SmelterTileEntity.this.burnTime = value;
                    break;
                case 1:
                    SmelterTileEntity.this.recipesUsed = value;
                    break;
                case 2:
                    SmelterTileEntity.this.cookTime = value;
                    break;
                case 3:
                    SmelterTileEntity.this.cookTimeTotal = value;
            }
        }
        public int size() {
            return 4;
        }
    };
    private final Object2IntOpenHashMap<ResourceLocation> recipes = new Object2IntOpenHashMap<>();
    protected final IRecipeType<SmelterRecipe> recipeType;
    protected int numPlayersUsing;
    private IItemHandlerModifiable items = createHandler();
    private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(()->items);
    private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;

    public SmelterTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.recipeType= IAlphaRecipeType.SMELTER; // hard coding this tile entity to the smelter recipe type
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.smelter");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new SmelterContainer( id, player,this);
    }

    public SmelterTileEntity() {
        this(AlphaTileEntityRegistry.SMELTER.get());
    }

    @Override
    public int getSizeInventory() {
        return 5;
    }

    public NonNullList<ItemStack> getItems() {
        return this.itemsInSmelter;
    }

    public void setItems(NonNullList<ItemStack> itemsIn){
        this.itemsInSmelter = itemsIn;
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if(!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.itemsInSmelter);
        }
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);this.itemsInSmelter = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if(!this.checkLootAndRead(nbt)){
            ItemStackHelper.loadAllItems(nbt,this.itemsInSmelter);
        }
    }

    private void playSound(SoundEvent sound){
        double dx = (double)this.pos.getX() + 0.5D;
        double dy = (double)this.pos.getY() + 0.5D;
        double dz = (double)this.pos.getZ() + 0.5D;
        this.world.playSound((PlayerEntity)null, dx, dy, dz, sound, SoundCategory.AMBIENT, 0.5f, this.world.rand.nextFloat() * 0.1f + 0.9f);
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if(id == 1){
            this.numPlayersUsing = type;
            return true;
        }else{
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public void openInventory(PlayerEntity player) {
        if(!player.isSpectator()){
            if(this.numPlayersUsing < 0){
                this.numPlayersUsing = 0;
            }
            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    @Override
    public void closeInventory(PlayerEntity player) {
        if(!player.isSpectator()){
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    protected void onOpenOrClose() {
        Block block = this.getBlockState().getBlock();
        if (block instanceof SmelterBlock) {
            this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, block);
        }
    }

    public static int getPlayersUsing(IBlockReader reader, BlockPos pos){
        BlockState blockstate = reader.getBlockState(pos);
        if(blockstate.hasTileEntity()){
            TileEntity te = reader.getTileEntity(pos);
            if(te instanceof SmelterTileEntity){
                return((SmelterTileEntity)te).numPlayersUsing;
            }
        }
        return 0;
    }

    public static void swapContents(SmelterTileEntity te, SmelterTileEntity otherTe){
        NonNullList<ItemStack> list = te.getItems();
        te.setItems(otherTe.getItems());
        otherTe.setItems(list);
    }

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        if(this.itemHandler != null){
            this.itemHandler.invalidate();
            this.itemHandler = null;
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (!this.removed && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (this.chestHandler == null)
                this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
            return this.chestHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    private IItemHandlerModifiable createHandler(){
        return new InvWrapper(this);
    }
    @Override
    public void remove(){
        super.remove();
        if(itemHandler != null){
            itemHandler.invalidate();
        }
    }

    protected boolean canSmelt(@Nullable IRecipe<?> recipeIn) {
        if (!this.itemsInSmelter.get(0).isEmpty() && !this.itemsInSmelter.get(1).isEmpty() && recipeIn != null) {
            ItemStack itemstack = recipeIn.getRecipeOutput();
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = this.itemsInSmelter.get(3);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.isItemEqual(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }
    
    private void smelt(@Nullable IRecipe<?> recipe) {
        
        if (recipe != null && this.canSmelt(recipe)) {
            ItemStack itemstackPrimary = this.itemsInSmelter.get(0);
            ItemStack itemstackSecondary = this.itemsInSmelter.get(1);
            ItemStack itemstackRecipeOutput = recipe.getRecipeOutput();
            ItemStack itemstackOutput = this.itemsInSmelter.get(3);
            if (itemstackOutput.isEmpty()) { // if output slot is empty then add recipe output called for by ingredients
                this.itemsInSmelter.set(3, itemstackRecipeOutput.copy());
            } else if (itemstackOutput.getItem() == itemstackRecipeOutput.getItem()) { // increment output slot by 1 if ingredients can make it
                itemstackOutput.grow(itemstackRecipeOutput.getCount());
            }

            if (!this.world.isRemote) {
                this.setRecipeUsed(recipe);
            }

            itemstackPrimary.shrink(1); // remove 1 ingredient from both primary slot
            itemstackSecondary.shrink(2); // and from secondary slot
        }
    }
    
    public static boolean isFuel(ItemStack stack) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(stack) > 0;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1) {
            Item item = stack.getItem();
            if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {

    }

    @Override
    public void setRecipeUsed( IRecipe<?> recipe) {

    }

    @Override
    public IRecipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void tick() {

    }
    protected int getBurnTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return net.minecraftforge.common.ForgeHooks.getBurnTime(fuel);
        }
    }

    protected int getCookTime() {
        return 200;
    }
}
