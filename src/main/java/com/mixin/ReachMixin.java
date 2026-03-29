package com.hereisa.client.mixin;

import com.hereisa.client.HereisaClient;
import com.hereisa.client.core.Module;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class ReachMixin {
    @Inject(method = "getEntityInteractionRange", at = @At("HEAD"), cancellable = true)
    private void onGetInteractionRange(CallbackInfoReturnable<Double> cir) {
        Module reach = HereisaClient.moduleManager.getModules().stream()
                .filter(m -> m.getName().equals("Reach"))
                .findFirst().orElse(null);

        if (reach != null && reach.isEnabled()) {
            cir.setReturnValue((double) reach.reachValue);
        }
    }
}
