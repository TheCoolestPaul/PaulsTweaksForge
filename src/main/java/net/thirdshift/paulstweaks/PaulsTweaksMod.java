package net.thirdshift.paulstweaks;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.thirdshift.paulstweaks.enchantment.NetherMendingEnchant;
import net.thirdshift.paulstweaks.enchantment.StoneMendingEnchantment;
import net.thirdshift.paulstweaks.event.BlockBreakEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("paulstweaks")
public class PaulsTweaksMod {
	public final static String MOD_ID = "paulstweaks";
	public static Logger LOGGER = LogManager.getLogger();

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
	public static RegistryObject<Enchantment> STONE_MENDING = ENCHANTMENTS.register("stone_mending", StoneMendingEnchantment::new);
	public static RegistryObject<Enchantment> NETHER_MENDING = ENCHANTMENTS.register("nether_mending", NetherMendingEnchant::new);

	public PaulsTweaksMod() {
		MinecraftForge.EVENT_BUS.register(BlockBreakEvent.class);
		LOGGER.debug("Registered into BlockBreakEvent");
		ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
		LOGGER.debug("Registered enchantments.");
	}

}
