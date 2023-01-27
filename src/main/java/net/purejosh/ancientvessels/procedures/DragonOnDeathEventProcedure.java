package net.purejosh.ancientvessels.procedures;

import net.purejosh.ancientvessels.init.AncientvesselsModItems;
import net.purejosh.ancientvessels.AncientvesselsMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import java.util.Map;
import java.util.HashMap;

public class DragonOnDeathEventProcedure {
	public DragonOnDeathEventProcedure() {
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
				AncientvesselsMod.LOGGER.warn("Failed to load dependency world for procedure DragonOnDeathEvent!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency entity for procedure DragonOnDeathEvent!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency sourceentity for procedure DragonOnDeathEvent!");
			return;
		}
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (entity instanceof EnderDragon) {
			if (sourceentity instanceof Player) {
				if (!world
						.getEntitiesOfClass(EnderDragon.class,
								AABB.ofSize(new Vec3((sourceentity.getX()), (sourceentity.getY()), (sourceentity.getZ())), 10, 10, 10), e -> true)
						.isEmpty() == true) {
					if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
							.getItem() == AncientvesselsModItems.ANCIENT_VESSEL
							|| (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
									.getItem() == AncientvesselsModItems.DRACONIC_VESSEL_1
							|| (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
									.getItem() == AncientvesselsModItems.DRACONIC_VESSEL_2
							|| (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
									.getItem() == AncientvesselsModItems.DRACONIC_VESSEL_3) {
						new Object() {
							private int ticks = 0;

							public void startDelay(LevelAccessor world) {
								ServerTickEvents.END_SERVER_TICK.register((server) -> {
									this.ticks++;
									if (this.ticks == 200) {
										if (world instanceof Level _level) {
											if (!_level.isClientSide()) {
												_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ()),
														SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.NEUTRAL, 4, (float) 0.4);
											} else {
												_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()),
														SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.NEUTRAL, 4, (float) 0.4, false);
											}
										}
										for (int index0 = 0; index0 < (int) (40); index0++) {
											if (world instanceof ServerLevel _level)
												_level.sendParticles(ParticleTypes.END_ROD,
														(entity.getX() + Mth.nextDouble(RandomSource.create(), -1, 1) * 4),
														(entity.getY() + Mth.nextDouble(RandomSource.create(), -1, 1) * 4),
														(entity.getZ() + Mth.nextDouble(RandomSource.create(), -1, 1) * 4), 200,
														(Mth.nextDouble(RandomSource.create(), -1, 1)),
														(Mth.nextDouble(RandomSource.create(), -4, 1)),
														(Mth.nextDouble(RandomSource.create(), -1, 1)), 2);
										}
										new Object() {
											private int ticks = 0;

											public void startDelay(LevelAccessor world) {
												ServerTickEvents.END_SERVER_TICK.register((server) -> {
													this.ticks++;
													if (this.ticks == 4) {
														if (world instanceof Level _level && !_level.isClientSide()) {
															ItemEntity entityToSpawn = new ItemEntity(_level, (entity.getX()), (entity.getY()),
																	(entity.getZ()), new ItemStack(AncientvesselsModItems.DRACONIC_SOUL_ORB));
															entityToSpawn.setPickUpDelay(10);
															_level.addFreshEntity(entityToSpawn);
														}
														return;
													}
												});
											}
										}.startDelay(world);
										new Object() {
											private int ticks = 0;

											public void startDelay(LevelAccessor world) {
												ServerTickEvents.END_SERVER_TICK.register((server) -> {
													this.ticks++;
													if (this.ticks == 30) {
														if (sourceentity instanceof Player _player && !_player.level.isClientSide())
															_player.displayClientMessage(
																	Component.literal("\u00A77\u00A7oYou hear something fall from the sky..."),
																	(false));
														return;
													}
												});
											}
										}.startDelay(world);
										return;
									}
								});
							}
						}.startDelay(world);
					}
				}
			}
		}
	}
}
