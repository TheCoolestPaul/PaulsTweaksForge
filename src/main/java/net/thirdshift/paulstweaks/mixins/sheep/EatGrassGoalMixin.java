package net.thirdshift.paulstweaks.mixins.sheep;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.SheepEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EatGrassGoal.class)
public abstract class EatGrassGoalMixin extends Goal {
	@Shadow @Final
	private MobEntity mob;

	@Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
	public void canUse(CallbackInfoReturnable<Boolean> info){
		if (this.mob instanceof SheepEntity)
			if ( !((SheepEntity)this.mob).isSheared() )
				info.setReturnValue(false);
	}
}
