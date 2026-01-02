package dev.sillyangel.more_spear_enchantments.fabric.enchantment;

import dev.sillyangel.more_spear_enchantments.util.ModTags;
import dev.sillyangel.more_spear_enchantments.enchantment.effect.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.EnchantmentEffectComponentTypes;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentGenerator extends FabricDynamicRegistryProvider {
	public ModEnchantmentGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
		System.out.println("REGISTERING ENCHANTS");
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
		// Our new enchantment, "Thundering."
		register(entries, ModEnchantmentEffects.THUNDERING, Enchantment.builder(
				Enchantment.definition(
					registries.getOrThrow(RegistryKeys.ITEM).getOrThrow(ModTags.Items.SPEARS),
					// this is the "weight" or probability of our enchantment showing up in the table
					10,
					// the maximum level of the enchantment
					3,
					// base cost for level 1 of the enchantment, and min levels required for something higher
					Enchantment.leveledCost(1, 10),
					// same fields as above but for max cost
					Enchantment.leveledCost(1, 15),
					// anvil cost
					5,
					// valid slots
					AttributeModifierSlot.HAND
				)
			)
				.addEffect(
					// enchantment occurs POST_ATTACK
					EnchantmentEffectComponentTypes.POST_ATTACK,
					EnchantmentEffectTarget.ATTACKER,
					EnchantmentEffectTarget.VICTIM,
					new LightningEnchantmentEffect(EnchantmentLevelBasedValue.linear(0.4f, 0.2f)) // scale the enchantment linearly.
				)
		);
		register(entries, ModEnchantmentEffects.POISONING, Enchantment.builder(
				Enchantment.definition(
						registries.getOrThrow(RegistryKeys.ITEM).getOrThrow(ModTags.Items.SPEARS),
						10,
						3,
						Enchantment.leveledCost(1, 10),
						Enchantment.leveledCost(1, 15),
						5,
						AttributeModifierSlot.HAND
				)
		)
				.addEffect(
					EnchantmentEffectComponentTypes.POST_ATTACK,
					EnchantmentEffectTarget.ATTACKER,
					EnchantmentEffectTarget.VICTIM,
					new PoisonEnchantmentEffect(EnchantmentLevelBasedValue.linear(3.0f, 1.0f)) // 3s base, +1s per level
				)
		);
		// Our new enchantment, "Withering."
		register(entries, ModEnchantmentEffects.WITHERING, Enchantment.builder(
				Enchantment.definition(
					registries.getOrThrow(RegistryKeys.ITEM).getOrThrow(ModTags.Items.SPEARS),
					10,
					3,
					Enchantment.leveledCost(1, 10),
					Enchantment.leveledCost(1, 15),
					5,
					AttributeModifierSlot.HAND
				)
			)
				.addEffect(
					EnchantmentEffectComponentTypes.POST_ATTACK,
					EnchantmentEffectTarget.ATTACKER,
					EnchantmentEffectTarget.VICTIM,
					new WitheringEnchantmentEffect(EnchantmentLevelBasedValue.linear(2.0f, 1.0f)) // 2s base, +1s per level
				)
		);
		// Our new enchantment, "Crippling."
		register(entries, ModEnchantmentEffects.CRIPPLING, Enchantment.builder(
				Enchantment.definition(
					registries.getOrThrow(RegistryKeys.ITEM).getOrThrow(ModTags.Items.SPEARS),
					10,
					3,
					Enchantment.leveledCost(1, 10),
					Enchantment.leveledCost(1, 15),
					5,
					AttributeModifierSlot.HAND
				)
			)
				.addEffect(
					EnchantmentEffectComponentTypes.POST_ATTACK,
					EnchantmentEffectTarget.ATTACKER,
					EnchantmentEffectTarget.VICTIM,
					new CripplingEnchantmentEffect(EnchantmentLevelBasedValue.linear(2.0f, 1.0f)) // 2s base, +1s per leve
				)
		);
	}

	private void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
		entries.add(key, builder.build(key.getValue()), resourceConditions);
	}

	@Override
	public String getName() {
		return "ModEnchantmentGenerator";
	}
}

