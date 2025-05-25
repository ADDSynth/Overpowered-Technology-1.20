package addsynth.overpoweredmod.items.basic;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class PlasmaItem extends Item {

  public PlasmaItem(){
    super(new Item.Properties());
  }

  @Override
  public Component getName(ItemStack stack){
    return ((MutableComponent)super.getName(stack)).withStyle(ChatFormatting.AQUA);
  }

}
