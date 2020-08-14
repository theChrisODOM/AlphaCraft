package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.tileentity.SmelterTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AlphaTileEntityRegistry {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, AlphaCraft.MOD_ID);

    public static void init() {
        TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<TileEntityType<SmelterTileEntity>> SMELTER = TILE_ENTITY_TYPES
            .register("smelter", ()->TileEntityType.Builder
                    .create(SmelterTileEntity::new, BlockRegistry.SMELTER.get()).build(null));
}
