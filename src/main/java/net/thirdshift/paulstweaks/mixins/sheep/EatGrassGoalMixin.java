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
	private MobEntity grassEaterEntity;

	@Inject(method = "shouldExecute", at = @At("HEAD"), cancellable = true)
	public void shouldExecute(CallbackInfoReturnable<Boolean> info){
		if (this.grassEaterEntity instanceof SheepEntity)
			if ( !((SheepEntity)this.grassEaterEntity).getSheared() )
				info.setReturnValue(false);
	}

}
