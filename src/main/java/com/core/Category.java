package com.hereisa.client.core;

public enum Category {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    PLAYER("Player"),
    WORLD("World"),
    MISC("Misc");

    public final String name;

    Category(String name) {
        this.name = name;
    }
}
