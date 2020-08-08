package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class DarkPlankBlock extends Block {

    public DarkPlankBlock() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .hardnessAndResistance(1.5f,3.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(1)
                .harvestTool(ToolType.AXE));

    }
}
