package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.block.material.Material;

public class TallDarkGrassBlock extends TallGrassBlock implements IGrowable, net.minecraftforge.common.IForgeShearable {
    public TallDarkGrassBlock() {
        super(AbstractBlock.Properties.create(Material.TALL_PLANTS)
                .doesNotBlockMovement()
                .zeroHardnessAndResistance()
                .sound(SoundType.PLANT));
    }
}
