package addsynth.energy.lib.tiles.network;

import addsynth.core.block_network.BlockNetwork;
import addsynth.energy.lib.energy_network.EnergyNetwork;
import addsynth.energy.lib.main.Energy;
import addsynth.energy.lib.main.IEnergyUser;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/** All TileEntities that use Energy must derive from this class.
 *  This makes them part of an Energy Network.
 */
public abstract class AbstractEnergyTile extends AbstractEnergyNetworkTile implements IEnergyUser {

  protected final Energy energy;

  public AbstractEnergyTile(BlockEntityType type, BlockPos position, BlockState blockstate, Energy energy){
    super(type, position, blockstate);
    this.energy = energy;
  }

  @Override
  public void serverTick(ServerLevel level, BlockState blockstate){
    BlockNetwork.tick(network, level, this, EnergyNetwork::new);
    if(energy.tick()){
      update_data();
    }
  }

  @Override
  public void load(final CompoundTag nbt){
    super.load(nbt);
    if(energy != null){ energy.loadFromNBT(nbt);}
  }

  @Override
  protected void saveAdditional(final CompoundTag nbt){
    super.saveAdditional(nbt);
    if(energy != null){ energy.saveToNBT(nbt);}
  }
  
  @Override
  public Energy getEnergy(){
    return energy;
  }

}
