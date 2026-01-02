package dev.sillyangel.more_spear_enchantments.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.util.math.Vec3d;

public record PoisonEnchantmentEffect(EnchantmentLevelBasedValue duration) implements EnchantmentEntityEffect {
    public static final MapCodec<PoisonEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("duration").forGetter(PoisonEnchantmentEffect::duration)
            ).apply(instance, PoisonEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        if (target instanceof LivingEntity victim) {
            if (context.owner() != null && context.owner() instanceof PlayerEntity player) {
                int poisonDuration = (int) (this.duration.getValue(level) * 40); // Convert to ticks
                int poisonAmplifier = level - 2; // Level 1 = Poison 0, Level 2 = Poison I, Level 3 = Poison II

                victim.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.POISON,
                        poisonDuration,
                        poisonAmplifier,
                        false,
                        true
                ));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}

