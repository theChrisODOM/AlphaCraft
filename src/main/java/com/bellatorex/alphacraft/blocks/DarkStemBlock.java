package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class DarkStemBlock extends Block {


    public DarkStemBlock() {

        super(Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(1.5f,3.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(1)
                .harvestTool(ToolType.AXE));


    }
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 15;
    }

}