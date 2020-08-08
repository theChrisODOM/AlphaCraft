package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class EnderiteOreBlock extends OreBlock {

    public EnderiteOreBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(6.0f,8.0f)
                .sound(SoundType.NETHER_GOLD)
                .harvestLevel(3)
                .harvestTool(ToolType.PICKAXE));

    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch){
        return 1;
    }
}