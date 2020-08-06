package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.Blocks.BlockItemBase;
import com.bellatorex.alphacraft.Blocks.CastleWallBlock;
import com.bellatorex.alphacraft.Items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlphaCraft.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AlphaCraft.MOD_ID);



    public static void init() {

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    //Items
    public static final RegistryObject<Item> BALL = ITEMS.register("ball", ItemBase::new);
    public static final RegistryObject<Item> ENDERITE_INGOT = ITEMS.register("enderite_ingot", ItemBase::new);

    //Blocks
    public static final RegistryObject<Block> CASTLE_WALL_BLOCK = BLOCKS.register("castle_wall_block", CastleWallBlock::new);

    //Block Items
    public static final RegistryObject<Item> CASTLE_WALL_BLOCK_ITEM = ITEMS.register("castle_wall_block", () -> new BlockItemBase(CASTLE_WALL_BLOCK.get()));
}
