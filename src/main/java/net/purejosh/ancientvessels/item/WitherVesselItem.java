
package net.purejosh.ancientvessels.item;

import net.purejosh.ancientvessels.procedures.WitherVesselProjectileProcedure;
import net.purejosh.ancientvessels.procedures.WitherVesselLivingEntityIsHitWithToolProcedure;
import net.purejosh.ancientvessels.init.AncientvesselsModItems;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import java.util.List;

public class WitherVesselItem extends SwordItem {
	public WitherVesselItem() {
		super(new Tier() {
			public int getUses() {
				return 2031;
			}

			public float getSpeed() {
				return 4f;
			}

			public float getAttackDamageBonus() {
				return 6f;
			}

			public int getLevel() {
				return 1;
			}

			public int getEnchantmentValue() {
				return 15;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(AncientvesselsModItems.DECAYED_SOUL_CRYSTAL));
			}
		}, 3, -2.5999999999999999f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant());
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		Level world = entity.level;
		WitherVesselLivingEntityIsHitWithToolProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("world", world)
				.put("x", x).put("y", y).put("z", z).put("entity", entity).put("sourceentity", sourceentity).build());
		return retval;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		WitherVesselProjectileProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("x", entity.getX())
				.put("y", entity.getY()).put("z", entity.getZ()).put("world", world).put("entity", entity).build());
		return ar;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A77Wither's Touch"));
		list.add(Component.literal("\u00A78Uses Wither effect to"));
		list.add(Component.literal("\u00A78deal damage over time."));
		list.add(Component.literal("\u00A77Wither Skull \u00A7eRIGHT CLICK"));
		list.add(Component.literal("\u00A78Shoots a Wither Skull"));
		list.add(Component.literal("\u00A78Durability cost: \u00A7310"));
		list.add(Component.literal("\u00A78Cooldown: \u00A7a20s"));
	}
}
