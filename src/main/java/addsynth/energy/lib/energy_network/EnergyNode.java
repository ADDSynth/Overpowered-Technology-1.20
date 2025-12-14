package addsynth.energy.lib.energy_network;

import javax.annotation.Nonnull;
import addsynth.core.block_network.node.BlockEntityNode;
import addsynth.energy.lib.main.Energy;
import addsynth.energy.lib.main.IEnergyUser;
import net.minecraft.world.level.block.entity.BlockEntity;

public class EnergyNode<E extends BlockEntity & IEnergyUser> extends BlockEntityNode<E> {

  public EnergyNode(@Nonnull final E tile){
    super(tile);
  }

  public Energy getEnergy(){
    return tile.getEnergy();
  }

}
