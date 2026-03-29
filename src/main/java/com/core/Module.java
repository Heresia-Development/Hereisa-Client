package com.hereisa.client.core;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    private final String name;
    private final Category category;
    private int key;
    private boolean enabled;

    public Module(String name, Category category, int key) {
        this.name = name;
        this.category = category;
        this.key = key;
        this.enabled = false;
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }
}
