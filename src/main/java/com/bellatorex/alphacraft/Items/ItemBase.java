package com.bellatorex.alphacraft.Items;

import com.bellatorex.alphacraft.AlphaCraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

    public ItemBase(){
        super(new Item.Properties().group(AlphaCraft.TAB));
    }

}
