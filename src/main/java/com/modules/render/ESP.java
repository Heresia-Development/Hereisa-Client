package com.hereisa.client.modules.render;

import com.hereisa.client.core.Category;
import com.hereisa.client.core.Module;
import com.hereisa.client.util.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class ESP extends Module {
    public ESP() {
        super("ESP", Category.RENDER);
    }

    public void onRender2D(DrawContext context, float tickDelta) {
        if (!this.isEnabled() || mc.world == null || mc.player == null) return;

        EntityRenderDispatcher dispatcher = mc.getEntityRenderDispatcher();

        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof PlayerEntity && entity != mc.player && entity.isAlive()) {
                PlayerEntity player = (PlayerEntity) entity;
                
                int screenX = 100;
                int screenY = 100;
                int boxWidth = 50;
                int boxHeight = 80;
                int boxColor = 0xFF00AAFF; 

                context.drawBorder(screenX, screenY, boxWidth, boxHeight, boxColor);

                float health = player.getHealth();
                float maxHealth = player.getMaxHealth();
                int healthBarColor = RenderUtils.getHealthColor(health, maxHealth);
                
                int barWidth = 4;
                int barHeight = (int) (boxHeight * (health / maxHealth));
                int barX = screenX - barWidth - 2; 
                int barY = screenY + (boxHeight - barHeight); 

                context.fill(barX, screenY, barX + barWidth, screenY + boxHeight, 0xBB222222);
                context.fill(barX, barY, barX + barWidth, screenY + boxHeight, healthBarColor);

                String name = player.getName().getString();
                
                int nameColor = 0xFFFFFFFF; 
                int nameX = screenX + (boxWidth / 2);
                int nameY = screenY - 12;
                
                context.drawCenteredTextWithShadow(mc.textRenderer, Text.literal(name), nameX, nameY, nameColor);
            }
        }
    }
}
