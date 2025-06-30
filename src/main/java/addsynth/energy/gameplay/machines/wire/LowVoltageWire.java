package addsynth.energy.gameplay.machines.wire;

import javax.annotation.Nullable;
import addsynth.energy.gameplay.machines.solar_panel.SolarPanel;
import addsynth.energy.gameplay.machines.solar_panel.SolarPanelController;
import addsynth.energy.lib.blocks.WallAttachableWire;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LowVoltageWire extends WallAttachableWire {

  public LowVoltageWire(){
    super(Block.Properties.of());
  }

  @Override
  @Nullable
  public BlockEntity newBlockEntity(BlockPos position, BlockState blockstate){
    return new LowVoltageWireTile(position, blockstate);
  }

  @Override
  protected boolean isAcceptableBlock(Block block, BlockState blockstate){
    if(block instanceof SolarPanel          ) return true;
    if(block instanceof SolarPanelController) return true;
    return false;
  }

}
