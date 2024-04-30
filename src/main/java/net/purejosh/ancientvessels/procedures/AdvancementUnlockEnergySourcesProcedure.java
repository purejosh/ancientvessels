package net.purejosh.ancientvessels.procedures;

import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import javax.annotation.Nullable;

import java.util.Map;
import java.util.HashMap;
public AdvancementUnlockEnergySourcesProcedure() {
ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, damageSource, amount) -> {
Map<String, Object> dependencies = new HashMap<>();
dependencies.put("entity", entity);
dependencies.put("x", entity.getX());
dependencies.put("y", entity.getY());
dependencies.put("z", entity.getZ());
dependencies.put("world", entity.level());
dependencies.put("sourceentity", damageSource.getEntity());
dependencies.put("immediatesourceentity", damageSource.getDirectEntity());
dependencies.put("amount", amount);
execute(dependencies);
return true;
});
}
public static void execute(
Entity entity,
Entity sourceentity
) {
execute(null,entity,sourceentity);
}
private static void execute(
@Nullable Event event,
Entity entity,
Entity sourceentity
) {
if(
entity == null ||
sourceentity == null
) return ;
if (sourceentity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel && _plr0.getAdvancements()
.getOrStartProgress(_plr0.server.getAdvancements().getAdvancement(new ResourceLocation("ancientvessels:adv_find_vessel"))).isDone()) {if (entity instanceof EnderDragon) {if(sourceentity instanceof ServerPlayer _player) {
Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("ancientvessels:adv_dragon_energy"));
AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
if (!_ap.isDone()) {
for (String criteria : _ap.getRemainingCriteria())
_player.getAdvancements().award(_adv, criteria);
}
}}if (entity instanceof WitherBoss) {if(sourceentity instanceof ServerPlayer _player) {
Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("ancientvessels:adv_wither_energy"));
AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
if (!_ap.isDone()) {
for (String criteria : _ap.getRemainingCriteria())
_player.getAdvancements().award(_adv, criteria);
}
}}}
}
}
