/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.purejosh.ancientvessels.init;

import net.purejosh.ancientvessels.item.WitherVesselItem;
import net.purejosh.ancientvessels.item.WitherIconItem;
import net.purejosh.ancientvessels.item.DragonVesselItem;
import net.purejosh.ancientvessels.item.DraconicVessel3Item;
import net.purejosh.ancientvessels.item.DraconicVessel2Item;
import net.purejosh.ancientvessels.item.DraconicVessel1Item;
import net.purejosh.ancientvessels.item.DraconicSoulOrbItem;
import net.purejosh.ancientvessels.item.DecayedVessel3Item;
import net.purejosh.ancientvessels.item.DecayedVessel2Item;
import net.purejosh.ancientvessels.item.DecayedVessel1Item;
import net.purejosh.ancientvessels.item.DecayedSoulCrystalItem;
import net.purejosh.ancientvessels.item.AncientVesselItem;
import net.purejosh.ancientvessels.AncientvesselsMod;

import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

public class AncientvesselsModItems {
	public static Item ANCIENT_VESSEL;
	public static Item DECAYED_VESSEL_1;
	public static Item DECAYED_VESSEL_2;
	public static Item DECAYED_VESSEL_3;
	public static Item WITHER_VESSEL;
	public static Item DRACONIC_VESSEL_1;
	public static Item DRACONIC_VESSEL_2;
	public static Item DRACONIC_VESSEL_3;
	public static Item DRAGON_VESSEL;
	public static Item DECAYED_SOUL_CRYSTAL;
	public static Item DRACONIC_SOUL_ORB;
	public static Item WITHER_ICON;

	public static void load() {
		ANCIENT_VESSEL = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "ancient_vessel"), new AncientVesselItem());
		DECAYED_VESSEL_1 = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "decayed_vessel_1"), new DecayedVessel1Item());
		DECAYED_VESSEL_2 = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "decayed_vessel_2"), new DecayedVessel2Item());
		DECAYED_VESSEL_3 = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "decayed_vessel_3"), new DecayedVessel3Item());
		WITHER_VESSEL = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "wither_vessel"), new WitherVesselItem());
		DRACONIC_VESSEL_1 = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "draconic_vessel_1"), new DraconicVessel1Item());
		DRACONIC_VESSEL_2 = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "draconic_vessel_2"), new DraconicVessel2Item());
		DRACONIC_VESSEL_3 = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "draconic_vessel_3"), new DraconicVessel3Item());
		DRAGON_VESSEL = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "dragon_vessel"), new DragonVesselItem());
		DECAYED_SOUL_CRYSTAL = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "decayed_soul_crystal"), new DecayedSoulCrystalItem());
		DRACONIC_SOUL_ORB = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "draconic_soul_orb"), new DraconicSoulOrbItem());
		WITHER_ICON = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AncientvesselsMod.MODID, "wither_icon"), new WitherIconItem());
	}

	public static void clientLoad() {
	}
}
