package com.bellatorex.alphacraft.blocks;

import com.bellatorex.alphacraft.tileentity.DarkChestTileEntity;
import com.bellatorex.alphacraft.util.AlphaTileEntityRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class DarkChestBlock extends ContainerBlock {
    public static final DirectionProperty PROPERTY_FACING = BlockStateProperties.FACING;
    public static final BooleanProperty PROPERTY_OPEN = BlockStateProperties.OPEN;
    public DarkChestBlock() {
        super(Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(5.0f, 6.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE)
                .setRequiresTool());
        this.setDefaultState(this.stateContainer.getBaseState().with(PROPERTY_FACING, Direction.NORTH).with(PROPERTY_OPEN, Boolean.valueOf(false)));
    }
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) { return AlphaTileEntityRegistry.DARK_CHEST.get().create(); }
    public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof DarkChestTileEntity) {
                DarkChestTileEntity te = (DarkChestTileEntity)tileentity;
                te.openInventory(player);
                NetworkHooks.openGui((ServerPlayerEntity)player, te, pos);
            }
            return ActionResultType.CONSUME;
        }
    }
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) { return this.getDefaultState().with(PROPERTY_FACING, context.getNearestLookingDirection().getOpposite()); }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) { builder.add(PROPERTY_FACING, PROPERTY_OPEN); }
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            TileEntity te = worldIn.getTileEntity(pos);
            if(te instanceof DarkChestTileEntity){
                InventoryHelper.dropItems(worldIn, pos, ((DarkChestTileEntity) te).getItems());
                te.remove();
            }
        }
    }
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) { return Container.calcRedstone(worldIn.getTileEntity(pos)); }
}
