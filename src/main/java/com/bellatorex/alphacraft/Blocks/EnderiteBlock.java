package com.bellatorex.alphacraft.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class EnderiteBlock extends Block {

    public EnderiteBlock() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(6.0f,8.0f)
                .sound(SoundType.METAL)
                .harvestLevel(3)
                .harvestTool(ToolType.PICKAXE));

    }
}