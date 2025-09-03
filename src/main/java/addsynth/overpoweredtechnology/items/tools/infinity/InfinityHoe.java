package addsynth.overpoweredtechnology.items.tools.infinity;

import addsynth.overpoweredtechnology.items.tools.OverpoweredTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InfinityHoe extends HoeItem {

  public InfinityHoe(){
    super(OverpoweredTiers.INFINITY, -4, 0.0f, new Item.Properties());
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
