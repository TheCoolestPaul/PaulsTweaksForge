package net.thirdshift.paulstweaks.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class NetherDustItem extends Item {
	public NetherDustItem() {
		super(new Item.Properties().group(ItemGroup.MISC).isImmuneToFire());
	}
}
