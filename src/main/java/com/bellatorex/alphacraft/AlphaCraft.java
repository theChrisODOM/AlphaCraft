package com.bellatorex.alphacraft;

import com.bellatorex.alphacraft.client.gui.*;
import com.bellatorex.alphacraft.util.*;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "alphacraft";

    public AlphaCraft() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        modEventBus.addListener(EventPriority.LOWEST, this::commonSetup);


        // All mod registry initializations
        BaseItemRegistry.init();
        BlockRegistry.init();
        AlphaTileEntityRegistry.init();
        AlphaContainerRegistry.init();
        ToolRegistry.init();
        ArmorRegistry.init();
        RecipeSerializerRegistry.init();
        AlphacraftBiomesManager.init();


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) { AlphacraftBiomesManager.setupBiomes(); }

    private void setup(final FMLCommonSetupEvent event) {
        AlphacraftBiomesManager.setupBiomes();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlockRegistry.NASTY_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.DARK_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.NASTY_TREE_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.DARK_TREE_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.DARK_GRASS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockRegistry.BRIGHT_FLOWER.get(), RenderType.getCutout());

        ScreenManager.registerFactory(AlphaContainerRegistry.SMELTER.get(), SmelterScreen::new);
        ScreenManager.registerFactory(AlphaContainerRegistry.DARK_CHEST.get(), DarkChestScreen::new);
    }
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {
            AlphacraftBiomesManager.registerFeatures(event);
        }
    }
    public static final ItemGroup ALPHA_BLOCKS = new ItemGroup("alphaCraftBlocksTab") {@Override public ItemStack createIcon() { return new ItemStack(BlockRegistry.DARK_GRASS_BLOCK.get()); }};
    public static final ItemGroup ALPHA_DECOR = new ItemGroup("alphaCraftDecorTab") {@Override public ItemStack createIcon() { return new ItemStack(BlockRegistry.DARK_LEAVES.get()); }};
    public static final ItemGroup ALPHA_TOOLS = new ItemGroup("alphaCraftToolsTab") {@Override public ItemStack createIcon() { return new ItemStack(ToolRegistry.ENDERITE_AXE.get()); }};
    public static final ItemGroup ALPHA_ITEMS = new ItemGroup("alphaCraftItemsTab") {@Override public ItemStack createIcon() { return new ItemStack(BaseItemRegistry.ENDERITE_INGOT.get()); }};

}


