package net.purejosh.ancientvessels.procedures;

import net.purejosh.ancientvessels.init.AncientvesselsModItems;
import net.purejosh.ancientvessels.AncientvesselsMod;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

public class WitherSkeletonOnDeathEventProcedure {
	public WitherSkeletonOnDeathEventProcedure() {
		ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, amount) -> {
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("entity", entity);
			dependencies.put("x", entity.getX());
			dependencies.put("y", entity.getY());
			dependencies.put("z", entity.getZ());
			dependencies.put("world", entity.level);
			dependencies.put("sourceentity", damageSource.getEntity());
			execute(dependencies);
			return true;
		});
	}

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency world for procedure WitherSkeletonOnDeathEvent!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency x for procedure WitherSkeletonOnDeathEvent!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency y for procedure WitherSkeletonOnDeathEvent!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency z for procedure WitherSkeletonOnDeathEvent!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency entity for procedure WitherSkeletonOnDeathEvent!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency sourceentity for procedure WitherSkeletonOnDeathEvent!");
			return;
		}
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double particleRadius = 0;
		double particleAmount = 0;
		if (entity instanceof WitherSkeleton) {
			if (sourceentity instanceof Player) {
				if (sourceentity instanceof ServerPlayer _plr && _plr.level instanceof ServerLevel
						? _plr.getAdvancements()
								.getOrStartProgress(_plr.server.getAdvancements().getAdvancement(new ResourceLocation("minecraft:end/kill_dragon")))
								.isDone()
						: false) {
					if (Mth.nextDouble(RandomSource.create(), 0, 100) < 1) {
						if (world instanceof Level _level && !_level.isClientSide()) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(AncientvesselsModItems.ANCIENT_VESSEL));
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(x, y, z), SoundEvents.WITHER_DEATH, SoundSource.AMBIENT, (float) 0.3, 2);
							} else {
								_level.playLocalSound(x, y, z, SoundEvents.WITHER_DEATH, SoundSource.AMBIENT, (float) 0.3, 2, false);
							}
						}
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.SOUL, x, y, z, 160, 0.33, 0.33, 0.33, 0.1);
						if (sourceentity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements()
									.getAdvancement(new ResourceLocation("ancientvessels:adv_find_vessel"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemainingCriteria().iterator();
								while (_iterator.hasNext())
									_player.getAdvancements().award(_adv, (String) _iterator.next());
							}
						}
					}
				}
			}
		}
	}
}
