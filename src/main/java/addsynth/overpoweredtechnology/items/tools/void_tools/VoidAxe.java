package addsynth.overpoweredtechnology.items.tools.void_tools;

import addsynth.overpoweredtechnology.items.tools.OverpoweredTiers;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class VoidAxe extends AxeItem {

  public VoidAxe(){
    super(OverpoweredTiers.VOID, 14.0f, -3.0f, new Item.Properties());
  }

  @Override
  public boolean isFoil(ItemStack stack){
    return true;
  }
  
  @Override
  public boolean isEnchantable(ItemStack stack){
    return false;
  }

  @Override
  public Rarity getRarity(ItemStack stack){
    return Rarity.EPIC;
  }

}
