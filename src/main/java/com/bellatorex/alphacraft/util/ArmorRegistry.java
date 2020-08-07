package com.bellatorex.alphacraft.util;

import com.bellatorex.alphacraft.AlphaCraft;
import com.bellatorex.alphacraft.armor.AlphaArmorMaterial;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ArmorRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlphaCraft.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //Armor
    public static final RegistryObject<ArmorItem> ENDERITE_HELMET = ITEMS.register("enderite_helmet", () ->
            new ArmorItem(AlphaArmorMaterial.ENDERITE, EquipmentSlotType.HEAD, new Item.Properties().group(AlphaCraft.TAB)));

    public static final RegistryObject<ArmorItem> ENDERITE_CHESTPLATE = ITEMS.register("enderite_chestplate", () ->
            new ArmorItem(AlphaArmorMaterial.ENDERITE, EquipmentSlotType.CHEST, new Item.Properties().group(AlphaCraft.TAB)));

    public static final RegistryObject<ArmorItem> ENDERITE_LEGGINGS = ITEMS.register("enderite_leggings", () ->
            new ArmorItem(AlphaArmorMaterial.ENDERITE, EquipmentSlotType.LEGS, new Item.Properties().group(AlphaCraft.TAB)));

    public static final RegistryObject<ArmorItem> ENDERITE_BOOTS = ITEMS.register("enderite_boots", () ->
            new ArmorItem(AlphaArmorMaterial.ENDERITE, EquipmentSlotType.FEET, new Item.Properties().group(AlphaCraft.TAB)));
}
