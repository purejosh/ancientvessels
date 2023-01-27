package net.purejosh.ancientvessels.procedures;

import net.purejosh.ancientvessels.init.AncientvesselsModItems;
import net.purejosh.ancientvessels.AncientvesselsMod;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.Map;

public class VesselLivingEntityIsHitWithToolProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency world for procedure VesselLivingEntityIsHitWithTool!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency x for procedure VesselLivingEntityIsHitWithTool!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency y for procedure VesselLivingEntityIsHitWithTool!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency z for procedure VesselLivingEntityIsHitWithTool!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency entity for procedure VesselLivingEntityIsHitWithTool!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency sourceentity for procedure VesselLivingEntityIsHitWithTool!");
			return;
		}
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (!entity.isAlive()) {
			if (entity instanceof WitherBoss) {
				if (sourceentity instanceof Player) {
					if (world instanceof Level _level && !_level.isClientSide()) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(AncientvesselsModItems.DECAYED_SOUL_CRYSTAL));
						entityToSpawn.setPickUpDelay(10);
						_level.addFreshEntity(entityToSpawn);
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, new BlockPos(x, y, z), SoundEvents.WITHER_SHOOT, SoundSource.AMBIENT, 1, (float) 0.4);
						} else {
							_level.playLocalSound(x, y, z, SoundEvents.WITHER_SHOOT, SoundSource.AMBIENT, 1, (float) 0.4, false);
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.END_ROD, x, y, z, 400, 0.4, 0.4, 0.4, 0.1);
					new Object() {
						private int ticks = 0;

						public void startDelay(LevelAccessor world) {
							ServerTickEvents.END_SERVER_TICK.register((server) -> {
								this.ticks++;
								if (this.ticks == 4) {
									if (entity instanceof Player _player && !_player.level.isClientSide())
										_player.displayClientMessage(Component.literal("\u00A77\u00A7oYou hear something drop..."), (false));
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
