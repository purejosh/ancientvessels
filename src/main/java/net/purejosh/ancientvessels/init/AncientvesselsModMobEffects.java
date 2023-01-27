
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.purejosh.ancientvessels.init;

import net.purejosh.ancientvessels.potion.DragonsCurseMobEffect;
import net.purejosh.ancientvessels.AncientvesselsMod;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public class AncientvesselsModMobEffects {
	public static MobEffect DRAGONS_CURSE;

	public static void load() {
		DRAGONS_CURSE = Registry.register(Registry.MOB_EFFECT, new ResourceLocation(AncientvesselsMod.MODID, "dragons_curse"),
				new DragonsCurseMobEffect());
	}
}
