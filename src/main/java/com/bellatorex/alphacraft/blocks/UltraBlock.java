package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class UltraBlock extends Block {
    public UltraBlock() { super(Block.Properties.from(Blocks.NETHERITE_BLOCK).setLightLevel((p_235470_0_) -> { return 15;}));}
}
