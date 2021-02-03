package net.thirdshift.paulstweaks.event;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.thirdshift.paulstweaks.PaulsTweaksMod;
import net.thirdshift.paulstweaks.enchantment.StoneMendingEnchantment;

/**
 * Currently only used for StoneMending
 */
public class BlockBreakEvent {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event){
		if (event.getPlayer()!=null && event.getPlayer().getHeldItemMainhand().isEnchanted()){
			final int level = EnchantmentHelper.getEnchantmentLevel(PaulsTweaksMod.STONE_MENDING.get(), event.getPlayer().getHeldItemMainhand());
			if (level>0) {
				if (event.getState().isIn(BlockTags.BASE_STONE_OVERWORLD))
					StoneMendingEnchantment.stoneMend(event.getPlayer().getHeldItemMainhand(), level);
			}
		}
	}
}
