package dev.sillyangel.more_spear_enchantments.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.phys.Vec3;

public record VampiricEnchantmentEffect(LevelBasedValue healPercentage) implements EnchantmentEntityEffect {
    public static final MapCodec<VampiricEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    LevelBasedValue.CODEC.fieldOf("heal_percentage").forGetter(VampiricEnchantmentEffect::healPercentage)
            ).apply(instance, VampiricEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        if (target instanceof LivingEntity victim) {
            if (context.owner() != null && context.owner() instanceof Player player) {
                // Calculate heal amount based on a percentage of damage
                // This is a simplified version - you may need to track actual damage dealt
                float healPercent = this.healPercentage.calculate(level);

                // Heal the player (assuming average damage for calculation)
                float healAmount = 2.0f * healPercent; // Base heal amount scaled by level

                float maxHealth = player.getMaxHealth();
                float newHealth = Math.min(player.getHealth() + healAmount, maxHealth);
                player.setHealth(newHealth);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}

