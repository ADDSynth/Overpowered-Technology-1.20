package addsynth.overpoweredmod.items.tools;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public final class OverpoweredAxe extends AxeItem {

  public OverpoweredAxe(){
    super(OverpoweredTiers.CELESTIAL, 14.0f, -3.0f, new Item.Properties());
  }

  @Override
  public boolean isEnchantable(ItemStack stack){
    return false;
  }

  @Override
  public Rarity getRarity(ItemStack stack){
    return Rarity.RARE;
  }
}
