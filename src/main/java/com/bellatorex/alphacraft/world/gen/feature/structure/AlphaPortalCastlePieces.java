package com.bellatorex.alphacraft.world.gen.feature.structure;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.util.AlphacraftBiomesManager;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.*;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class AlphaPortalCastlePieces {

    public static void addPieces(ChunkGenerator chunkGeneratorIn, TemplateManager templateManagerIn, BlockPos posIn, List<StructurePiece> structurePieces, SharedSeedRandom p_215139_4_) {
        init();
        JigsawManager.func_236823_a_(new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/portal"), 7, AlphaPortalCastlePieces.AlphaPortalCastle::new, chunkGeneratorIn, templateManagerIn, posIn, structurePieces, p_215139_4_, false, false);
    }

    public static void init() {
    }

    static {
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/portal"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/portal/portal"), 7)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/hall"), new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/terminator"), ImmutableList.of(Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/hall/cross_01"), 3), Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/hall/cross_02"), 1), Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/hall/hall_01"), 4), Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/hall/hall_02"), 2), Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/hall/loot_hall_01"), 2), Pair.of(EmptyJigsawPiece.INSTANCE, 2)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/loot"), new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/terminator"), ImmutableList.of(Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/loot/loot_01"), 2), Pair.of(EmptyJigsawPiece.INSTANCE, 2)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/terminator"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/terminator/terminator_01"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
    }

    public static class AlphaPortalCastle extends AbstractVillagePiece {
        public AlphaPortalCastle(TemplateManager templateManagerIn, JigsawPiece jigsawPieceIn, BlockPos posIn, int p_i50560_4_, Rotation rotationIn, MutableBoundingBox boundsIn) {
            super(AlphacraftBiomesManager.APCP, templateManagerIn, jigsawPieceIn, posIn, p_i50560_4_, rotationIn, boundsIn);
        }

        public AlphaPortalCastle(TemplateManager templateManagerIn, CompoundNBT nbt) {
            super(templateManagerIn, nbt, AlphacraftBiomesManager.APCP);
        }
        /*
         * If you added any data marker structure blocks to your structure, you can access and modify them here. In this case, our structure has a data maker
         * with the string "chest" put into it. So we check to see if the incoming function is "chest" and if it is, we now have that exact position.
         *
         * So what is done here is we replace the structure block with a chest and we can then set the loottable for it.
         *
         * You can set other data markers to do other behaviors such as spawn a random mob in a certain spot, randomize what rare block spawns under the floor,
         * or what item an Item Frame will have.
        */

        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
            if (function.startsWith("Chest")) {
                BlockPos blockpos = pos.down();
                if (sbb.isVecInside(blockpos)) {
                    LockableLootTileEntity.setLootTable(worldIn, rand, blockpos, new ResourceLocation(AlphaCraft.MOD_ID+":loot_tables/dungeon_01"));
                }
            }
        }
    }
}
