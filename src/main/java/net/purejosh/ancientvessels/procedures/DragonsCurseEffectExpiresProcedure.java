package net.purejosh.ancientvessels.procedures;

import net.minecraft.world.entity.Entity;

public class DragonsCurseEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putDouble("dragonCurseTick", 0);
	}
}
