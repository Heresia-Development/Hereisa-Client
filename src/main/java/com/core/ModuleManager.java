package com.hereisa.client.core;

import com.hereisa.client.modules.combat.*;
import com.hereisa.client.modules.movement.*;
import com.hereisa.client.modules.render.*;
import java.util.*;
import java.util.stream.Collectors;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        modules.add(new KillAura());
        modules.add(new Reach());
        modules.add(new BoatFly());
        modules.add(new Spider());
        modules.add(new XRay());
        modules.add(new ESP());
    }

    public List<Module> getModules() { return modules; }
    public List<Module> getModulesByCategory(Category c) {
        return modules.stream().filter(m -> m.getCategory() == c).collect(Collectors.toList());
    }
    public void onTick() {
        modules.stream().filter(Module::isEnabled).forEach(Module::onTick);
    }
}
