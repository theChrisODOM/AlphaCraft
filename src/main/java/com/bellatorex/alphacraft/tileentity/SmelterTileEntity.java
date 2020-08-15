package com.bellatorex.alphacraft.tileentity;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.blocks.SmelterBlock;
import com.bellatorex.alphacraft.inventory.containers.SmelterContainer;
import com.bellatorex.alphacraft.recipes.IAlphaRecipeType;
import com.bellatorex.alphacraft.util.AlphaContainerRegistry;
import com.bellatorex.alphacraft.util.AlphaTileEntityRegistry;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ExperienceOrbEntity;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;
import java.util.List;

public class SmelterTileEntity extends LockableLootTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {


    private static final int[] SLOTS_UP = new int[]{0};
    private static final int[] SLOTS_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_HORIZONTAL = new int[]{1};
    private NonNullList<ItemStack> itemsInSmelter = NonNullList.withSize(4, ItemStack.EMPTY);
    private int burnTime;
    private int recipesUsed;
    private int cookTime;
    private int cookTimeTotal = 200;
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
    protected final IRecipeType<? extends AbstractCookingRecipe> recipeType;
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
        return 4;
    }

    public NonNullList<ItemStack> getItems() {
        return this.itemsInSmelter;
    }

    public void setItems(NonNullList<ItemStack> itemsIn){
        this.itemsInSmelter = itemsIn;
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", this.cookTimeTotal);
        ItemStackHelper.saveAllItems(compound, this.itemsInSmelter);
        CompoundNBT compoundnbt = new CompoundNBT();
        this.recipes.forEach((p_235643_1_, p_235643_2_) -> {
            compoundnbt.putInt(p_235643_1_.toString(), p_235643_2_);
        });
        compound.put("RecipesUsed", compoundnbt);
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.itemsInSmelter = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.itemsInSmelter);
        this.burnTime = nbt.getInt("BurnTime");
        this.cookTime = nbt.getInt("CookTime");
        this.cookTimeTotal = nbt.getInt("CookTimeTotal");
        this.recipesUsed = this.getBurnTime(this.itemsInSmelter.get(2));
        CompoundNBT compoundnbt = nbt.getCompound("RecipesUsed");

        for(String s : compoundnbt.keySet()) {
            this.recipes.put(new ResourceLocation(s), compoundnbt.getInt(s));
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

    public void tick() {
        boolean ifSmelterIsCurrentlyBurning = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }

        if (!this.world.isRemote) {
            ItemStack fuelItemStack = this.itemsInSmelter.get(2);
            if ((this.isBurning() || !fuelItemStack.isEmpty()) && !this.itemsInSmelter.get(0).isEmpty() && !this.itemsInSmelter.get(1).isEmpty()) {
                IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>)this.recipeType, this, this.world).orElse(null);
                // this runs if the furnace is off but there is a recipe in the furnace
                if (!this.isBurning() && this.canSmelt(irecipe)) {
                    this.burnTime = this.getBurnTime(fuelItemStack);
                    this.recipesUsed = this.burnTime;
                    if (this.isBurning()) {
                        flag1 = true;
                        if (fuelItemStack.hasContainerItem())
                            this.itemsInSmelter.set(2, fuelItemStack.getContainerItem());
                        else
                        if (!fuelItemStack.isEmpty()) {
                            Item item = fuelItemStack.getItem();
                            fuelItemStack.shrink(1);
                            if (fuelItemStack.isEmpty()) {
                                this.itemsInSmelter.set(1, fuelItemStack.getContainerItem());
                            }
                        }
                    }
                }
                // this runs if the furnace is on and there is a recipe in the furnace
                if (this.isBurning() && this.canSmelt(irecipe)) {
                    ++this.cookTime;
                    if (this.cookTime >= this.cookTimeTotal) {
                        this.cookTime = 0;
                        this.cookTimeTotal = this.getCookTime();
                        this.smelt(irecipe);
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
            }

            if (ifSmelterIsCurrentlyBurning != this.isBurning()) {
                flag1 = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, Boolean.valueOf(this.isBurning())), 3);
            }
        }

        if (flag1) {
            this.markDirty();
        }

    }


    protected boolean canSmelt(@Nullable IRecipe<?> recipeIn) {
        if (!this.itemsInSmelter.get(0).isEmpty() && !this.itemsInSmelter.get(1).isEmpty() && recipeIn != null) {
            ItemStack recipeOutputStack = recipeIn.getRecipeOutput();
            if (recipeOutputStack.isEmpty()) {
                return false;
            } else {
                ItemStack outPutItemStack = this.itemsInSmelter.get(3);
                if (outPutItemStack.isEmpty()) {
                    return true;
                } else if (!outPutItemStack.isItemEqual(recipeOutputStack)) {
                    return false;
                } else if (outPutItemStack.getCount() + recipeOutputStack.getCount() <= this.getInventoryStackLimit() && outPutItemStack.getCount() + recipeOutputStack.getCount() <= outPutItemStack.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return outPutItemStack.getCount() + recipeOutputStack.getCount() <= recipeOutputStack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
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
            itemstackSecondary.shrink(1); // and from secondary slot
        }
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    public static boolean isFuel(ItemStack stack) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(stack) > 0;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_DOWN;
        } else {
            return side == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
        }
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
        if (recipe != null) {
            ResourceLocation resourcelocation = recipe.getId();
            this.recipes.addTo(resourcelocation, 1);
        }
    }

    @Override
    public IRecipe<?> getRecipeUsed() {
        return null;
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

    public void func_235645_d_(PlayerEntity player) {
        List<IRecipe<?>> list = this.func_235640_a_(player.world, player.getPositionVec());
        player.unlockRecipes(list);
        this.recipes.clear();
    }

    public List<IRecipe<?>> func_235640_a_(World worldIn, Vector3d vector) {
        List<IRecipe<?>> list = Lists.newArrayList();

        for(Object2IntMap.Entry<ResourceLocation> entry : this.recipes.object2IntEntrySet()) {
            worldIn.getRecipeManager().getRecipe(entry.getKey()).ifPresent((recipe) -> {
                list.add(recipe);
                AlphaCraft.LOGGER.debug("Xp from recipe: "+((AbstractCookingRecipe)recipe).getExperience());
                giveExperience(worldIn, vector, entry.getIntValue(), ((AbstractCookingRecipe)recipe).getExperience());
            });
        }

        return list;
    }

    private static void giveExperience(World worldIn, Vector3d vector3d, int p_235641_2_, float recipeExperienceAmount) {
        int i = MathHelper.floor((float)p_235641_2_ * recipeExperienceAmount);
        float f = MathHelper.frac((float)p_235641_2_ * recipeExperienceAmount);
        if (f != 0.0F && Math.random() < (double)f) {
            ++i;
        }

        while(i > 0) {
            int j = ExperienceOrbEntity.getXPSplit(i);
            i -= j;
            worldIn.addEntity(new ExperienceOrbEntity(worldIn, vector3d.x, vector3d.y, vector3d.z, j));
        }

    }


}
