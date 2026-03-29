package com.hereisa.client.util;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public class RenderUtils {

    public static void drawOutlinedRectangle(DrawContext ctx, int x, int y, int w, int h, int color, int thickness) {
        ctx.fill(x, y, x + w, y + thickness, color);
        ctx.fill(x, y + h - thickness, x + w, y + h, color);
        ctx.fill(x, y, x + thickness, y + h, color);
        ctx.fill(x + w - thickness, y, x + w, y + h, color);
    }

    public static int getHealthColor(float health, float maxHealth) {
        float ratio = MathHelper.clamp(health / maxHealth, 0, 1);
        
        int r = (int) (255 * (1 - ratio));
        int g = (int) (255 * ratio);
        int b = 0;
        
        return (255 << 24) | (r << 16) | (g << 8) | b;
    }
}
