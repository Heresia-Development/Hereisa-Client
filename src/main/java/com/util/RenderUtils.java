package com.hereisa.client.util;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public class RenderUtils {
    public static void drawOutlinedBox(DrawContext ctx, int x, int y, int w, int h, int color) {
        ctx.fill(x, y, x + w, y + 1, color); 
        ctx.fill(x, y + h - 1, x + w, y + h, color); 
        ctx.fill(x, y, x + 1, y + h, color); 
        ctx.fill(x + w - 1, y, x + w, y + h, color); 
    }

    public static int getHealthColor(float h, float m) {
        float r = MathHelper.clamp(h / m, 0, 1);
        return (255 << 24) | ((int)(255 * (1-r)) << 16) | ((int)(255 * r) << 8);
    }
}
