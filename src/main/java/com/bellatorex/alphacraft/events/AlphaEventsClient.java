package com.bellatorex.alphacraft.events;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.client.gui.SmelterScreen;
import com.bellatorex.alphacraft.util.AlphaContainerRegistry;
import com.bellatorex.alphacraft.util.AlphaDimensionsRegistry;
import com.bellatorex.alphacraft.world.structure.AlphaIslandStructures;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AlphaCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class AlphaEventsClient {
    @SubscribeEvent
    public static void whenTPToTheAlpha(EntityTravelToDimensionEvent event){
        if(event.getDimension() == AlphaDimensionsRegistry.the_alpha_w){
            World serverworld = event.getEntity().world;
            MinecraftServer server = serverworld.getServer();
            ServerWorld destination = server.getWorld(AlphaDimensionsRegistry.the_alpha_w);

            AlphaIslandStructures.spawnPlatform(destination);
            //spawns return portal
            AlphaIslandStructures.spawnReturnPortal(destination);

            String msg = TextFormatting.BLUE + "Welcome to the Alpha";
            event.getEntity().sendMessage(new StringTextComponent(msg), event.getEntity().getUniqueID());
            AlphaCraft.LOGGER.debug("Teleport to alpha event fired!");
        }
    }
}
