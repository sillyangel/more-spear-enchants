package dev.sillyangel.more_spear_enchantments.fabric.enchantment;

import com.mojang.serialization.MapCodec;
import dev.sillyangel.more_spear_enchantments.MoreSpearEnchantments;
import dev.sillyangel.more_spear_enchantments.enchantment.effect.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;

public class ModEnchantmentEffects {
	public static final RegistryKey<Enchantment> THUNDERING = of("thundering");
	public static final RegistryKey<Enchantment> WITHERING = of("withering");
	public static final RegistryKey<Enchantment> POISONING = of("poisoning");
	public static final RegistryKey<Enchantment> CRIPPLING = of("crippling");


	private static RegistryKey<Enchantment> of(String path) {
		Identifier id = Identifier.of(MoreSpearEnchantments.MOD_ID, path);
		return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
	}

	private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
		return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(MoreSpearEnchantments.MOD_ID, id), codec);
	}

	public static void registerModEnchantmentEffects() {
		MoreSpearEnchantments.LOGGER.info("Registering EnchantmentEffects for " + MoreSpearEnchantments.MOD_ID);

		register("lightning", LightningEnchantmentEffect.CODEC);
		register("poison", PoisonEnchantmentEffect.CODEC);
		register("withering", WitheringEnchantmentEffect.CODEC);
		register("crippling", CripplingEnchantmentEffect.CODEC);
	}
}

