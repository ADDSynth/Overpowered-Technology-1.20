package addsynth.overpoweredtechnology.items;

import java.util.List;
import addsynth.core.game.item.constants.ItemValue;
import addsynth.overpoweredtechnology.compatability.curios.RingEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public final class Ring extends Item {

  public Ring(){
    super(new Item.Properties().stacksTo(1));
  }

  @Override
  @Nullable
  public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt){
    return CuriosApi.createCurioProvider(
      new ICurio(){
      
        @Override
        public ItemStack getStack(){
          return stack;
        }
        
        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack){
        }
        
        @Override
        public void curioTick(SlotContext slotContext){
          final LivingEntity livingEntity = slotContext.entity();
          @SuppressWarnings("resource")
          final Level level = livingEntity.level();
          if(level.isClientSide == false){
            // Example Ring in the Curios mod checks the livingEntity.ticksExisted and modulos it with 19,
            // then Re-adds the effect every second. This is far superior than checking to see if the
            // entity has the effect, and then if the effect is about to run out.
            // But we still need the special case for the Extra Health effect.
            RingEffects.checkEntityHasEffect(stack, livingEntity);
          }
        }
        
        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack){
          RingEffects.removeEffectsFromEntity(stack, slotContext.entity());
        }
        
      }
    );
  }

  @Override
  public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn){
    RingEffects.assignToolTip(stack, tooltip);
  }

  @Override
  public Rarity getRarity(ItemStack stack){
    return ItemValue.values()[RingEffects.get_ring_rarity(stack)].rarity;
  }

  @Override
  public boolean isFoil(final ItemStack stack){
    return RingEffects.get_ring_rarity(stack) >= ItemValue.EPIC.value;
  }

}
