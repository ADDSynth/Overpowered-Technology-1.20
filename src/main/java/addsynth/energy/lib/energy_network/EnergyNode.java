package addsynth.energy.lib.energy_network;

import java.util.HashSet;
import javax.annotation.Nonnull;
import addsynth.core.block_network.AbstractNode;
import addsynth.energy.lib.main.Energy;
import addsynth.energy.lib.main.IEnergyUser;
import net.minecraft.world.level.block.entity.BlockEntity;

public class EnergyNode<E extends BlockEntity & IEnergyUser> extends AbstractNode<E> {

  public EnergyNode(@Nonnull final E tile){
    super(tile);
  }

  @SuppressWarnings("unchecked")
  public static boolean add(final HashSet<EnergyNode> set, @Nonnull BlockEntity tile){
    if(tile instanceof IEnergyUser){
      set.add(new EnergyNode(tile));
      return true;
    }
    return false;
  }

  public Energy getEnergy(){
    return tile.getEnergy();
  }

}
