
package net.purejosh.ancientvessels.item;

import net.purejosh.ancientvessels.procedures.VesselLivingEntityIsHitWithToolProcedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.chat.Component;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import java.util.List;

public class DecayedVessel3Item extends SwordItem {
	public DecayedVessel3Item() {
		super(new Tier() {
			public int getUses() {
				return 784;
			}

			public float getSpeed() {
				return 4f;
			}

			public float getAttackDamageBonus() {
				return 4f;
			}

			public int getLevel() {
				return 1;
			}

			public int getEnchantmentValue() {
				return 10;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.EMPTY;
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
		VesselLivingEntityIsHitWithToolProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("world", world)
				.put("x", x).put("y", y).put("z", z).put("entity", entity).put("sourceentity", sourceentity).build());
		return retval;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A77An Ancient Vessel infused with a"));
		list.add(Component.literal("\u00A77greater amount of Wither energy."));
	}
}
