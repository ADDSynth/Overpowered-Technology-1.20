package addsynth.overpoweredtechnology.items.tools.celestial;

import addsynth.overpoweredtechnology.items.tools.OverpoweredTiers;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class CelestialHoe extends HoeItem {

  public CelestialHoe(){
    super(OverpoweredTiers.CELESTIAL, -4, 0.0f, new Item.Properties());
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
