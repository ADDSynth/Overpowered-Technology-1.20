package addsynth.energy.gameplay.items;

import addsynth.core.util.color.MinecraftColor;
import addsynth.energy.lib.items.energy.EnergyItemCapabilityProvider;
import addsynth.energy.lib.items.energy.ItemEnergy;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public final class BatteryItem extends Item {

  private static final int battery_charge = 32_000;

  public BatteryItem(){
    super(new Item.Properties());
  }

  @Override
  @Nullable
  public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt){
    return EnergyItemCapabilityProvider.create(stack, battery_charge);
  }

  @Override
  public boolean isBarVisible(ItemStack itemstack){
    return true;
  }

  @Override
  public int getBarColor(ItemStack itemstack){
    return MinecraftColor.YELLOW.value;
  }

  @Override
  public int getBarWidth(ItemStack itemstack){
    final CompoundTag tag = itemstack.getOrCreateTag();
    final float energy = tag.getInt(ItemEnergy.ENERGY_LABEL);
    final float capacity = tag.getInt(ItemEnergy.CAPACITY_LABEL);
    return 13 - Math.round((energy / capacity) * 13);
  }

}
