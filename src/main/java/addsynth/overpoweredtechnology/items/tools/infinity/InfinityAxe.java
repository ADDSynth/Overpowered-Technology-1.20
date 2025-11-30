package addsynth.overpoweredtechnology.items.tools.infinity;

import addsynth.overpoweredtechnology.items.tools.OverpoweredTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class InfinityAxe extends AxeItem {

  public InfinityAxe(){
    super(OverpoweredTiers.INFINITY, 6.0f, -3.0f, new Item.Properties().fireResistant());
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
