package addsynth.overpoweredtechnology.machines.fusion.control;

import java.util.List;
import javax.annotation.Nullable;
import addsynth.energy.lib.blocks.MachineBlock;
import addsynth.overpoweredtechnology.game.reference.TextReference;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public final class FusionControlUnit extends MachineBlock {

  public FusionControlUnit(){
    super(MapColor.WOOL);
  }

  @Override
  public final void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn){
    tooltip.add(TextReference.fusion_machine);
  }

  @Override
  @Nullable
  public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_){
    return null;
  }

}
