package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

public class AlphaFlowers extends FlowerBlock {
    public AlphaFlowers() {
        super(Effects.GLOWING, 30, AbstractBlock.Properties.create(Material.TALL_PLANTS)
                .doesNotBlockMovement()
                .zeroHardnessAndResistance()
                .sound(SoundType.PLANT)
                .setLightLevel((p_235470_0_) -> { return 10;}));
    }
}
