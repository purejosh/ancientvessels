package net.purejosh.ancientvessels.procedures;

import net.purejosh.ancientvessels.init.AncientvesselsModItems;
import net.purejosh.ancientvessels.AncientvesselsMod;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;

import java.util.Map;

public class WitherVesselProjectileProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency world for procedure WitherVesselProjectile!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency x for procedure WitherVesselProjectile!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency y for procedure WitherVesselProjectile!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency z for procedure WitherVesselProjectile!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency entity for procedure WitherVesselProjectile!");
			return;
		}
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
				.getItem() == AncientvesselsModItems.WITHER_VESSEL) {
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem(),
						400);
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(x, y, z), SoundEvents.WITHER_SHOOT, SoundSource.PLAYERS, (float) 0.6, 1);
					} else {
						_level.playLocalSound(x, y, z, SoundEvents.WITHER_SHOOT, SoundSource.PLAYERS, (float) 0.6, 1, false);
					}
				}
			}
			{
				ItemStack _ist = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
				if (_ist.hurt(10, RandomSource.create(), null)) {
					_ist.shrink(1);
					_ist.setDamageValue(0);
				}
			}
			{
				Entity _shootFrom = entity;
				Level projectileLevel = _shootFrom.level;
				if (!projectileLevel.isClientSide()) {
					Projectile _entityToSpawn = new Object() {
						public Projectile getFireball(Level level, Entity shooter, double ax, double ay, double az) {
							AbstractHurtingProjectile entityToSpawn = new WitherSkull(EntityType.WITHER_SKULL, level);
							entityToSpawn.setOwner(shooter);
							entityToSpawn.xPower = ax;
							entityToSpawn.yPower = ay;
							entityToSpawn.zPower = az;
							return entityToSpawn;
						}
					}.getFireball(projectileLevel, entity, (entity.getLookAngle().x / 10), (entity.getLookAngle().y / 10),
							(entity.getLookAngle().z / 10));
					_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
					_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, (float) 0.1, 0);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
			}
		}
	}
}
