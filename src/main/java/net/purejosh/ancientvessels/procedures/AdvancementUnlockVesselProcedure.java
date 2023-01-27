package net.purejosh.ancientvessels.procedures;

import net.purejosh.ancientvessels.AncientvesselsMod;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

import io.github.fabricators_of_create.porting_lib.event.common.AdvancementCallback;

public class AdvancementUnlockVesselProcedure {
	public AdvancementUnlockVesselProcedure() {
		AdvancementCallback.EVENT.register((player, advancement) -> {
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("entity", player);
			dependencies.put("advancement", advancement);
			dependencies.put("x", player.getX());
			dependencies.put("y", player.getY());
			dependencies.put("z", player.getZ());
			dependencies.put("world", player.level);
			execute(dependencies);
		});
	}

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AncientvesselsMod.LOGGER.warn("Failed to load dependency entity for procedure AdvancementUnlockVessel!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof ServerPlayer _plr && _plr.level instanceof ServerLevel
				? _plr.getAdvancements()
						.getOrStartProgress(_plr.server.getAdvancements().getAdvancement(new ResourceLocation("minecraft:end/kill_dragon"))).isDone()
				: false) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("ancientvessels:adv_unlock_start"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemainingCriteria().iterator();
					while (_iterator.hasNext())
						_player.getAdvancements().award(_adv, (String) _iterator.next());
				}
			}
		}
	}
}
