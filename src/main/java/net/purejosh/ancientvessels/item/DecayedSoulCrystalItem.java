
package net.purejosh.ancientvessels.item;

import net.purejosh.ancientvessels.procedures.DecayedSoulCrystalItemInHandTickProcedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.util.List;

public class DecayedSoulCrystalItem extends Item {
	public DecayedSoulCrystalItem() {
		super(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).stacksTo(16).rarity(Rarity.UNCOMMON));
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A77A strange crystal that"));
		list.add(Component.literal("\u00A77seems to echo a faint cry"));
		list.add(Component.literal("\u00A77when held in your hand."));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			DecayedSoulCrystalItemInHandTickProcedure.execute(com.google.common.collect.ImmutableMap.<String, Object>builder().put("x", entity.getX())
					.put("y", entity.getY()).put("z", entity.getZ()).put("world", world).build());
	}
}
