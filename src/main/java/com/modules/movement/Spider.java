package com.hereisa.client.modules.movement;

import com.hereisa.client.core.Category;
import com.hereisa.client.core.Module;
import net.minecraft.util.math.Vec3d;

public class Spider extends Module {
    public Spider() {
        super("Spider", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (mc.player != null && mc.player.horizontalCollision) {
            Vec3d velocity = mc.player.getVelocity();
            mc.player.setVelocity(velocity.x, 0.25, velocity.z);
        }
    }
}
