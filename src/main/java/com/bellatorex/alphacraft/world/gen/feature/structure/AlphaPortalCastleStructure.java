package com.bellatorex.alphacraft.world.gen.feature.structure;

import com.bellatorex.alphacraft.AlphaCraft;
import com.mojang.serialization.Codec;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class AlphaPortalCastleStructure extends Structure<NoFeatureConfig> {

    public AlphaPortalCastleStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    /*
     * This is how the worldgen code will start the generation of our structure when it passes the checks.
     */
    public boolean func_230363_a_(ChunkGenerator chunkGen, BiomeProvider biomeManager, long seedModifier, SharedSeedRandom rand, int chunkPosX, int chunkPosZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig config) {
        // This is very similar to the code for making pillager outposts unable to spawn near villages.
        /*
        int i = chunkPosX >> 4;
        int j = chunkPosZ >> 4;
        rand.setSeed((long) (i ^ j << 4) ^ seedModifier);
        for (int k = chunkPosX - 10; k <= chunkPosX + 10; ++k) {
            for (int l = chunkPosZ - 10; l <= chunkPosZ + 10; ++l) {
                ChunkPos chunkpos = Structure.field_236381_q_.func_236392_a_(chunkGen.func_235957_b_().func_236197_a_(Structure.field_236381_q_), seedModifier,
                        rand, k, l);
                if (k == chunkpos.x && l == chunkpos.z) {
                    return false;
                }
            }
        }*/
        return true;
    }

    @Override
    public Structure.IStartFactory getStartFactory() {
        return AlphaPortalCastleStructure.Start::new;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void func_230364_a_(ChunkGenerator generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, IFeatureConfig config) {
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];

            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            // Finds the y value of the terrain at location.
            int surfaceY = 63;
            surfaceY = surfaceY - (rand.nextInt(32)+16);

            BlockPos blockpos = new BlockPos(x, surfaceY, z);
            AlphaPortalCastlePieces.addPieces(generator, templateManagerIn, blockpos, this.components, this.rand);

AlphaCraft.LOGGER.debug("checking if the components of the structure is null or not structure:    "+this.components);

            this.recalculateStructureSize();
        }
    }
}
