package addsynth.energy.lib.tiles.machines;

import addsynth.energy.lib.main.IEnergyConsumer;
import addsynth.energy.lib.main.Receiver;
import addsynth.energy.lib.tiles.AbstractEnergyTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/** All machines that only receive energy to do work derive from this class. */
public abstract class TileAbstractMachine extends AbstractEnergyTile implements IEnergyConsumer {

  public TileAbstractMachine(final BlockEntityType type, BlockPos position, BlockState blockstate, final Receiver energy){
    super(type, position, blockstate, energy);
  }

  @Override
  public double getRequestedEnergy(){
    return energy.getRequestedEnergy();
  }

}
