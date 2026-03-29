package com.hereisa.client.mixin;

import com.hereisa.client.HereisaClient;
import com.hereisa.client.modules.render.ESP;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render", at = @At("RETURN"))
    private void onRender(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        HereisaClient.moduleManager.getModules().stream()
            .filter(m -> m instanceof ESP)
            .map(m -> (ESP) m)
            .forEach(esp -> {
                if (esp.isEnabled()) {
                    esp.onRender2D(context, tickCounter.getTickDelta(false));
                }
            });
    }
}
