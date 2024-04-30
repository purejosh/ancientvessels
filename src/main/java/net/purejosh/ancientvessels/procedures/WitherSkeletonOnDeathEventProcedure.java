package net.purejosh.ancientvessels.procedures;

import net.purejosh.ancientvessels.init.AncientvesselsModItems;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import javax.annotation.Nullable;

import java.util.Map;
import java.util.HashMap;
public WitherSkeletonOnDeathEventProcedure() {
ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, amount) -> {
Map<String, Object> dependencies = new HashMap<>();
dependencies.put("entity", entity);
dependencies.put("x", entity.getX());
dependencies.put("y", entity.getY());
dependencies.put("z", entity.getZ());
dependencies.put("world", entity.level());
dependencies.put("sourceentity", damageSource.getEntity());
execute(dependencies);
return true;
});
}
public static void execute(
LevelAccessor world,
double x,
double y,
double z,
Entity entity,
Entity sourceentity
) {
execute(null,world,x,y,z,entity,sourceentity);
}
private static void execute(
@Nullable Event event,
LevelAccessor world,
double x,
double y,
double z,
Entity entity,
Entity sourceentity
) {
if(
entity == null ||
sourceentity == null
) return ;
double particleRadius = 0;double particleAmount = 0;
if (entity instanceof WitherSkeleton) {if (sourceentity instanceof Player) {if (sourceentity instanceof ServerPlayer _plr2 && _plr2.level() instanceof ServerLevel && _plr2.getAdvancements()
.getOrStartProgress(_plr2.server.getAdvancements().getAdvancement(new ResourceLocation("ancientvessels:adv_learn_ancient_vessel"))).isDone()) {if (Mth.nextDouble(RandomSource.create(), 0, 100)<1) {if (world instanceof ServerLevel _level) {
ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(AncientvesselsModItems.ANCIENT_VESSEL));
entityToSpawn.setPickUpDelay(10);
_level.addFreshEntity(entityToSpawn);
}if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.wither.death")),
SoundSource.AMBIENT, (float)0.6, 2);
} else {
_level.playLocalSound(x, y, z,
BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.wither.death")),
SoundSource.AMBIENT, (float)0.6, 2, false);
}
}if (world instanceof ServerLevel _level)
_level.sendParticles(ParticleTypes.SOUL, x, y, z, 360, 0.33, 0.33, 0.33, 0.1);if(sourceentity instanceof ServerPlayer _player) {
Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("ancientvessels:adv_find_vessel"));
AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
if (!_ap.isDone()) {
for (String criteria : _ap.getRemainingCriteria())
_player.getAdvancements().award(_adv, criteria);
}
}}}}}
}
}
