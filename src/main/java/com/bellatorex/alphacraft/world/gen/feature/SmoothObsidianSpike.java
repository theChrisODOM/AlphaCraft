package com.bellatorex.alphacraft.world.gen.feature;

import com.bellatorex.alphacraft.util.BlockRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class SmoothObsidianSpike extends Feature<NoFeatureConfig> {
    public SmoothObsidianSpike(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean func_230362_a_(ISeedReader worldIn, StructureManager structureManager, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        while (worldIn.isAirBlock(pos) && pos.getY() > 2) {
            pos = pos.down();
        }
        if (!worldIn.getBlockState(pos).isIn(BlockRegistry.BRIGHT_SAND.get()) && !worldIn.getBlockState(pos).isIn(BlockRegistry.ASH.get())) {
            return false;
        }else{
            pos = pos.up(rand.nextInt(4));
            int spikeHeight = 9;
            int j = spikeHeight / 4 + rand.nextInt(2);


            for(int k = 0; k < spikeHeight; ++k) {
                float f = (1.0F - (float)k / (float)spikeHeight) * (float)j;
                int l = MathHelper.ceil(f);

                for(int i1 = -l; i1 <= l; ++i1) {
                    float f1 = (float)MathHelper.abs(i1) - 0.25F;

                    for(int j1 = -l; j1 <= l; ++j1) {
                        float f2 = (float)MathHelper.abs(j1) - 0.25F;
                        if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(rand.nextFloat() > 0.75F))) {
                            BlockState currentBlockState = worldIn.getBlockState(pos.add(i1, k, j1));
                            Block block = currentBlockState.getBlock();
                            BlockPos topBlock = new BlockPos(pos.getX(), pos.getY() + spikeHeight, pos.getZ()); //
                            if (currentBlockState.isAir(worldIn, pos.add(i1, k, j1)) || isDirt(block) || block == BlockRegistry.BRIGHT_SAND.get() || block == BlockRegistry.ASH.get()) {
                                this.func_230367_a_(worldIn, pos.add(i1, k, j1), BlockRegistry.SMOOTH_OBSIDIAN.get().getDefaultState());
                            }
                            if(rand.nextInt(10) > 1){
                                this.func_230367_a_(worldIn, topBlock, Blocks.OBSIDIAN.getDefaultState());
                            }else {
                                this.func_230367_a_(worldIn, topBlock, Blocks.CRYING_OBSIDIAN.getDefaultState());
                            }
                            if (k != 0 && l > 1) {
                                currentBlockState = worldIn.getBlockState(pos.add(i1, -k, j1));
                                block = currentBlockState.getBlock();
                                if (currentBlockState.isAir(worldIn, pos.add(i1, -k, j1)) || isDirt(block) || block == BlockRegistry.BRIGHT_SAND.get() || block == BlockRegistry.ASH.get()) {
                                    this.func_230367_a_(worldIn, pos.add(i1, -k, j1), BlockRegistry.SMOOTH_OBSIDIAN.get().getDefaultState());
                                }
                            }
                        }
                    }
                }
            }

            int k1 = j - 1;
            if (k1 < 0) {
                k1 = 0;
            } else if (k1 > 1) {
                k1 = 1;
            }

            for(int l1 = -k1; l1 <= k1; ++l1) {
                for(int i2 = -k1; i2 <= k1; ++i2) {
                    BlockPos blockpos = pos.add(l1, -1, i2);
                    BlockState blockstate1 = worldIn.getBlockState(blockpos);
                    Block block1 = blockstate1.getBlock();
                    if (!blockstate1.isAir(worldIn, blockpos) && block1 != BlockRegistry.ASH.get() && block1 != BlockRegistry.BRIGHT_SAND.get() && block1 != BlockRegistry.SMOOTH_OBSIDIAN.get()) {
                        break;
                    }

                    worldIn.setBlockState(blockpos, BlockRegistry.SMOOTH_OBSIDIAN.get().getDefaultState(), 1);
                }
            }
            return true;
        }
    }
}
