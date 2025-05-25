package addsynth.overpoweredmod.blocks;

import java.text.NumberFormat;
import java.util.List;
import javax.annotation.Nullable;
import addsynth.overpoweredmod.config.MachineValues;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public final class LightBlock extends Block {

  public LightBlock(){
    super(Block.Properties.of().sound(SoundType.STONE).mapColor(MapColor.QUARTZ).lightLevel((blockstate)->{return 15;}).strength(5.0f, 6.0f).requiresCorrectToolForDrops());
  }

  @Override
  public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn){
    final String energy = NumberFormat.getIntegerInstance().format(MachineValues.light_block_energy.get());
    tooltip.add(Component.translatable("gui.addsynth_energy.tooltip.energy", energy).withStyle(ChatFormatting.AQUA));
  }

}
