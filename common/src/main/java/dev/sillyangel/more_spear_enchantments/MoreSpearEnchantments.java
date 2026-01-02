package dev.sillyangel.more_spear_enchantments;

import dev.sillyangel.more_spear_enchantments.enchantment.ModEnchantmentEffects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MoreSpearEnchantments {
    public static final String MOD_ID = "more_spear_enchantments";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        LOGGER.info("Initializing More Spear Enchantments");
        ModEnchantmentEffects.registerModEnchantmentEffects();
    }
}
