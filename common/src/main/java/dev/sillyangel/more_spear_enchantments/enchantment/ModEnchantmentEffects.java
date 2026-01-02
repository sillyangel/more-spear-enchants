package dev.sillyangel.more_spear_enchantments.enchantment;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.sillyangel.more_spear_enchantments.MoreSpearEnchantments;
import dev.sillyangel.more_spear_enchantments.enchantment.effect.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
	// Deferred register for enchantment effect types
	public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_ENTITY_EFFECTS =
			DeferredRegister.create(MoreSpearEnchantments.MOD_ID, RegistryKeys.ENCHANTMENT_ENTITY_EFFECT_TYPE);

	// Enchantment registry keys
	public static final RegistryKey<Enchantment> THUNDERING = of("thundering");
	public static final RegistryKey<Enchantment> WITHERING = of("withering");
	public static final RegistryKey<Enchantment> POISONING = of("poisoning");
	public static final RegistryKey<Enchantment> CRIPPLING = of("crippling");

	// Register enchantment effect types
	public static final RegistrySupplier<MapCodec<LightningEnchantmentEffect>> LIGHTNING_EFFECT =
			ENCHANTMENT_ENTITY_EFFECTS.register("lightning", () -> LightningEnchantmentEffect.CODEC);
	public static final RegistrySupplier<MapCodec<PoisonEnchantmentEffect>> POISON_EFFECT =
			ENCHANTMENT_ENTITY_EFFECTS.register("poison", () -> PoisonEnchantmentEffect.CODEC);
	public static final RegistrySupplier<MapCodec<WitheringEnchantmentEffect>> WITHERING_EFFECT =
			ENCHANTMENT_ENTITY_EFFECTS.register("withering", () -> WitheringEnchantmentEffect.CODEC);
	public static final RegistrySupplier<MapCodec<CripplingEnchantmentEffect>> CRIPPLING_EFFECT =
			ENCHANTMENT_ENTITY_EFFECTS.register("crippling", () -> CripplingEnchantmentEffect.CODEC);

	private static RegistryKey<Enchantment> of(String path) {
		Identifier id = Identifier.of(MoreSpearEnchantments.MOD_ID, path);
		return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
	}

	public static void registerModEnchantmentEffects() {
		MoreSpearEnchantments.LOGGER.info("Registering EnchantmentEffects for " + MoreSpearEnchantments.MOD_ID);
		ENCHANTMENT_ENTITY_EFFECTS.register();
	}
}

