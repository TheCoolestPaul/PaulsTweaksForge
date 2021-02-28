package net.thirdshift.paulstweaks.event;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.thirdshift.paulstweaks.enchantment.NetherMendingEnchant;
import net.thirdshift.paulstweaks.enchantment.StoneMendingEnchantment;

import static net.thirdshift.paulstweaks.PaulsTweaksMod.*;

public class EventListener {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event){
		if (event.getPlayer()!=null && event.getPlayer().getHeldItemMainhand().isEnchanted()){
			int stoneLevel = EnchantmentHelper.getEnchantmentLevel(STONE_MENDING.get(), event.getPlayer().getHeldItemMainhand());
			int netherLevel = EnchantmentHelper.getEnchantmentLevel(NETHER_MENDING.get(), event.getPlayer().getHeldItemMainhand());
			if (event.getState().isIn(BlockTags.BASE_STONE_OVERWORLD) && stoneLevel > 0) {
				StoneMendingEnchantment.stoneMend(event.getPlayer().getHeldItemMainhand(), stoneLevel);
			} else if (event.getState().isIn(BlockTags.BASE_STONE_NETHER) && netherLevel > 0)
				NetherMendingEnchant.netherMend(event.getPlayer().getHeldItemMainhand(), netherLevel);
		}
	}

	@SubscribeEvent
	public static void onAnvilUpdate(AnvilUpdateEvent event){
		ItemStack item = event.getLeft();
		if (item.isEnchanted()) {
			int level = EnchantmentHelper.getEnchantmentLevel(CHEAPSKATE.get(), item);
			switch (level) {
				case 1:
					event.setCost((int) (event.getCost() - (event.getCost() * 0.25)));
					event.setMaterialCost((int) (event.getMaterialCost() - (event.getMaterialCost() * 0.25)));
				case 2:
					event.setCost((int) (event.getCost() - (event.getCost() * 0.50)));
					event.setMaterialCost((int) (event.getMaterialCost() - (event.getMaterialCost() * 0.25)));
				case 3:
					event.setCost((int) (event.getCost() - (event.getCost() * 0.75)));
					event.setMaterialCost((int) (event.getMaterialCost() - (event.getMaterialCost() * 0.75)));
				case 4:
					event.setCost(0);
					event.setMaterialCost(1);
			}
		}
	}
}
