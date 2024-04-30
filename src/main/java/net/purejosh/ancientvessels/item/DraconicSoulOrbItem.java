
package net.purejosh.ancientvessels.item;

import net.purejosh.ancientvessels.procedures.DraconicSoulOrbItemInHandTickProcedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import java.util.List;

public class DraconicSoulOrbItem extends Item {
	public DraconicSoulOrbItem() {
		super(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON));
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(content -> content.accept(this));
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A77A strange orb that seems"));
		list.add(Component.literal("\u00A77to resonate a deep roar"));
		list.add(Component.literal("\u00A77when held in your hand."));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			DraconicSoulOrbItemInHandTickProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ());
	}
}
