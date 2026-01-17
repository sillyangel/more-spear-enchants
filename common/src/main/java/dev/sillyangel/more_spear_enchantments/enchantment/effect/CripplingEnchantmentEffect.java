package dev.sillyangel.more_spear_enchantments.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.phys.Vec3;

public record CripplingEnchantmentEffect(LevelBasedValue duration) implements EnchantmentEntityEffect {
    public static final MapCodec<CripplingEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    LevelBasedValue.CODEC.fieldOf("duration").forGetter(CripplingEnchantmentEffect::duration)
            ).apply(instance, CripplingEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        if (target instanceof LivingEntity victim) {
            if (context.owner() != null && context.owner() instanceof Player player) {
                int Duration = (int) (this.duration.calculate(level) * 50); // Convert to ticks
                int slownessAmplifier = level - 1; // Level 1 = Slowness 0, Level 2 = Slowness I, Level 3 = Slowness II
                int weaknessAmplifier = level - 1; // Level 1 = Weakness 0, Level 2 = Weakness I, Level 3 = Weakness II
                victim.addEffect(new MobEffectInstance(
                        MobEffects.SLOWNESS,
                        Duration,
                        slownessAmplifier,
                        false,
                        true
                ));
                victim.addEffect(new MobEffectInstance(
                        MobEffects.WEAKNESS,
                        Duration,
                        weaknessAmplifier,
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

