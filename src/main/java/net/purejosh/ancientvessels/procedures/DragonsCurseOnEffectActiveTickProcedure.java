package net.purejosh.ancientvessels.procedures;

import net.purejosh.ancientvessels.AncientvesselsMod;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;

import java.util.Map;

public class DragonsCurseOnEffectActiveTickProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency entity for procedure DragonsCurseOnEffectActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getExtraCustomData().putDouble("dragonCurseTick", (entity.getExtraCustomData().getDouble("dragonCurseTick") + 1));
		if (entity.getExtraCustomData().getDouble("dragonCurseTick") == 20) {
			if (entity instanceof LivingEntity _entity)
				_entity.hurt(new DamageSource("dragonscurse").bypassArmor(), 1);
			entity.getExtraCustomData().putDouble("dragonCurseTick", 1);
		}
	}
}
