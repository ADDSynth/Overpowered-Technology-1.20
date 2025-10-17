package addsynth.overpoweredtechnology.items.tools;

import java.util.List;
import javax.annotation.Nullable;
import addsynth.core.util.color.Colors;
import addsynth.energy.lib.items.energy.EnergyItemCapabilityProvider;
import addsynth.energy.lib.items.energy.ItemEnergy;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class LaserSword extends SwordItem {

  public static final int DEFAULT_ENERGY = Tiers.DIAMOND.getUses();

  public LaserSword(){
    super(OverpoweredTiers.LASER_SWORD, 3, -2.4f, new Item.Properties().setNoRepair());
  }

  @Override
  public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt){
    return EnergyItemCapabilityProvider.createChargeOnly(stack, DEFAULT_ENERGY);
  }

  @Override
  public float getDestroySpeed(ItemStack itemstack, BlockState blockstate){
    return ItemEnergy.hasEnergy(itemstack) ? super.getDestroySpeed(itemstack, blockstate) : 1.0f;
  }

  @Override
  public boolean hurtEnemy(ItemStack itemstack, LivingEntity target, LivingEntity attacker){
    if(ItemEnergy.hasEnergy(itemstack)){
      // set entity on fire
      target.setSecondsOnFire(4); // same as Fire Aspect Level 1
      ItemEnergy.useEnergy(itemstack, 1);
      return true;
    }
    return false;
  }

  @Override
  public boolean mineBlock(ItemStack itemstack, Level level, BlockState blockstate, BlockPos position, LivingEntity entity){
    return !level.isClientSide && blockstate.getDestroySpeed(level, position) > 0 && ItemEnergy.hasEnergy(itemstack);
  }

  @Override
  public boolean isCorrectToolForDrops(ItemStack stack, BlockState state){
    return ItemEnergy.hasEnergy(stack) ? isCorrectToolForDrops(state) : false;
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack itemstack){
    return ItemEnergy.hasEnergy(itemstack) ? super.getDefaultAttributeModifiers(slot) : ImmutableMultimap.of();
  }

  @Override
  public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> components, TooltipFlag flag){
    components.add(ItemEnergy.getEnergyComponent(itemstack));
  }

  @Override
  public boolean isBarVisible(ItemStack itemstack){
    return true;
  }

  @Override
  public int getBarColor(ItemStack itemstack){
    return Colors.WHITE.value;
  }

  @Override
  public int getBarWidth(ItemStack itemstack){
    return ItemEnergy.getBarWidth(itemstack);
  }

  @Override
  public boolean isEnchantable(ItemStack itemstack){
    return false;
  }

  @Override
  public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair){
    return false;
  }

}
