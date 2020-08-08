package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class DarkEarthBlock extends Block {
    public DarkEarthBlock() {
        super(Block.Properties.create(Material.EARTH)
                .hardnessAndResistance(0.5f,2.0f)
                .sound(SoundType.GROUND)
                .harvestLevel(1)
                .harvestTool(ToolType.SHOVEL));
    }
}