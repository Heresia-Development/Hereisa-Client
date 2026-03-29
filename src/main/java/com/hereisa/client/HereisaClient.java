package com.hereisa.client;

import com.hereisa.client.core.ModuleManager;
import com.hereisa.client.ui.ClickGUI;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class HereisaClient implements ClientModInitializer {
    public static ModuleManager moduleManager;
    private static KeyBinding guiKeyBinding;

    @Override
    public void onInitializeClient() {
        moduleManager = new ModuleManager();

        guiKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.hereisa.clickgui", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_RIGHT_SHIFT, 
                "category.hereisa.client"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.world != null) {
                if (moduleManager != null) {
                    moduleManager.onTick();
                }

                while (guiKeyBinding.wasPressed()) {
                    if (!(client.currentScreen instanceof ClickGUI)) {
                        client.setScreen(new ClickGUI());
                    }
                }
            }
        });
    }
}
