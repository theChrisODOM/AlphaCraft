package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

public class AlphaFlowers extends FlowerBlock {

    public AlphaFlowers(Effect effect, int effectDuration, Properties properties) {
        super(effect, effectDuration, properties);
    }
}
