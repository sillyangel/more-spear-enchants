package dev.sillyangel.more_spear_enchantments.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public record ExplosiveEnchantmentEffect(LevelBasedValue power, LevelBasedValue chance) implements EnchantmentEntityEffect {
    public static final MapCodec<ExplosiveEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    LevelBasedValue.CODEC.fieldOf("power").forGetter(ExplosiveEnchantmentEffect::power),
                    LevelBasedValue.CODEC.fieldOf("chance").forGetter(ExplosiveEnchantmentEffect::chance)
            ).apply(instance, ExplosiveEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        if (target instanceof LivingEntity victim) {
            if (context.owner() != null && context.owner() instanceof Player player) {
                // Calculate chance and power based on level
                float explosionChance = this.chance.calculate(level);

                // Random check for explosion
                if (world.random.nextFloat() < explosionChance) {
                    float explosionPower = this.power.calculate(level);

                    // Create explosion at victim's location
                    // false, false = no block breaking, no fire
                    world.explode(
                            null,
                            victim.getX(),
                            victim.getY(),
                            victim.getZ(),
                            explosionPower,
                            Level.ExplosionInteraction.NONE
                    );
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}

