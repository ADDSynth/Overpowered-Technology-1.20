package addsynth.energy.lib.tiles.generators;

import addsynth.energy.lib.main.Generator;
import addsynth.energy.lib.main.IEnergyGenerator;
import addsynth.energy.lib.tiles.network.AbstractEnergyTile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/** This TileEntity is for machines that generate Energy all on their own. */
public abstract class TileAbstractGenerator extends AbstractEnergyTile implements IEnergyGenerator {

  protected boolean changed;

  public TileAbstractGenerator(final BlockEntityType type, BlockPos position, BlockState blockstate){
    super(type, position, blockstate, new Generator());
  }

  @Override
  public void serverTick(ServerLevel level, BlockState blockstate){
    if(energy.isEmpty()){
      setGeneratorData();
      changed = true;
    }
    if(energy.tick()){
      changed = true;
    }
    if(changed){
      update_data();
      changed = false;
    }
  }

  protected abstract void setGeneratorData();

  @Override
  public double getAvailableEnergy(){
    return energy.getAvailableEnergy();
  }

}
