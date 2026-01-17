package dev.sillyangel.more_spear_enchantments.util;

import dev.sillyangel.more_spear_enchantments.MoreSpearEnchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreSpearEnchantments.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SPEARS = createTag("spears");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreSpearEnchantments.MOD_ID, name));
        }
    }
}