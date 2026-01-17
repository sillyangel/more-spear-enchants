package dev.sillyangel.more_spear_enchantments.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.phys.Vec3;

public record PoisonEnchantmentEffect(LevelBasedValue duration) implements EnchantmentEntityEffect {
    public static final MapCodec<PoisonEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    LevelBasedValue.CODEC.fieldOf("duration").forGetter(PoisonEnchantmentEffect::duration)
            ).apply(instance, PoisonEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        if (target instanceof LivingEntity victim) {
            if (context.owner() != null && context.owner() instanceof Player player) {
                int poisonDuration = (int) (this.duration.calculate(level) * 40); // Convert to ticks
                int poisonAmplifier = level - 2; // Level 1 = Poison 0, Level 2 = Poison I, Level 3 = Poison II

                victim.addEffect(new MobEffectInstance(
                        MobEffects.POISON,
                        poisonDuration,
                        poisonAmplifier,
                        false,
                        true
                ));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}

