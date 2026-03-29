package com.hereisa.client.mixin;

import com.hereisa.client.HereisaClient;
import com.hereisa.client.modules.render.XRay;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class BlockMixin {

    @Inject(method = "shouldDrawSide", at = @At("HEAD"), cancellable = true)
    private void onShouldDrawSide(BlockView world, BlockPos pos, Direction side, CallbackInfoReturnable<Boolean> cir) {
        boolean isXrayActive = HereisaClient.moduleManager.getModules().stream()
                .anyMatch(m -> m.getName().equalsIgnoreCase("XRay") && m.isEnabled());

        if (isXrayActive) {
            BlockState state = (BlockState) (Object) this;
            if (XRay.ORES != null && !XRay.ORES.contains(state.getBlock())) {
                cir.setReturnValue(false);
            } else {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "getAmbientOcclusionLightLevel", at = @At("HEAD"), cancellable = true)
    private void onGetAmbientOcclusionLightLevel(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        boolean isXrayActive = HereisaClient.moduleManager.getModules().stream()
                .anyMatch(m -> m.getName().equalsIgnoreCase("XRay") && m.isEnabled());

        if (isXrayActive) {
            cir.setReturnValue(1.0f);
        }
    }
}
