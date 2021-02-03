package net.thirdshift.paulstweaks.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public final class StoneMendingEnchantment extends Enchantment {
	public StoneMendingEnchantment() {
		super(Rarity.RARE, EnchantmentType.DIGGER, new EquipmentSlotType[]{ EquipmentSlotType.MAINHAND });
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

	public static void stoneMend(ItemStack item, int mendLevel){
		switch (mendLevel){
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
		item.setDamage(item.getDamage()-2);
	}
}
