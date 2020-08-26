package com.bellatorex.alphacraft.world.gen.feature.structure;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.util.AlphacraftBiomesManager;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.*;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;

public class AlphaPortalCastlePieces {

    public static void addPieces(ChunkGenerator chunkGeneratorIn, TemplateManager templateManagerIn, BlockPos posIn, List<StructurePiece> structurePieces, SharedSeedRandom p_215139_4_) {
        init();
        JigsawManager.func_236823_a_(new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/portal"), 7, AlphaPortalCastlePieces.AlphaPortalCastle::new, chunkGeneratorIn, templateManagerIn, posIn, structurePieces, p_215139_4_, false, false);
    }

    public static void init() {
    }

    static {
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/portal"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/portal/portal"), 7)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/hall"), new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/terminator"), ImmutableList.of(Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/hall/cross_01"), 2), Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/hall/cross_02"), 2), Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/hall/hall_01"), 2), Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/hall/hall_02"), 2), Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/hall/loot_hall_01"), 2), Pair.of(EmptyJigsawPiece.INSTANCE, 6)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/loot"), new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/terminator"), ImmutableList.of(Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/hall/loot_01"), 2), Pair.of(EmptyJigsawPiece.INSTANCE, 6)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(AlphaCraft.MOD_ID+":dungeon_01/terminator"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new LegacySingleJigsawPiece(AlphaCraft.MOD_ID+":dungeon_01/terminator/terminator_01"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
    }

    public static class AlphaPortalCastle extends AbstractVillagePiece {
        public AlphaPortalCastle(TemplateManager templateManagerIn, JigsawPiece jigsawPieceIn, BlockPos posIn, int p_i50560_4_, Rotation rotationIn, MutableBoundingBox boundsIn) {
            super(AlphacraftBiomesManager.APCP, templateManagerIn, jigsawPieceIn, posIn, p_i50560_4_, rotationIn, boundsIn);
        }

        public AlphaPortalCastle(TemplateManager templateManagerIn, CompoundNBT nbt) {
            super(templateManagerIn, nbt, AlphacraftBiomesManager.APCP);
        }
    }
}
