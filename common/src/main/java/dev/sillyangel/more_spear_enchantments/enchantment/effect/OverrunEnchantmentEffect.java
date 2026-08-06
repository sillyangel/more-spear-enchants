package dev.sillyangel.more_spear_enchantments.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record OverrunEnchantmentEffect(LevelBasedValue duration) implements EnchantmentEntityEffect {

    public static final MapCodec<OverrunEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    LevelBasedValue.CODEC.fieldOf("duration")
                            .forGetter(OverrunEnchantmentEffect::duration)
            ).apply(instance, OverrunEnchantmentEffect::new)
    );


    @Override
    public void apply(
            ServerLevel world,
            int level,
            EnchantedItemInUse context,
            Entity target,
            Vec3 pos
    ) {

        if (!(context.owner() instanceof Player player)) {
            return;
        }

        if (!(target instanceof LivingEntity victim)) {
            return;
        }

        // Only activate when the enemy dies
        if (victim.isAlive()) {
            return;
        }

        int time = (int) duration.calculate(level);

        // Give speed after killing
        player.addEffect(
                new MobEffectInstance(
                        MobEffects.SPEED,
                        time,
                        level - 1
                )
        );


        // Push player forward
        Vec3 movement = player.getLookAngle()
                .multiply(
                        0.6 + (level * 0.2),
                        0,
                        0.6 + (level * 0.2)
                );

        player.push(
                movement.x,
                0.25,
                movement.z
        );

        player.hurtMarked = true;
    }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}