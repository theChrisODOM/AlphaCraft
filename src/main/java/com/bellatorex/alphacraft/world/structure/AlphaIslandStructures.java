package com.bellatorex.alphacraft.world.structure;

import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class AlphaIslandStructures {

    /**
     *  These functions are not designed to be utilized in other locations or dimensions, they are hard coded for the center island of the alpha dimension
     *  spawnPlatform will spawn the square platform on the edge of the island that the player spawns on
     *  spawnReturnPortal will spawn the return portal fountain at the center of the island
     */

    public static void spawnPlatform(ServerWorld destination){
        //sets spawn platform

        int i = 0;int j = 161;int k = 48;
        BlockPos.getAllInBoxMutable(i - 2, j + 1, k - 2, i + 2, j + 20, k + 2).forEach((blockInBox) -> {
            destination.setBlockState(blockInBox, Blocks.AIR.getDefaultState());
        });
        BlockPos.getAllInBoxMutable(i - 2, j, k - 2, i + 2, j, k + 2).forEach((blockInBox) -> {
            destination.setBlockState(blockInBox, BlockRegistry.DARK_COBBLESTONE.get().getDefaultState());
        });

    }

    public static void spawnReturnPortal(ServerWorld destination){
        int i = 0;int j=166;int k=0;

        // Dark Bricks
        BlockPos.getAllInBoxMutable(i - 2, j, k - 2, i + 2, j+1, k + 2).forEach((blockInBox) -> { destination.setBlockState(blockInBox, BlockRegistry.DARK_BRICKS.get().getDefaultState()); });
        BlockPos.getAllInBoxMutable(i - 3, j, k - 1, i - 3, j+1, k + 1).forEach((blockInBox) -> { destination.setBlockState(blockInBox, BlockRegistry.DARK_BRICKS.get().getDefaultState()); });
        BlockPos.getAllInBoxMutable(i + 3, j, k - 1, i + 3, j+1, k + 1).forEach((blockInBox) -> { destination.setBlockState(blockInBox, BlockRegistry.DARK_BRICKS.get().getDefaultState()); });
        BlockPos.getAllInBoxMutable(i - 1, j, k + 3, i + 1, j+1, k + 3).forEach((blockInBox) -> { destination.setBlockState(blockInBox, BlockRegistry.DARK_BRICKS.get().getDefaultState()); });
        BlockPos.getAllInBoxMutable(i - 1, j, k - 3, i + 1, j+1, k - 3).forEach((blockInBox) -> { destination.setBlockState(blockInBox, BlockRegistry.DARK_BRICKS.get().getDefaultState()); });
        // Portal Blocks
        BlockPos.getAllInBoxMutable(i - 1, j+1, k - 1, i + 1, j+1, k + 1).forEach((blockInBox) -> { destination.setBlockState(blockInBox, BlockRegistry.ALPHA_PORTAL_BLOCK.get().getDefaultState()); });
        BlockPos.getAllInBoxMutable(i - 2, j+1, k - 1, i - 2, j+1, k + 1).forEach((blockInBox) -> { destination.setBlockState(blockInBox, BlockRegistry.ALPHA_PORTAL_BLOCK.get().getDefaultState()); });
        BlockPos.getAllInBoxMutable(i + 2, j+1, k - 1, i + 2, j+1, k + 1).forEach((blockInBox) -> { destination.setBlockState(blockInBox, BlockRegistry.ALPHA_PORTAL_BLOCK.get().getDefaultState()); });
        BlockPos.getAllInBoxMutable(i - 1, j+1, k + 2, i + 1, j+1, k + 2).forEach((blockInBox) -> { destination.setBlockState(blockInBox, BlockRegistry.ALPHA_PORTAL_BLOCK.get().getDefaultState()); });
        BlockPos.getAllInBoxMutable(i - 1, j+1, k - 2, i + 1, j+1, k - 2).forEach((blockInBox) -> { destination.setBlockState(blockInBox, BlockRegistry.ALPHA_PORTAL_BLOCK.get().getDefaultState()); });
        // air blocks
        BlockPos.getAllInBoxMutable(i - 4, j + 2, k - 4, i + 4, j + 20, k + 4).forEach((blockInBox) -> { destination.setBlockState(blockInBox, Blocks.AIR.getDefaultState()); });
        // thingy in the middle
        BlockPos.getAllInBoxMutable(i , j+1, k, i, j+5, k).forEach((blockInBox) -> { destination.setBlockState(blockInBox, BlockRegistry.DARK_BRICKS.get().getDefaultState()); });
    }
}
