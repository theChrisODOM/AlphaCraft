package com.bellatorex.alphacraft.client.gui;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.containers.SmelterContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class SmelterScreen extends ContainerScreen<SmelterContainer> {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(AlphaCraft.MOD_ID, "textures/gui/containers/smelter.png");

    public SmelterScreen(SmelterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 175;
        this.ySize = 165;
    }

    @Override
    public void render(MatrixStack matrixStack,final int mouseX,final int mouseY,final float partialTicks) {
        AlphaCraft.LOGGER.debug("Starting Smelter Render");
        this.renderBackground(matrixStack);
        AlphaCraft.LOGGER.debug("Render Background complete");
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        AlphaCraft.LOGGER.debug("Super Render complete");
        this.func_230459_a_(matrixStack, mouseX, mouseY);
        AlphaCraft.LOGGER.debug("Render tooltips complete");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {

        AlphaCraft.LOGGER.debug(matrixStack.getLast());
        super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY);
        this.font.drawString(matrixStack, "Smelter", 8.0f,6.0f, 4210752);
        this.font.drawString(matrixStack, "Inventory", 8.0f,73.0f, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1,1,1,1);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int x = (this.width - this.xSize)/2;
        int y = (this.height - this.ySize)/2;
        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize);
    }
}
