package net.purejosh.ancientvessels.procedures;

import net.purejosh.ancientvessels.AncientvesselsMod;

import net.minecraft.world.entity.Entity;

import java.util.Map;

public class DragonsCurseEffectExpiresProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency entity for procedure DragonsCurseEffectExpires!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getExtraCustomData().putDouble("dragonCurseTick", 0);
	}
}
