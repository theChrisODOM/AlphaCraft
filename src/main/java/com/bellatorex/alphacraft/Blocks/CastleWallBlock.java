package com.bellatorex.alphacraft.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class CastleWallBlock extends Block {

    public CastleWallBlock() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(4.0f,6.0f)
                .sound(SoundType.METAL)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE));
    }
}
