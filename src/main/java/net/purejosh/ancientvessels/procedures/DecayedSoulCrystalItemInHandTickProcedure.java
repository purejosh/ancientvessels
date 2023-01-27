package net.purejosh.ancientvessels.procedures;

import net.purejosh.ancientvessels.AncientvesselsMod;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;

import java.util.Map;

public class DecayedSoulCrystalItemInHandTickProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency world for procedure DecayedSoulCrystalItemInHandTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency x for procedure DecayedSoulCrystalItemInHandTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency y for procedure DecayedSoulCrystalItemInHandTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency z for procedure DecayedSoulCrystalItemInHandTick!");
			return;
		}
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if (Mth.nextDouble(RandomSource.create(), 0, 200) < 1) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos(x, y, z), SoundEvents.WITHER_AMBIENT, SoundSource.PLAYERS, (float) 0.25, (float) 0.1);
				} else {
					_level.playLocalSound(x, y, z, SoundEvents.WITHER_AMBIENT, SoundSource.PLAYERS, (float) 0.25, (float) 0.1, false);
				}
			}
		}
	}
}
