package com.bellatorex.alphacraft.blocks;

import com.bellatorex.alphacraft.util.AlphaDimensionsRegistry;
import com.bellatorex.alphacraft.world.AlphaTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.EndPortalTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.awt.*;

public class AlphaPortalBlock extends ContainerBlock {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    public AlphaPortalBlock() {
        super(Block.Properties.create(Material.PORTAL)
                .hardnessAndResistance(-0F,-0F));
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        if (!entity.isPassenger() && !entity.isBeingRidden() && entity.isNonBoss()) {
                // get player world
                World serverworld = entity.world;

                if(serverworld != null) {
                    // get the server from the current world
                    MinecraftServer minecraftserver = serverworld.getServer();
                    // if player is in the overworld set destination to the alpha, visa versa
                    RegistryKey<World> destination_dimension = entity.world.func_234923_W_() == AlphaDimensionsRegistry.the_alpha_w ? World.field_234918_g_ : AlphaDimensionsRegistry.the_alpha_w;

                    if(minecraftserver != null) {
                        ServerWorld destination = minecraftserver.getWorld(destination_dimension);

                        if (destination != null && minecraftserver.getAllowNether() && !entity.isPassenger()) {
                            entity.world.getProfiler().startSection("alpha_portal_block");
                            entity.changeDimension(destination, new AlphaTeleporter());
                            entity.world.getProfiler().endSection();
                        }
                    }
                }

        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isReplaceable(BlockState state, Fluid fluid) {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return null;
    }
}