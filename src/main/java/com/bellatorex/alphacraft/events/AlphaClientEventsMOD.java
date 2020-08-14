package com.bellatorex.alphacraft.events;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.client.gui.SmelterScreen;
import com.bellatorex.alphacraft.util.AlphaContainerRegistry;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AlphaCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AlphaClientEventsMOD {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){

    }
}
