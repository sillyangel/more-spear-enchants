package dev.sillyangel.more_spear_enchantments.fabric.enchantment;

import dev.sillyangel.more_spear_enchantments.util.ModTags;
import dev.sillyangel.more_spear_enchantments.enchantment.ModEnchantmentEffects;
import dev.sillyangel.more_spear_enchantments.enchantment.effect.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
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
	public ModEnchantmentGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
		System.out.println("REGISTERING ENCHANTS");
	}

	@Override
	protected void configure(HolderLookup.Provider registries, Entries entries) {
		// Thundering
		register(entries, ModEnchantmentEffects.THUNDERING, Enchantment.enchantment(
				Enchantment.definition(
					registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
					10, 3,
					Enchantment.dynamicCost(1, 1),
					Enchantment.dynamicCost(1, 1),
					1,
					EquipmentSlotGroup.HAND
				)
			).withEffect(
				EnchantmentEffectComponents.POST_ATTACK,
				EnchantmentTarget.ATTACKER,
				EnchantmentTarget.VICTIM,
				new LightningEnchantmentEffect(LevelBasedValue.perLevel(0.4f, 0.2f))
			)
		);

		// Poisoning
		register(entries, ModEnchantmentEffects.POISONING, Enchantment.enchantment(
				Enchantment.definition(
						registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
						10, 3,
						Enchantment.dynamicCost(1, 1),
						Enchantment.dynamicCost(1, 1),
						1,
						EquipmentSlotGroup.HAND
				)
		).withEffect(
				EnchantmentEffectComponents.POST_ATTACK,
				EnchantmentTarget.ATTACKER,
				EnchantmentTarget.VICTIM,
				new PoisonEnchantmentEffect(LevelBasedValue.perLevel(3.0f, 1.0f))
		));

		// Withering
		register(entries, ModEnchantmentEffects.WITHERING, Enchantment.enchantment(
				Enchantment.definition(
					registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
					10, 3,
					Enchantment.dynamicCost(1, 1),
					Enchantment.dynamicCost(1, 1),
					1,
					EquipmentSlotGroup.HAND
				)
			).withEffect(
				EnchantmentEffectComponents.POST_ATTACK,
				EnchantmentTarget.ATTACKER,
				EnchantmentTarget.VICTIM,
				new WitheringEnchantmentEffect(LevelBasedValue.perLevel(2.0f, 1.0f))
			)
		);

		// Crippling
		register(entries, ModEnchantmentEffects.CRIPPLING, Enchantment.enchantment(
				Enchantment.definition(
					registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
					10, 3,
					Enchantment.dynamicCost(1, 1),
					Enchantment.dynamicCost(1, 1),
					1,
					EquipmentSlotGroup.HAND
				)
			).withEffect(
				EnchantmentEffectComponents.POST_ATTACK,
				EnchantmentTarget.ATTACKER,
				EnchantmentTarget.VICTIM,
				new CripplingEnchantmentEffect(LevelBasedValue.perLevel(2.0f, 1.0f))
			)
		);

		// Vampiric
		register(entries, ModEnchantmentEffects.VAMPIRIC, Enchantment.enchantment(
				Enchantment.definition(
					registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
					5, 3,
					Enchantment.dynamicCost(1, 1),
					Enchantment.dynamicCost(1, 1),
					1,
					EquipmentSlotGroup.HAND
				)
			).withEffect(
				EnchantmentEffectComponents.POST_ATTACK,
				EnchantmentTarget.ATTACKER,
				EnchantmentTarget.VICTIM,
				new VampiricEnchantmentEffect(LevelBasedValue.perLevel(0.5f, 0.5f))
			)
		);

		// Explosive
		register(entries, ModEnchantmentEffects.EXPLOSIVE, Enchantment.enchantment(
				Enchantment.definition(
					registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
					2, 3,
					Enchantment.dynamicCost(1, 1),
					Enchantment.dynamicCost(1, 1),
					1,
					EquipmentSlotGroup.HAND
				))
			.withEffect(
				EnchantmentEffectComponents.POST_ATTACK,
				EnchantmentTarget.ATTACKER,
				EnchantmentTarget.VICTIM,
				new ExplosiveEnchantmentEffect(
					LevelBasedValue.perLevel(1.5f, 0.5f),
					LevelBasedValue.perLevel(0.1f, 0.1f)
				)
			)
		);

		// Overrun
		register(entries, ModEnchantmentEffects.OVERRUN, Enchantment.enchantment(
				Enchantment.definition(
						registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
						5, 3,
						Enchantment.dynamicCost(1, 1),
						Enchantment.dynamicCost(1, 1),
						1,
						EquipmentSlotGroup.HAND
				))
			.withEffect(
				EnchantmentEffectComponents.POST_ATTACK,
				EnchantmentTarget.ATTACKER,
				EnchantmentTarget.VICTIM,
				new OverrunEnchantmentEffect(LevelBasedValue.perLevel(40.0f, 20.0f))
			)
		);

		// Momentum - consecutive kills build damage stacks
		register(entries, ModEnchantmentEffects.MOMENTUM, Enchantment.enchantment(
				Enchantment.definition(
						registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
						8, 3,
						Enchantment.dynamicCost(1, 1),
						Enchantment.dynamicCost(1, 1),
						1,
						EquipmentSlotGroup.HAND
				))
			.withEffect(
				EnchantmentEffectComponents.POST_ATTACK,
				EnchantmentTarget.ATTACKER,
				EnchantmentTarget.VICTIM,
				new MomentumEnchantmentEffect(
						LevelBasedValue.perLevel(0.15f, 0.05f),  // 15% base, +5% per level
						LevelBasedValue.perLevel(3.0f, 1.0f),      // max 3 stacks base, +1 per level
						LevelBasedValue.perLevel(8.0f, 2.0f)       // 8s base, +2s per level
				)
			)
		);

		// Impact - kill creates shockwave
		register(entries, ModEnchantmentEffects.IMPACT, Enchantment.enchantment(
				Enchantment.definition(
						registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.SPEARS),
						3, 3,
						Enchantment.dynamicCost(1, 1),
						Enchantment.dynamicCost(1, 1),
						1,
						EquipmentSlotGroup.HAND
				))
			.withEffect(
				EnchantmentEffectComponents.POST_ATTACK,
				EnchantmentTarget.ATTACKER,
				EnchantmentTarget.VICTIM,
				new ImpactEnchantmentEffect(
						LevelBasedValue.perLevel(4.0f, 1.0f),   // 4 block radius base, +1 per level
						LevelBasedValue.perLevel(1.5f, 0.5f),   // knockback 1.5 base, +0.5 per level
						LevelBasedValue.perLevel(3.0f, 1.0f)    // 3 damage base, +1 per level
				)
			)
		);
	}

	private void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
		entries.add(key, builder.build(key.identifier()), resourceConditions);
	}

	@Override
	public String getName() {
		return "ModEnchantmentGenerator";
	}
}
