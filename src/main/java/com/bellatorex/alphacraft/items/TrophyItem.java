package com.bellatorex.alphacraft.items;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.blocks.AlphaPortalFrameBlock;
import com.bellatorex.alphacraft.util.AlphacraftBiomesManager;
import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.EyeOfEnderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;

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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.NONE);
        if (raytraceresult.getType() == RayTraceResult.Type.BLOCK && worldIn.getBlockState(((BlockRayTraceResult)raytraceresult).getPos()).isIn(BlockRegistry.PORTAL_FRAME.get())) {
            return ActionResult.resultPass(itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            if (worldIn instanceof ServerWorld) {
                BlockPos blockpos = ((ServerWorld)worldIn).getChunkProvider().getChunkGenerator().func_235956_a_((ServerWorld)worldIn, AlphacraftBiomesManager.ALPHA_PORTAL_CASTLE, playerIn.getPosition(), 100, false);
                if (blockpos != null) {
                    EyeOfEnderEntity eyeofenderentity = new EyeOfEnderEntity(worldIn, playerIn.getPosX(), playerIn.getPosYHeight(0.5D), playerIn.getPosZ());
                    eyeofenderentity.func_213863_b(itemstack);
                    eyeofenderentity.moveTowards(blockpos);
                    worldIn.addEntity(eyeofenderentity);
                    if (playerIn instanceof ServerPlayerEntity) {
                        CriteriaTriggers.USED_ENDER_EYE.trigger((ServerPlayerEntity)playerIn, blockpos);
                    }

                    worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                    worldIn.playEvent((PlayerEntity)null, 1003, playerIn.getPosition(), 0);
                    if (!playerIn.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }

                    playerIn.addStat(Stats.ITEM_USED.get(this));
                    playerIn.swing(handIn, true);
                    return ActionResult.resultSuccess(itemstack);
                }
            }

            return ActionResult.resultConsume(itemstack);
        }
    }
}
