package com.bellatorex.alphacraft;

import com.bellatorex.alphacraft.util.*;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("alphacraft")
public class AlphaCraft
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "alphacraft";

    public AlphaCraft() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        modEventBus.addListener(EventPriority.LOWEST, this::commonSetup);

        AlphaBiomes.register(modEventBus);

        // All mod registry initializations
        BaseItemRegistry.init();
        ToolRegistry.init();
        ArmorRegistry.init();
        BlockRegistry.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        AlphaBiomes.setupBiomes();
    }

    private void setup(final FMLCommonSetupEvent event) {
        AlphaBiomes.setupBiomes();
    }

    private void doClientStuff(final FMLClientSetupEvent event) { }


    public static final ItemGroup TAB = new ItemGroup("alphaCraftTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockRegistry.DARK_GRASS_BLOCK.get());
        }
    };
}


