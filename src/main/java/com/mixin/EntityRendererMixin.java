package com.hereisa.client.mixin;

import com.hereisa.client.HereisaClient;
import com.hereisa.client.modules.render.XRay;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class EntityRendererMixin {
    @Inject(method = "shouldDrawSide", at = @At("HEAD"), cancellable = true)
    private static void onShouldDrawSide(BlockState state, BlockView world, BlockPos pos, net.minecraft.util.math.Direction side, BlockPos otherPos, CallbackInfoReturnable<Boolean> cir) {
        if (HereisaClient.moduleManager.getModules().stream().anyMatch(m -> m.getName().equals("XRay") && m.isEnabled())) {
            if (!XRay.ORES.contains(state.getBlock())) {
                cir.setReturnValue(false);
            } else {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "getAmbientOcclusionLightLevel", at = @At("HEAD"), cancellable = true)
    private void onGetAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (HereisaClient.moduleManager.getModules().stream().anyMatch(m -> m.getName().equals("XRay") && m.isEnabled())) {
            cir.setReturnValue(1.0f);
        }
    }
}
