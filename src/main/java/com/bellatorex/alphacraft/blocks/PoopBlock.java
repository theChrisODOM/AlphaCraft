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
                .hardnessAndResistance(5.0f, 6.0f)
                .sound(SoundType.GROUND)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE)
                .setRequiresTool());
    }
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.onLivingFall(fallDistance, 0.2F);
    }
}