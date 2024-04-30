
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.purejosh.ancientvessels.init;

import net.purejosh.ancientvessels.procedures.WitherVesselProjectileProcedure;
import net.purejosh.ancientvessels.procedures.WitherVesselLivingEntityIsHitWithToolProcedure;
import net.purejosh.ancientvessels.procedures.WitherSkeletonOnDeathEventProcedure;
import net.purejosh.ancientvessels.procedures.VesselLivingEntityIsHitWithToolProcedure;
import net.purejosh.ancientvessels.procedures.DragonsCurseOnEffectActiveTickProcedure;
import net.purejosh.ancientvessels.procedures.DragonsCurseEffectExpiresProcedure;
import net.purejosh.ancientvessels.procedures.DragonVesselProjectileProcedure;
import net.purejosh.ancientvessels.procedures.DragonVesselLivingEntityIsHitWithToolProcedure;
import net.purejosh.ancientvessels.procedures.DragonOnDeathEventProcedure;
import net.purejosh.ancientvessels.procedures.DraconicSoulOrbItemInHandTickProcedure;
import net.purejosh.ancientvessels.procedures.DecayedSoulCrystalItemInHandTickProcedure;
import net.purejosh.ancientvessels.procedures.AdvancementUnlockEnergySourcesProcedure;

@SuppressWarnings("InstantiationOfUtilityClass")
public class AncientvesselsModProcedures {
	public static void load() {
		new WitherVesselLivingEntityIsHitWithToolProcedure();
		new WitherSkeletonOnDeathEventProcedure();
		new DraconicSoulOrbItemInHandTickProcedure();
		new VesselLivingEntityIsHitWithToolProcedure();
		new DragonOnDeathEventProcedure();
		new DragonsCurseOnEffectActiveTickProcedure();
		new DragonVesselLivingEntityIsHitWithToolProcedure();
		new DragonsCurseEffectExpiresProcedure();
		new WitherVesselProjectileProcedure();
		new DragonVesselProjectileProcedure();
		new DecayedSoulCrystalItemInHandTickProcedure();
		new AdvancementUnlockEnergySourcesProcedure();
	}
}
