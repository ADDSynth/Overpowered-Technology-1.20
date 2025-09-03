package addsynth.overpoweredtechnology.items.tools.celestial;

import addsynth.core.game.item.tool.ToolConstants;
import addsynth.overpoweredtechnology.items.tools.OverpoweredTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;

public final class CelestialShovel extends ShovelItem {

  public CelestialShovel(){
    super(OverpoweredTiers.CELESTIAL, ToolConstants.axe_damage, ToolConstants.axe_speed, new Item.Properties());
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
