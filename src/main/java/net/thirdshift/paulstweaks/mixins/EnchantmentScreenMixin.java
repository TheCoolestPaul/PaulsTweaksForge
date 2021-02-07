package net.thirdshift.paulstweaks.mixins;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Mixin(EnchantmentScreen.class)
public abstract class EnchantmentScreenMixin extends ContainerScreen<EnchantmentContainer> {
	@Shadow
	@Final
	private Random random;

	@Shadow
	private ItemStack last;

	public EnchantmentScreenMixin(EnchantmentContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Inject(method = "render", locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target =
			"Lnet/minecraft/client/gui/screen/EnchantmentScreen;func_243308_b(Lcom/mojang/blaze3d/matrix/MatrixStack;Ljava/util/List;II)V"))
	public void fullText(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci, boolean bl, int i, int j, int k, Enchantment enchantment, int l, int m, List<ITextComponent> list){
		list.remove(0);
		list.addAll(0,
				this.generateEnchantments(j, k).stream().map(e -> e.enchantment.getDisplayName(e.enchantmentLevel)).collect(Collectors.toList()));
	}

	private List<EnchantmentData> generateEnchantments(int slot, int level) {
		this.random.setSeed(this.container.func_217005_f() + slot);
		List<EnchantmentData> list = EnchantmentHelper.buildEnchantmentList(this.random, last, level, false);
		if (last.getItem() == Items.BOOK && list.size() > 1) {
			list.remove(this.random.nextInt(list.size()));
		}

		return list;
	}

}
