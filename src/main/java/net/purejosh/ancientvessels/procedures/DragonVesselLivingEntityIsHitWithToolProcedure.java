package net.purejosh.ancientvessels.procedures;

import net.purejosh.ancientvessels.init.AncientvesselsModMobEffects;
import net.purejosh.ancientvessels.AncientvesselsMod;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.Map;

public class DragonVesselLivingEntityIsHitWithToolProcedure {

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency entity for procedure DragonVesselLivingEntityIsHitWithTool!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(AncientvesselsModMobEffects.DRAGONS_CURSE, 100, 1, (true), (true)));
	}
}
