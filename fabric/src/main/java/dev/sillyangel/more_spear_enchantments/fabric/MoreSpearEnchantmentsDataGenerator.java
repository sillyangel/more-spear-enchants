package dev.sillyangel.more_spear_enchantments.fabric;

import dev.sillyangel.more_spear_enchantments.fabric.datagen.ModItemTagProvider;
import dev.sillyangel.more_spear_enchantments.fabric.enchantment.ModEnchantmentGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MoreSpearEnchantmentsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModEnchantmentGenerator::new);
	}
}
