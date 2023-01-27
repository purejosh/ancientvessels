/*
 *	MCreator note:
 *
 *	If you lock base mod element files, you can edit this file and the proxy files
 *	and they won't get overwritten. If you change your mod package or modid, you
 *	need to apply these changes to this file MANUALLY.
 *
 *
 *	If you do not lock base mod element files in Workspace settings, this file
 *	will be REGENERATED on each build.
 *
 */
package net.purejosh.ancientvessels;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.purejosh.ancientvessels.init.AncientvesselsModProcedures;
import net.purejosh.ancientvessels.init.AncientvesselsModMobEffects;
import net.purejosh.ancientvessels.init.AncientvesselsModItems;

import net.minecraft.nbt.CompoundTag;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.api.ModInitializer;

public class AncientvesselsMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "ancientvessels";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing AncientvesselsMod");

		AncientvesselsModMobEffects.load();

		AncientvesselsModItems.load();

		AncientvesselsModProcedures.load();

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			if (handler.getPlayer().getExtraCustomData().getCompound("PlayerPersisted").isEmpty()) {
				handler.getPlayer().getExtraCustomData().put("PlayerPersisted", new CompoundTag());
			}
			AncientvesselsMod.LOGGER.info(handler.getPlayer().getExtraCustomData());
		});
	}
}
