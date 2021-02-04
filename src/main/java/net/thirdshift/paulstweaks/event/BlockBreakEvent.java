package net.thirdshift.paulstweaks.event;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.thirdshift.paulstweaks.enchantment.NetherMendingEnchant;
import net.thirdshift.paulstweaks.enchantment.StoneMendingEnchantment;

import static net.thirdshift.paulstweaks.PaulsTweaksMod.NETHER_MENDING;
import static net.thirdshift.paulstweaks.PaulsTweaksMod.STONE_MENDING;

/**
 * Currently only used for StoneMending
 */
public class BlockBreakEvent {
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
}
