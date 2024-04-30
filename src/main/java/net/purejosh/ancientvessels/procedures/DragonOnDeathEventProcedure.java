package net.purejosh.ancientvessels.procedures;

import net.purejosh.ancientvessels.init.AncientvesselsModItems;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import javax.annotation.Nullable;

import java.util.Map;
import java.util.HashMap;
public DragonOnDeathEventProcedure() {
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
Entity entity,
Entity sourceentity
) {
execute(null,world,entity,sourceentity);
}
private static void execute(
@Nullable Event event,
LevelAccessor world,
Entity entity,
Entity sourceentity
) {
if(
entity == null ||
sourceentity == null
) return ;
if (entity instanceof EnderDragon) {if (sourceentity instanceof Player) {if(sourceentity instanceof ServerPlayer _player) {
Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("ancientvessels:adv_learn_ancient_vessel"));
AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
if (!_ap.isDone()) {
for (String criteria : _ap.getRemainingCriteria())
_player.getAdvancements().award(_adv, criteria);
}
}if (!world.getEntitiesOfClass(EnderDragon.class,
AABB.ofSize(new Vec3((sourceentity.getX()), (sourceentity.getY()), (sourceentity.getZ())), 10, 10, 10), e -> true)
.isEmpty()==true) {if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem():ItemStack.EMPTY).getItem() == AncientvesselsModItems.ANCIENT_VESSEL||(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem():ItemStack.EMPTY).getItem() == AncientvesselsModItems.DRACONIC_VESSEL_1||(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem():ItemStack.EMPTY).getItem() == AncientvesselsModItems.DRACONIC_VESSEL_2||(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem():ItemStack.EMPTY).getItem() == AncientvesselsModItems.DRACONIC_VESSEL_3) {new Object() {
private int ticks = 0;
public void startDelay(LevelAccessor world) {
ServerTickEvents.END_SERVER_TICK.register((server) -> {
this.ticks++;
if (this.ticks == 200) {
if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(sourceentity.getX(),sourceentity.getY(),sourceentity.getZ()),
BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.dragon_fireball.explode")),
SoundSource.NEUTRAL, 1, (float)0.4);
} else {
_level.playLocalSound((sourceentity.getX()), (sourceentity.getY()), (sourceentity.getZ()),
BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.dragon_fireball.explode")),
SoundSource.NEUTRAL, 1, (float)0.4, false);
}
}if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(sourceentity.getX(),sourceentity.getY(),sourceentity.getZ()),
BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.dragon_fireball.explode")),
SoundSource.NEUTRAL, 1, (float)0.6);
} else {
_level.playLocalSound((sourceentity.getX()), (sourceentity.getY()), (sourceentity.getZ()),
BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.dragon_fireball.explode")),
SoundSource.NEUTRAL, 1, (float)0.6, false);
}
}if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(sourceentity.getX(),sourceentity.getY(),sourceentity.getZ()),
BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.dragon_fireball.explode")),
SoundSource.NEUTRAL, 1, (float)0.8);
} else {
_level.playLocalSound((sourceentity.getX()), (sourceentity.getY()), (sourceentity.getZ()),
BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.dragon_fireball.explode")),
SoundSource.NEUTRAL, 1, (float)0.8, false);
}
}for (int index0 = 0; index0<40; index0++) {if (world instanceof ServerLevel _level)
_level.sendParticles(ParticleTypes.END_ROD, (entity.getX()+Mth.nextDouble(RandomSource.create(), -1, 1)*4), (entity.getY()+Mth.nextDouble(RandomSource.create(), -1, 1)*4), (entity.getZ()+Mth.nextDouble(RandomSource.create(), -1, 1)*4), 200, (Mth.nextDouble(RandomSource.create(), -1, 1)), (Mth.nextDouble(RandomSource.create(), -4, 1)), (Mth.nextDouble(RandomSource.create(), -1, 1)), 2);}new Object() {
private int ticks = 0;
public void startDelay(LevelAccessor world) {
ServerTickEvents.END_SERVER_TICK.register((server) -> {
this.ticks++;
if (this.ticks == 4) {
if (world instanceof ServerLevel _level) {
ItemEntity entityToSpawn = new ItemEntity(_level, (entity.getX()), (entity.getY()), (entity.getZ()), new ItemStack(AncientvesselsModItems.DRACONIC_SOUL_ORB));
entityToSpawn.setPickUpDelay(10);
_level.addFreshEntity(entityToSpawn);
}
return;
}
});
}
}.startDelay(world);
new Object() {
private int ticks = 0;
public void startDelay(LevelAccessor world) {
ServerTickEvents.END_SERVER_TICK.register((server) -> {
this.ticks++;
if (this.ticks == 30) {
if (sourceentity instanceof Player _player && !_player.level().isClientSide())
_player.displayClientMessage(Component.literal("\u00A77\u00A7oYou hear something fall from the sky..."), false);
return;
}
});
}
}.startDelay(world);
return;
}
});
}
}.startDelay(world);
}}}}
}
}
