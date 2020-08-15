package com.bellatorex.alphacraft.blocks;

import com.bellatorex.alphacraft.tileentity.DarkChestTileEntity;
import com.bellatorex.alphacraft.tileentity.SmelterTileEntity;
import com.bellatorex.alphacraft.util.AlphaTileEntityRegistry;
import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DarkChestBlock extends Block {
    public DarkChestBlock() {
        super(Block.Properties.from(BlockRegistry.DARK_PLANKS.get()));
        this.setDefaultState(this.stateContainer.getBaseState());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return AlphaTileEntityRegistry.DARK_CHEST.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            NetworkHooks.openGui((ServerPlayerEntity)player, (DarkChestTileEntity)tile, pos);
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            TileEntity te = worldIn.getTileEntity(pos);
            if(te instanceof DarkChestTileEntity){
                InventoryHelper.dropItems(worldIn, pos, ((DarkChestTileEntity) te).getItems());
            }
        }
    }
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }


    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

}
