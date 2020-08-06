package com.bellatorex.alphacraft.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class EnderiteOreBlock extends Block {

    public EnderiteOreBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(6.0f,8.0f)
                .sound(SoundType.STONE)
                .harvestLevel(3)
                .harvestTool(ToolType.PICKAXE));

    }
}