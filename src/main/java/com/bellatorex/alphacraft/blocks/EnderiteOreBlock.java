package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class EnderiteOreBlock extends OreBlock {

    public EnderiteOreBlock() {
        super(Block.Properties.from(Blocks.ANCIENT_DEBRIS));

    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch){
        return 1;
    }
}