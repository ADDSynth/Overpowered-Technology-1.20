package addsynth.overpoweredmod.items.tools;

import addsynth.core.game.item.constants.ToolConstants;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;

public class OverpoweredPickaxe extends PickaxeItem {

  public OverpoweredPickaxe(){
    super(OverpoweredTiers.CELESTIAL_PICKAXE, ToolConstants.pickaxe_damage, ToolConstants.pickaxe_speed,
      new Item.Properties());
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
