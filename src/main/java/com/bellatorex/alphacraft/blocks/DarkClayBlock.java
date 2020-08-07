package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class DarkClayBlock extends Block {
    public DarkClayBlock() {
        super(Block.Properties.create(Material.EARTH)
                .hardnessAndResistance(4.0f,6.0f)
                .sound(SoundType.GROUND)
                .harvestLevel(1)
                .harvestTool(ToolType.SHOVEL));
    }
}