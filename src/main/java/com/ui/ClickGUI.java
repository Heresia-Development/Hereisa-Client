package com.hereisa.client.ui;

import com.hereisa.client.HereisaClient;
import com.hereisa.client.core.Category;
import com.hereisa.client.core.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ClickGUI extends Screen {
    public ClickGUI() { 
        super(Text.literal("Hereisa")); 
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        this.renderBackground(ctx, mouseX, mouseY, delta);
        
        int x = 10;
        for (Category cat : Category.values()) {
            ctx.fill(x, 10, x + 90, 25, 0xFF1A1A1A);
            ctx.drawCenteredTextWithShadow(textRenderer, cat.name(), x + 45, 14, -1);
            
            int y = 26;
            for (Module m : HereisaClient.moduleManager.getModulesByCategory(cat)) {
                int c = m.isEnabled() ? 0xFF00AAFF : 0xFF000000;
                
                ctx.fill(x, y, x + 90, y + 14, c);
                
                String txt = m.getName() + (m.getName().equals("Reach") ? " " + m.reachValue : "");
                ctx.drawTextWithShadow(textRenderer, txt, x + 5, y + 3, -1);
                
                y += 15;
            }
            x += 95;
        }
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int btn) {
        int x = 10;
        for (Category cat : Category.values()) {
            int y = 26;
            for (Module m : HereisaClient.moduleManager.getModulesByCategory(cat)) {
                if (mx >= x && mx <= x + 90 && my >= y && my <= y + 14) {
                    if (btn == 0) {
                        m.toggle();
                    } 
                    else if (btn == 1 && m.getName().equals("Reach")) { 
                        m.reachValue += 0.5f;
                        if (m.reachValue > 6.0f) m.reachValue = 3.0f;
                    }
                    return true;
                }
                y += 15;
            }
            x += 95;
        }
        return super.mouseClicked(mx, my, btn);
    }
    
    @Override
    public boolean shouldPause() {
        return false; 
    }
}
