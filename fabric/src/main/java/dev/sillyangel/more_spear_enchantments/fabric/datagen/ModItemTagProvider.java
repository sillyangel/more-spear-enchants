package dev.sillyangel.more_spear_enchantments.fabric.datagen;

import dev.sillyangel.more_spear_enchantments.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
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