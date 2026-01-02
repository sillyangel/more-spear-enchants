package dev.sillyangel.more_spear_enchantments.fabric.datagen;

import dev.sillyangel.more_spear_enchantments.util.ModTags;
import dev.sillyangel.more_spear_enchantments.MoreSpearEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ModTags.Items.SPEARS)
                .add(Items.WOODEN_SPEAR)
                .add(Items.STONE_SPEAR)
                .add(Items.COPPER_SPEAR)
                .add(Items.IRON_SPEAR)
                .add(Items.GOLDEN_SPEAR)
                .add(Items.DIAMOND_SPEAR)
                .add(Items.NETHERITE_SPEAR);
    }
}