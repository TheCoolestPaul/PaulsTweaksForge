package net.thirdshift.paulstweaks.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class CheapskateEnchant extends Enchantment {

	public CheapskateEnchant(){
		super(Rarity.VERY_RARE, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

}
