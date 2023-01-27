
package net.purejosh.ancientvessels.potion;

import net.purejosh.ancientvessels.procedures.DragonsCurseOnEffectActiveTickProcedure;
import net.purejosh.ancientvessels.procedures.DragonsCurseEffectExpiresProcedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class DragonsCurseMobEffect extends MobEffect {
	public DragonsCurseMobEffect() {
		super(MobEffectCategory.HARMFUL, -6750055);
	}

	@Override
	public String getDescriptionId() {
		return "effect.ancientvessels.dragons_curse";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		Level world = entity.level;
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		DragonsCurseOnEffectActiveTickProcedure
				.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("entity", entity).build());
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		Level world = entity.level;
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		DragonsCurseEffectExpiresProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("entity", entity).build());
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
