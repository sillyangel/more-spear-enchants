package dev.sillyangel.more_spear_enchantments.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.phys.Vec3;

public record ImpactEnchantmentEffect(LevelBasedValue radius, LevelBasedValue knockback, LevelBasedValue damage) implements EnchantmentEntityEffect {

    public static final MapCodec<ImpactEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    LevelBasedValue.CODEC.fieldOf("radius").forGetter(ImpactEnchantmentEffect::radius),
                    LevelBasedValue.CODEC.fieldOf("knockback").forGetter(ImpactEnchantmentEffect::knockback),
                    LevelBasedValue.CODEC.fieldOf("damage").forGetter(ImpactEnchantmentEffect::damage)
            ).apply(instance, ImpactEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        if (!(context.owner() instanceof Player player)) return;
        if (!(target instanceof LivingEntity victim)) return;
        if (victim.isAlive()) return;

        double r = this.radius.calculate(level);
        double kb = this.knockback.calculate(level);
        float dmg = (float) this.damage.calculate(level);

        Vec3 center = victim.position();

        // Particles
        world.sendParticles(ParticleTypes.EXPLOSION, center.x(), center.y(), center.z(), 1, 0, 0, 0, 0);
        world.sendParticles(ParticleTypes.SMOKE, center.x(), center.y() + 1, center.z(), 20, r, 1, r, 0.1);
        world.sendParticles(ParticleTypes.CRIT, center.x(), center.y() + 1, center.z(), 30, r, 1, r, 0.2);

        // Sound
        world.playSound(null, center.x(), center.y(), center.z(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 0.8f, 1.2f);
        world.playSound(null, center.x(), center.y(), center.z(), SoundEvents.ANVIL_LAND, SoundSource.BLOCKS, 0.5f, 0.8f);

        // Knockback & damage nearby entities
        DamageSource source = world.damageSources().playerAttack(player);
        world.getEntitiesOfClass(LivingEntity.class, victim.getBoundingBox().inflate(r))
                .stream()
                .filter(e -> e != victim && e != player)
                .forEach(e -> {
                    Vec3 dir = e.position().subtract(center).normalize();
                    e.knockback(dir.x(), dir.z(), kb, source, 1.0f, false);
                    e.hurt(world.damageSources().playerAttack(player), dmg);
                });
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
