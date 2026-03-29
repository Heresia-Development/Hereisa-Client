package com.hereisa.client.modules.movement;

import com.hereisa.client.core.Category;
import com.hereisa.client.core.Module;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;

public class BoatFly extends Module {
    private final double speed = 0.6;

    public BoatFly() {
        super("BoatFly", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (mc.player != null && mc.player.getVehicle() instanceof BoatEntity boat) {
            Vec3d velocity = boat.getVelocity();
            double y = 0;
            if (mc.options.jumpKey.isPressed()) y = speed;
            else if (mc.options.sneakKey.isPressed()) y = -speed;

            boat.setVelocity(velocity.x, y, velocity.z);
        }
    }
}
