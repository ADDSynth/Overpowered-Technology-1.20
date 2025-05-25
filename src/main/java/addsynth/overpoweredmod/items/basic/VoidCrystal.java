package addsynth.overpoweredmod.items.basic;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class VoidCrystal extends Item {

  public VoidCrystal(){
    super(new Item.Properties());
  }

  @Override
  public boolean isFoil(ItemStack stack){
    return true;
  }

}
