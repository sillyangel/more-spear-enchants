package dev.sillyangel.more_spear_enchantments.fabric.enchantment;

import com.mojang.serialization.MapCodec;
import dev.sillyangel.more_spear_enchantments.MoreSpearEnchantments;
import dev.sillyangel.more_spear_enchantments.enchantment.effect.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantmentEffects {
	public static final ResourceKey<Enchantment> THUNDERING = of("thundering");
	public static MapCodec<LightningEnchantmentEffect> LIGHTNING_EFFECT = register("lightning_effect", LightningEnchantmentEffect.CODEC);
	public static final ResourceKey<Enchantment> WITHERING = of("withering");
	public static MapCodec<WitheringEnchantmentEffect> WITHERING_EFFECT = register("withering_effect", WitheringEnchantmentEffect.CODEC);
	public static final ResourceKey<Enchantment> POISONING = of("poisoning");
	public static MapCodec<PoisonEnchantmentEffect> POISONING_EFFECT = register("poisoning_effect", PoisonEnchantmentEffect.CODEC);
	public static final ResourceKey<Enchantment> CRIPPLING = of("crippling");
	public static MapCodec<CripplingEnchantmentEffect> CRIPPLING_EFFECT = register("crippling_effect", CripplingEnchantmentEffect.CODEC);


	private static ResourceKey<Enchantment> of(String path) {
		Identifier id = Identifier.fromNamespaceAndPath(MoreSpearEnchantments.MOD_ID, path);
		return ResourceKey.create(Registries.ENCHANTMENT, id);
	}

	private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
		return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.fromNamespaceAndPath(MoreSpearEnchantments.MOD_ID, id), codec);
	}

	public static void registerModEnchantmentEffects() {
		MoreSpearEnchantments.LOGGER.info("Registering EnchantmentEffects for " + MoreSpearEnchantments.MOD_ID);

//		register("lightning", LightningEnchantmentEffect.CODEC);
//		register("poison", PoisonEnchantmentEffect.CODEC);
//		register("withering", WitheringEnchantmentEffect.CODEC);
//		register("crippling", CripplingEnchantmentEffect.CODEC);
	}
}

