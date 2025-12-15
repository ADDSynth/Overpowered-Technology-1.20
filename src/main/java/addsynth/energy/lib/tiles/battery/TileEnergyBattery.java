package addsynth.energy.lib.tiles.battery;

import addsynth.energy.lib.main.Energy;
import addsynth.energy.lib.main.IBattery;
import addsynth.energy.lib.tiles.network.AbstractEnergyTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/** This is the base class for all TileEntities that should be treated as Batteries. */
public abstract class TileEnergyBattery extends AbstractEnergyTile implements IBattery {

  public TileEnergyBattery(final BlockEntityType type, BlockPos position, BlockState blockstate, final Energy energy){
    super(type, position, blockstate, energy);
  }

  @Override
  public double getAvailableEnergy(){
    return energy.getAvailableEnergy();
  }

  @Override
  public double getRequestedEnergy(){
    return energy.getRequestedEnergy();
  }

}
