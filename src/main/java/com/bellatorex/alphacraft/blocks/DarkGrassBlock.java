package com.bellatorex.alphacraft.blocks;

import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

public class DarkGrassBlock extends SpreadableSnowyDirtBlock implements IGrowable {

    public DarkGrassBlock() {
        super(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK));
    }
    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return worldIn.getBlockState(pos.up()).isAir();
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos currentBlockPos, BlockState state) {
        BlockPos blockAbovePos = currentBlockPos.up();
        BlockState grassBlockState = BlockRegistry.DARK_GRASS.get().getDefaultState(); // <--- this is the actually grass that grows, not the grass block

        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockAbovePos;
            int j = 0;

            while(true) {
                if (j >= i / 16) {
                    BlockState blockstate2 = worldIn.getBlockState(blockpos1);
                    // if the block pos we are attempting to grow already has grass on it, then grow it to tall grass
                    if (blockstate2.isIn(grassBlockState.getBlock()) && rand.nextInt(10) == 0) {
                        ((IGrowable)grassBlockState.getBlock()).grow(worldIn, rand, blockpos1, blockstate2);
                    }
                    // if block above is not air then do nothing
                    if (!blockstate2.isAir()) {
                        break;
                    }
                    // 1 in 8 chance to grow a flower
                    BlockState blockstate1;
                    if (rand.nextInt(8) == 0) {
                        List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).getFlowers();
                        if (list.isEmpty()) {
                            break;
                        }

                        ConfiguredFeature<?, ?> configuredfeature = ((DecoratedFeatureConfig)(list.get(0)).config).feature;
                        blockstate1 = ((FlowersFeature)configuredfeature.feature).getFlowerToPlace(rand, blockpos1, configuredfeature.config);
                    } else {
                        // 7 in 8 chance to grow grass
                        blockstate1 = grassBlockState;
                    }

                    if (blockstate1.isValidPosition(worldIn, blockpos1)) {
                        worldIn.setBlockState(blockpos1, blockstate1, 3);
                    }
                    break;
                }
                // fetch next grass_block to attempt to grow more stuff on
                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (!worldIn.getBlockState(blockpos1.down()).isIn(this) || worldIn.getBlockState(blockpos1).hasOpaqueCollisionShape(worldIn, blockpos1)) {
                    break;
                }

                ++j;
            }
        }

    }
    /**
     * Performs a random tick on a block.  Overrides snowy spreadable so dark grass only grows onto and despawns into dark dirt
     */
    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!isSnowyConditions(state, worldIn, pos)) {
            if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            worldIn.setBlockState(pos, BlockRegistry.DARK_DIRT_BLOCK.get().getDefaultState());
        } else {
            if (worldIn.getLight(pos.up()) >= 9) {
                BlockState blockstate = this.getDefaultState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (worldIn.getBlockState(blockpos).isIn(BlockRegistry.DARK_DIRT_BLOCK.get()) && isSnowyAndNotUnderwater(blockstate, worldIn, blockpos)) {
                        worldIn.setBlockState(blockpos, blockstate.with(SNOWY, Boolean.valueOf(worldIn.getBlockState(blockpos.up()).isIn(Blocks.SNOW))));
                    }
                }
            }

        }
    }
    private static boolean isSnowyConditions(BlockState state, IWorldReader worldReader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = worldReader.getBlockState(blockpos);
        if (blockstate.isIn(Blocks.SNOW) && blockstate.get(SnowBlock.LAYERS) == 1) {
            return true;
        } else if (blockstate.getFluidState().getLevel() == 8) {
            return false;
        } else {
            int i = LightEngine.func_215613_a(worldReader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(worldReader, blockpos));
            return i < worldReader.getMaxLightLevel();
        }
    }

    private static boolean isSnowyAndNotUnderwater(BlockState state, IWorldReader worldReader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return isSnowyConditions(state, worldReader, pos) && !worldReader.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }
}
