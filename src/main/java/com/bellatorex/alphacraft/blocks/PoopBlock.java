package com.bellatorex.alphacraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class PoopBlock extends FallingBlock {

    public PoopBlock() {
        super(Block.Properties.create(Material.EARTH)
                .hardnessAndResistance(2.0f, 6.0f)
                .sound(SoundType.SLIME)
                .harvestLevel(0)
                .harvestTool(ToolType.SHOVEL));
    }
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.onLivingFall(fallDistance, 0.2F);
    }
}