package addsynth.overpoweredtechnology.items.tools.infinity;

import addsynth.core.game.item.tool.ToolConstants;
import addsynth.overpoweredtechnology.items.tools.OverpoweredTiers;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

public final class InfinitySword extends SwordItem {

  public InfinitySword(){
    super(OverpoweredTiers.INFINITY, ToolConstants.sword_damage, ToolConstants.sword_damage, new Item.Properties().fireResistant());
  }

  @Override
  public Component getName(ItemStack itemstack){
    return ((MutableComponent)super.getName(itemstack)).withStyle(ChatFormatting.GOLD);
  }

  @Override
  public boolean isEnchantable(ItemStack stack){
    return false;
  }

  @Override
  public boolean isBarVisible(ItemStack stack){
    return false;
  }

  @Override
  public boolean isValidRepairItem(ItemStack itemstack, ItemStack repair_item){
    return false;
  }

  // https://minecraft.gamepedia.com/Attribute
  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot){

    // Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
    Multimap<Attribute, AttributeModifier> multimap = HashMultimap.<Attribute, AttributeModifier>create();
    if (equipmentSlot == EquipmentSlot.MAINHAND) {
      // Base Attack Damage is 3 (found in attackDamage field initializer in SwordClass),
      // + ToolMaterial Attack Damage, Wood & Gold = 0, Stone = 1, Iron = 2, Diamond = 3
      // + 1 Base attack for when the player doesn't have any tools equipped? I think? I'm not sure how sword damage is calculated.
      multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 11.0d, AttributeModifier.Operation.ADDITION));
      // Base Attack Speed is 4, Vanilla modifier value is -2.4 with operation 0, which adds it.
      // 4 + -2.4 = 1.6. All swords have a Attack Speed of 1.6, which is number of swings per second.
      multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -1.9d, AttributeModifier.Operation.ADDITION));
    }
    return multimap;
  }
  
}
