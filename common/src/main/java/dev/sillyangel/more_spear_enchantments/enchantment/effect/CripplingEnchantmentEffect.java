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

public record CripplingEnchantmentEffect(EnchantmentLevelBasedValue duration) implements EnchantmentEntityEffect {
    public static final MapCodec<CripplingEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("duration").forGetter(CripplingEnchantmentEffect::duration)
            ).apply(instance, CripplingEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        if (target instanceof LivingEntity victim) {
            if (context.owner() != null && context.owner() instanceof PlayerEntity player) {
                int Duration = (int) (this.duration.getValue(level) * 50); // Convert to ticks
                int slownessAmplifier = level - 1; // Level 1 = Slowness 0, Level 2 = Slowness I, Level 3 = Slowness II
                int weaknessAmplifier = level - 1; // Level 1 = Weakness 0, Level 2 = Weakness I, Level 3 = Weakness II
                victim.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SLOWNESS,
                        Duration,
                        slownessAmplifier,
                        false,
                        true
                ));
                victim.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.WEAKNESS,
                        Duration,
                        weaknessAmplifier,
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

