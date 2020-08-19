package com.bellatorex.alphacraft.blocks;

import com.bellatorex.alphacraft.util.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BrightSand extends SandBlock{


    public BrightSand(int dustColorIn, Properties properties) {
        super(dustColorIn, properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
        BlockState plant = plantable.getPlant(world, pos.offset(facing));
        net.minecraftforge.common.PlantType type = plantable.getPlantType(world, pos.offset(facing));

        if (plant.getBlock() == Blocks.CACTUS)
            return state.isIn(Blocks.CACTUS) || state.isIn(Blocks.SAND) || state.isIn(Blocks.RED_SAND) || state.isIn(BlockRegistry.BRIGHT_SAND.get());

        if (plant.getBlock() == Blocks.SUGAR_CANE && this == Blocks.SUGAR_CANE)
            return true;

        if (net.minecraftforge.common.PlantType.DESERT.equals(type)) {
            return this.getBlock() == Blocks.SAND || this.getBlock() == Blocks.TERRACOTTA || this.getBlock() instanceof GlazedTerracottaBlock;
        } else if (net.minecraftforge.common.PlantType.NETHER.equals(type)) {
            return this.getBlock() == Blocks.SOUL_SAND;
        } else if (net.minecraftforge.common.PlantType.CROP.equals(type)) {
            return state.isIn(Blocks.FARMLAND);
        } else if (net.minecraftforge.common.PlantType.CAVE.equals(type)) {
            return Block.hasSolidSide(state, world, pos, Direction.UP);
        } else if (net.minecraftforge.common.PlantType.PLAINS.equals(type)) {
            return this.getBlock() == Blocks.GRASS_BLOCK || net.minecraftforge.common.Tags.Blocks.DIRT.contains(this) || this.getBlock() == Blocks.FARMLAND;
        } else if (net.minecraftforge.common.PlantType.WATER.equals(type)) {
            return state.getMaterial() == net.minecraft.block.material.Material.WATER; //&& state.getValue(BlockLiquidWrapper)
        } else if (net.minecraftforge.common.PlantType.BEACH.equals(type)) {
            boolean isBeach = state.isIn(Blocks.GRASS_BLOCK) || net.minecraftforge.common.Tags.Blocks.DIRT.contains(this) || state.isIn(Blocks.SAND) || state.isIn(Blocks.RED_SAND);
            boolean hasWater = false;
            for (Direction face : Direction.Plane.HORIZONTAL) {
                BlockState blockState = world.getBlockState(pos.offset(face));
                net.minecraft.fluid.FluidState fluidState = world.getFluidState(pos.offset(face));
                hasWater |= blockState.isIn(Blocks.FROSTED_ICE);
                hasWater |= fluidState.isTagged(net.minecraft.tags.FluidTags.WATER);
                if (hasWater)
                    break; //No point continuing.
            }
            return isBeach && hasWater;
        }
        return false;
    }
}
