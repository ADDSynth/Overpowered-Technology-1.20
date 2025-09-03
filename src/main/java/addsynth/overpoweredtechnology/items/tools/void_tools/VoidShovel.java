package addsynth.overpoweredtechnology.items.tools.void_tools;

import addsynth.core.game.item.tool.ToolConstants;
import addsynth.overpoweredtechnology.items.tools.OverpoweredTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;

public class VoidShovel extends ShovelItem {

  public VoidShovel(){
    super(OverpoweredTiers.VOID, ToolConstants.shovel_damage, ToolConstants.shovel_speed, new Item.Properties());
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
