package dev.sillyangel.more_spear_enchantments.fabric.datagen;

import dev.sillyangel.more_spear_enchantments.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.ItemIds;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        tag(ModTags.Items.SPEARS)
                .add(ItemIds.WOODEN_SPEAR)
                .add(ItemIds.STONE_SPEAR)
                .add(ItemIds.COPPER_SPEAR)
                .add(ItemIds.IRON_SPEAR)
                .add(ItemIds.GOLDEN_SPEAR)
                .add(ItemIds.DIAMOND_SPEAR)
                .add(ItemIds.NETHERITE_SPEAR);
    }
}