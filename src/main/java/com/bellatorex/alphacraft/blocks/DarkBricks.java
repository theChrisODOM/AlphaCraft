package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class DarkBricks extends Block {

    public DarkBricks() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(4.0f,6.0f)
                .sound(SoundType.NETHER_BRICK)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE));
    }
}
