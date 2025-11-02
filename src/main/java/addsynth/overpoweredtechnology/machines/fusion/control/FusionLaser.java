package addsynth.overpoweredtechnology.machines.fusion.control;

import java.util.List;
import javax.annotation.Nullable;
import addsynth.overpoweredtechnology.game.reference.OverpoweredBlocks;
import addsynth.overpoweredtechnology.game.reference.TextReference;
import addsynth.overpoweredtechnology.machines.laser.cannon.AbstractLaserCannon;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class FusionLaser extends AbstractLaserCannon {

  public FusionLaser(){
    super(MapColor.COLOR_GRAY);
  }

  @Override
  public final void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn){
    tooltip.add(TextReference.fusion_machine);
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos){
    final Block block = world.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).getBlock();
    return block == OverpoweredBlocks.fusion_control_unit.get();
  }

  @Override
  protected final ItemStack getItemStack(){return new ItemStack(OverpoweredBlocks.fusion_control_laser.get(), 1);}

}
