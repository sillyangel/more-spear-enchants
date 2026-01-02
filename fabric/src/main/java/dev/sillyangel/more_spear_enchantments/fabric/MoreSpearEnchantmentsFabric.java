package dev.sillyangel.more_spear_enchantments.fabric;

import dev.sillyangel.more_spear_enchantments.MoreSpearEnchantments;
import net.fabricmc.api.ModInitializer;

public class MoreSpearEnchantmentsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // Run our common setup
        MoreSpearEnchantments.init();
    }
}

