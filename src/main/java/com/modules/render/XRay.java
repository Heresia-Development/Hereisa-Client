package com.hereisa.client.modules.render;

import com.hereisa.client.core.Category;
import com.hereisa.client.core.Module;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import java.util.ArrayList;
import java.util.List;

public class XRay extends Module {
  
    public static final List<Block> ORES = new ArrayList<>();

    public XRay() {
        super("XRay", Category.RENDER);
        
        ORES.add(Blocks.DIAMOND_ORE);
        ORES.add(Blocks.DEEPSLATE_DIAMOND_ORE);
        ORES.add(Blocks.GOLD_ORE);
        ORES.add(Blocks.DEEPSLATE_GOLD_ORE);
        ORES.add(Blocks.IRON_ORE);
        ORES.add(Blocks.DEEPSLATE_IRON_ORE);
        ORES.add(Blocks.COAL_ORE);
        ORES.add(Blocks.DEEPSLATE_COAL_ORE);
        ORES.add(Blocks.NETHER_QUARTZ_ORE);
        ORES.add(Blocks.ANCIENT_DEBRIS);
        ORES.add(Blocks.EMERALD_ORE);
        ORES.add(Blocks.DEEPSLATE_EMERALD_ORE);
    }

    @Override
    public void onEnable() {
        if (mc.worldRenderer != null) {
            mc.worldRenderer.reload();
        }
    }

    @Override
    public void onDisable() {
        if (mc.worldRenderer != null) {
            mc.worldRenderer.reload();
        }
    }

    public static boolean isOre(Block block) {
        return ORES.contains(block);
    }
}
