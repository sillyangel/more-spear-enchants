package dev.sillyangel.more_spear_enchantments.enchantment;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.sillyangel.more_spear_enchantments.MoreSpearEnchantments;
import dev.sillyangel.more_spear_enchantments.enchantment.effect.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

public class ModEnchantmentEffects {
	// Deferred register for enchantment effect types
	public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_ENTITY_EFFECTS =
			DeferredRegister.create(MoreSpearEnchantments.MOD_ID, Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE);

	// Enchantment registry keys
	public static final ResourceKey<Enchantment> THUNDERING = of("thundering");
	public static final ResourceKey<Enchantment> WITHERING = of("withering");
	public static final ResourceKey<Enchantment> POISONING = of("poisoning");
	public static final ResourceKey<Enchantment> CRIPPLING = of("crippling");
	public static final ResourceKey<Enchantment> VAMPIRIC = of("vampiric");
	public static final ResourceKey<Enchantment> EXPLOSIVE = of("explosive");

	// Register enchantment effect types
	public static final RegistrySupplier<MapCodec<LightningEnchantmentEffect>> LIGHTNING_EFFECT =
			ENCHANTMENT_ENTITY_EFFECTS.register("lightning", () -> LightningEnchantmentEffect.CODEC);
	public static final RegistrySupplier<MapCodec<PoisonEnchantmentEffect>> POISON_EFFECT =
			ENCHANTMENT_ENTITY_EFFECTS.register("poison", () -> PoisonEnchantmentEffect.CODEC);
	public static final RegistrySupplier<MapCodec<WitheringEnchantmentEffect>> WITHERING_EFFECT =
			ENCHANTMENT_ENTITY_EFFECTS.register("withering", () -> WitheringEnchantmentEffect.CODEC);
	public static final RegistrySupplier<MapCodec<CripplingEnchantmentEffect>> CRIPPLING_EFFECT =
			ENCHANTMENT_ENTITY_EFFECTS.register("crippling", () -> CripplingEnchantmentEffect.CODEC);
	public static final RegistrySupplier<MapCodec<VampiricEnchantmentEffect>> VAMPIRIC_EFFECT =
			ENCHANTMENT_ENTITY_EFFECTS.register("vampiric", () -> VampiricEnchantmentEffect.CODEC);
	public static final RegistrySupplier<MapCodec<ExplosiveEnchantmentEffect>> EXPLOSIVE_EFFECT =
			ENCHANTMENT_ENTITY_EFFECTS.register("explosive", () -> ExplosiveEnchantmentEffect.CODEC);

	private static ResourceKey<Enchantment> of(String path) {
		Identifier id = Identifier.fromNamespaceAndPath(MoreSpearEnchantments.MOD_ID, path);
		return ResourceKey.create(Registries.ENCHANTMENT, id);
	}

	public static void registerModEnchantmentEffects() {
		MoreSpearEnchantments.LOGGER.info("Registering EnchantmentEffects for " + MoreSpearEnchantments.MOD_ID);
		ENCHANTMENT_ENTITY_EFFECTS.register();
	}
}

