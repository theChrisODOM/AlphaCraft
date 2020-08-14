package com.bellatorex.alphacraft.blocks;

import com.bellatorex.alphacraft.tileentity.SmelterTileEntity;
import com.bellatorex.alphacraft.util.AlphaTileEntityRegistry;
import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.fml.network.NetworkHooks;

public class SmelterBlock extends Block {
    public SmelterBlock() {
        super(Block.Properties.from(BlockRegistry.DARK_COBBLESTONE.get()));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return AlphaTileEntityRegistry.SMELTER.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            TileEntity tile = worldIn.getTileEntity(pos);
            if(tile instanceof SmelterTileEntity){
                NetworkHooks.openGui((ServerPlayerEntity)player, (SmelterTileEntity)tile, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            TileEntity te = worldIn.getTileEntity(pos);
            if(te instanceof SmelterTileEntity){
                InventoryHelper.dropItems(worldIn, pos, ((SmelterTileEntity) te).getItems());
            }
        }
    }

}
