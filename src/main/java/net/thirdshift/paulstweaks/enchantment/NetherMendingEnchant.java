package net.thirdshift.paulstweaks.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class NetherMendingEnchant extends Enchantment {

	public NetherMendingEnchant() {
		super(Rarity.RARE, EnchantmentType.DIGGER, new EquipmentSlotType[]{ EquipmentSlotType.MAINHAND });
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

	public static void netherMend(ItemStack item, int mendLevel){
		switch (mendLevel){
			case 0:
				return;
			case 1:
				if(Math.random() > 0.25)
					return;
			case 2:
				if (Math.random() > 0.50)
					return;
			case 3:
				if (Math.random() > 0.75)
					return;
		}
		item.setDamageValue(item.getDamageValue()-2);
	}
}
