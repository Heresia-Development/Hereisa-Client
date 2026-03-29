package com.hereisa.client.modules.combat;

import com.hereisa.client.core.Category;
import com.hereisa.client.core.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class KillAura extends Module {
    private final double range = 4.0;

    public KillAura() {
        super("KillAura", Category.COMBAT);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;

        for (Entity target : mc.world.getEntities()) {
            if (target instanceof PlayerEntity && target != mc.player && target.isAlive()) {
                if (mc.player.distanceTo(target) <= range) {
                    mc.interactionManager.attackEntity(mc.player, target);
                    mc.player.swingHand(Hand.MAIN_HAND);
                    break;
                }
            }
        }
    }
}
