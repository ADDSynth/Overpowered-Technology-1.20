package addsynth.energy.lib.tiles.machines.block_network;

import javax.annotation.Nullable;
import addsynth.core.block_network.BlockNetwork;
import addsynth.core.block_network.IBlockNetworkUser;
import addsynth.core.game.tiles.TileBase;
import addsynth.energy.lib.main.IEnergyConsumer;
import addsynth.energy.lib.main.Receiver;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

// I REALLY wish I could have a generic BlockNetwork TileEntity, but I just can't figure out the generics!
public abstract class AbstractBlockNetworkMachine<T extends BlockNetwork> extends TileBase implements IBlockNetworkUser<T>, IEnergyConsumer {

  protected final Receiver energy;
  protected T network;

  public AbstractBlockNetworkMachine(BlockEntityType type, BlockPos position, BlockState blockstate, Receiver energy){
    super(type, position, blockstate);
    this.energy = energy;
  }

  @Override
  @Nullable
  public T getBlockNetwork(){
    return network;
  }

  @Override
  public void setBlockNetwork(T network){
    this.network = network;
  }

  @Override
  public double getRequestedEnergy(){
    return getEnergy().getRequestedEnergy();
  }

}
