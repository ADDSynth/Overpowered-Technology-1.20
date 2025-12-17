package addsynth.energy.lib.energy_network;

import javax.annotation.Nonnull;
import addsynth.core.block_network.node.BlockEntityNode;
import addsynth.energy.lib.main.Energy;
import addsynth.energy.lib.main.IEnergyUser;
import addsynth.energy.lib.tiles.AbstractEnergyNetworkTile;
import net.minecraft.world.level.block.entity.BlockEntity;

/** <p>Energy Nodes are entries that are stored in the Energy Transfer system that determine
 *  individual machines that can transfer energy. They must be constantly checked by calling
 *  {@link #isInvalid()} to remove TileEntities that have suddenly been removed.
 *  <p>Since blocks that are part of their own BlockNetwork cannot extend from
 *  {@link AbstractEnergyNetworkTile} they have to implement their own class, but as long
 *  as they implement the <c>IEnergyUser</c> interface, we can add them as Energy Nodes.
 *  <p>Additionally, blocks that are part of a BlockNetwork could be encountered more than once
 *  during the EnergyNetwork discovery process, but EnergyNodes are only added if the {@link Energy}
 *  returned is different. So all blocks in the BlockNetwork MUST return their BlockNetwork's Energy.
 */
public class EnergyNode<E extends BlockEntity & IEnergyUser> extends BlockEntityNode<E> {

  public EnergyNode(@Nonnull final E tile){
    super(tile);
  }

  public Energy getEnergy(){
    return tile.getEnergy();
  }

  @Override
  public boolean isInvalid(){
    return super.isInvalid() || getEnergy() == null;
  }

  @Override
  public int hashCode(){
    return getEnergy().hashCode();
  }

  @Override
  public boolean equals(Object other){
    if(other instanceof EnergyNode node){
      return node.getEnergy() == getEnergy();
    }
    return false;
  }

}
