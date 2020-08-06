package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class PoopBlock extends Block {

    public PoopBlock() {
        super(Block.Properties.create(Material.CLAY)
                .hardnessAndResistance(2.0f,4.0f)
                .sound(SoundType.SLIME)
                .harvestLevel(0)
                .harvestTool(ToolType.SHOVEL));

    }
}