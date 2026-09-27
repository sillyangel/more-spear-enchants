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
import net.minecraft.world.phys.Vec3;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public record MomentumEnchantmentEffect(LevelBasedValue damageBonusPerStack, LevelBasedValue maxStacks, LevelBasedValue stackDuration) implements EnchantmentEntityEffect {

    public static final MapCodec<MomentumEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    LevelBasedValue.CODEC.fieldOf("damage_bonus_per_stack").forGetter(MomentumEnchantmentEffect::damageBonusPerStack),
                    LevelBasedValue.CODEC.fieldOf("max_stacks").forGetter(MomentumEnchantmentEffect::maxStacks),
                    LevelBasedValue.CODEC.fieldOf("stack_duration").forGetter(MomentumEnchantmentEffect::stackDuration)
            ).apply(instance, MomentumEnchantmentEffect::new)
    );

    // Track momentum stacks per player: UUID -> (stacks, lastHitTime)
    private static final ConcurrentHashMap<UUID, MomentumData> MOMENTUM_TRACKER = new ConcurrentHashMap<>();

    private record MomentumData(int stacks, long lastHitTime) {}

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        if (!(context.owner() instanceof Player player)) return;
        if (!(target instanceof LivingEntity victim)) return;
        if (victim.isAlive()) return; // Only on kill

        UUID playerId = player.getUUID();
        long now = world.getGameTime();
        int maxStacks = (int) this.maxStacks.calculate(level);
        float bonusPerStack = (float) this.damageBonusPerStack.calculate(level);
        int durationTicks = (int) (this.stackDuration.calculate(level) * 20); // Convert seconds to ticks

        MOMENTUM_TRACKER.compute(playerId, (id, data) -> {
            int stacks = 0;
            if (data != null && now - data.lastHitTime() <= durationTicks) {
                stacks = Math.min(data.stacks() + 1, maxStacks);
            } else {
                stacks = 1;
            }
            return new MomentumData(stacks, now);
        });

        // Apply damage bonus to the killing blow (retroactively not possible, so apply to next hit via attribute modifier)
        // Instead, heal player as visual feedback for the stack gain
        float healAmount = 0.5f * MOMENTUM_TRACKER.get(playerId).stacks();
        float newHealth = Math.min(player.getHealth() + healAmount, player.getMaxHealth());
        player.setHealth(newHealth);
    }

    // Call this from a separate mixin/event to apply the damage bonus on attack
    public static float getDamageBonus(Player player, int level, MomentumEnchantmentEffect effect) {
        MomentumData data = MOMENTUM_TRACKER.get(player.getUUID());
        if (data == null) return 0f;
        long now = player.level().getGameTime();
        int durationTicks = (int) (effect.stackDuration.calculate(level) * 20);
        if (now - data.lastHitTime() > durationTicks) return 0f;
        return data.stacks() * (float) effect.damageBonusPerStack.calculate(level);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
