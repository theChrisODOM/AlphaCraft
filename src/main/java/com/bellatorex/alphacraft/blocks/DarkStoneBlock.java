package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class DarkStoneBlock extends Block {
    public DarkStoneBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(0.5f,2.0f)
                .sound(SoundType.STONE)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE));
    }
}