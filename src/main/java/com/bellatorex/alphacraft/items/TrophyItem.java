package com.bellatorex.alphacraft.items;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.blocks.AlphaPortalFrameBlock;
import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TrophyItem extends Item {
    public TrophyItem() {
        super(new Item.Properties().group(AlphaCraft.ALPHA_ITEMS));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.isIn(BlockRegistry.PORTAL_FRAME.get()) && !blockstate.get(AlphaPortalFrameBlock.EYE)) {
            if (world.isRemote) {
                return ActionResultType.SUCCESS;
            } else {
                BlockState blockstate1 = blockstate.with(AlphaPortalFrameBlock.EYE, Boolean.valueOf(true));
                Block.nudgeEntitiesWithNewState(blockstate, blockstate1, world, blockpos);
                world.setBlockState(blockpos, blockstate1, 2);
                world.updateComparatorOutputLevel(blockpos, BlockRegistry.PORTAL_FRAME.get());
                context.getItem().shrink(1);
                world.playEvent(1503, blockpos, 0);
                BlockPattern.PatternHelper blockpattern$patternhelper = AlphaPortalFrameBlock.getOrCreatePortalShape().match(world, blockpos);
                if (blockpattern$patternhelper != null) {
                    BlockPos blockpos1 = blockpattern$patternhelper.getFrontTopLeft().add(-3, 0, -3);

                    for(int i = 0; i < 3; ++i) {
                        for(int j = 0; j < 3; ++j) {
                            world.setBlockState(blockpos1.add(i, 0, j), BlockRegistry.ALPHA_PORTAL_BLOCK.get().getDefaultState(), 2);
                        }
                    }

                    world.playBroadcastSound(1038, blockpos1.add(1, 0, 1), 0);
                }

                return ActionResultType.CONSUME;
            }
        } else {
            return ActionResultType.PASS;
        }
    }
}
