package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class DarkGrassBlock extends Block {

    public DarkGrassBlock() {
        super(AbstractBlock.Properties.create(Material.EARTH)
                .hardnessAndResistance(1.0f, 2.0f)
                .sound(SoundType.PLANT)
                .harvestTool(ToolType.SHOVEL));

    }
}
