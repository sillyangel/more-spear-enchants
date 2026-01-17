package dev.sillyangel.more_spear_enchantments.fabric.enchantment;

import dev.sillyangel.more_spear_enchantments.util.ModTags;
import dev.sillyangel.more_spear_enchantments.enchantment.effect.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.LevelBasedValue.Linear;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentGenerator extends FabricDynamicRegistryProvider {
	public ModEnchantmentGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
		System.out.println("REGISTERING ENCHANTS");
	}

	@Override
	protected void configure(HolderLookup.Provider registries, Entries entries) {
		// Our new enchantment, "Thundering."
		register(entries, ModEnchantmentEffects.THUNDERING, Enchantment.enchantment(
				Enchantment.definition(
					registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
					// this is the "weight" or probability of our enchantment showing up in the table
					10,
					// the maximum level of the enchantment
					3,
					// base cost for level 1 of the enchantment, and min levels required for something higher
					Enchantment.dynamicCost(1, 10),
					// same fields as above but for max cost
					Enchantment.dynamicCost(1, 15),
					// anvil cost
					5,
					// valid slots
					EquipmentSlotGroup.HAND
				)
			)
				.withEffect(
					// enchantment occurs POST_ATTACK
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					new LightningEnchantmentEffect(LevelBasedValue.perLevel(0.4f, 0.2f)) // scale the enchantment linearly.
				)
		);
		register(entries, ModEnchantmentEffects.POISONING, Enchantment.enchantment(
				Enchantment.definition(
						registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
						10,
						3,
						Enchantment.dynamicCost(1, 10),
						Enchantment.dynamicCost(1, 15),
						5,
						EquipmentSlotGroup.HAND
				)
		)
				.withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					new PoisonEnchantmentEffect(LevelBasedValue.perLevel(3.0f, 1.0f)) // 3s base, +1s per level
				)
		);
		// Our new enchantment, "Withering."
		register(entries, ModEnchantmentEffects.WITHERING, Enchantment.enchantment(
				Enchantment.definition(
					registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
					10,
					3,
					Enchantment.dynamicCost(1, 10),
					Enchantment.dynamicCost(1, 15),
					5,
					EquipmentSlotGroup.HAND
				)
			)
				.withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					new WitheringEnchantmentEffect(LevelBasedValue.perLevel(2.0f, 1.0f)) // 2s base, +1s per level
				)
		);
		// Our new enchantment, "Crippling."
		register(entries, ModEnchantmentEffects.CRIPPLING, Enchantment.enchantment(
				Enchantment.definition(
					registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
					10,
					3,
					Enchantment.dynamicCost(1, 10),
					Enchantment.dynamicCost(1, 15),
					5,
					EquipmentSlotGroup.HAND
				)
			)
                .withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					new CripplingEnchantmentEffect(LevelBasedValue.perLevel(2.0f, 1.0f)) // 2s base, +1s per leve
				)
		);
	}

	private void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
		entries.add(key, builder.build(key.identifier()), resourceConditions); //idk, im going insane
	}

	@Override
	public String getName() {
		return "ModEnchantmentGenerator";
	}
}

