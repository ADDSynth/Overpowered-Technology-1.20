package addsynth.overpoweredtechnology.items.tools.infinity;

import addsynth.core.game.item.tool.ToolConstants;
import addsynth.overpoweredtechnology.items.tools.OverpoweredTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;

public final class InfinityShovel extends ShovelItem {

  public InfinityShovel(){
    super(OverpoweredTiers.INFINITY, ToolConstants.axe_damage, ToolConstants.axe_speed, new Item.Properties().fireResistant());
  }

  @Override
  public boolean isEnchantable(ItemStack stack){
    return false;
  }

  @Override
  public Component getName(ItemStack itemstack){
    return ((MutableComponent)super.getName(itemstack)).withStyle(ChatFormatting.GOLD);
  }

}
