package addsynth.overpoweredtechnology.items.tools.celestial;

import addsynth.core.game.item.tool.ToolConstants;
import addsynth.overpoweredtechnology.items.tools.OverpoweredTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;

public class CelestialPickaxe extends PickaxeItem {

  public CelestialPickaxe(){
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
