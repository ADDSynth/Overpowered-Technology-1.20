package addsynth.overpoweredtechnology.items.tools.celestial;

import addsynth.overpoweredtechnology.items.tools.OverpoweredTiers;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public final class CelestialAxe extends AxeItem {

  public CelestialAxe(){
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
