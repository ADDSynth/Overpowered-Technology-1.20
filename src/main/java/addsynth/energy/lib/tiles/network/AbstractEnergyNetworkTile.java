package addsynth.energy.lib.tiles.network;

import javax.annotation.Nullable;
import addsynth.core.block_network.BlockNetwork;
import addsynth.core.block_network.IBlockNetworkUser;
import addsynth.core.game.tiles.TileBase;
import addsynth.energy.lib.energy_network.EnergyNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/** TileEntities that are a part of the Energy Network derive from this.
 * @author ADDSynth
 */
public abstract class AbstractEnergyNetworkTile extends TileBase implements IBlockNetworkUser<EnergyNetwork> {

  @Nullable
  protected EnergyNetwork network;

  public AbstractEnergyNetworkTile(final BlockEntityType type, BlockPos position, BlockState blockstate){
    super(type, position, blockstate);
  }

  @Override
  public void serverTick(ServerLevel level, BlockState blockstate){
    BlockNetwork.tick(network, level, this, EnergyNetwork::new);
  }

  @Override
  @Nullable
  public final EnergyNetwork getBlockNetwork(){
    return network;
  }

  @Override
  public final void setBlockNetwork(final EnergyNetwork network){
    this.network = network;
  }

}
