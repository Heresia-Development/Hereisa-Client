package com.hereisa.client;

import com.hereisa.client.core.ModuleManager;
import com.hereisa.client.ui.ClickGUI;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.util.InputUtil;

public class HereisaClient implements ClientModInitializer {
    public static ModuleManager moduleManager;

    @Override
    public void onInitializeClient() {
        moduleManager = new ModuleManager();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                moduleManager.onTick();
                if (InputUtil.isKeyPressed(client.getWindow().getHandle(), 344)) { 
                    if (!(client.currentScreen instanceof ClickGUI)) client.setScreen(new ClickGUI());
                }
            }
        });
    }
}
