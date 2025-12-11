package addsynth.energy.lib.tiles;

import java.util.ArrayList;
import addsynth.core.game.tiles.TileBase;
import addsynth.core.util.game.MinecraftUtility;
import addsynth.energy.lib.energy_network.EnergyNetwork;
import addsynth.energy.lib.energy_network.tiles.AbstractEnergyNetworkTile;
import addsynth.energy.lib.main.Energy;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractEnergyTile extends TileBase {

  protected final Energy energy;

  public AbstractEnergyTile(BlockEntityType type, BlockPos position, BlockState blockstate, Energy energy){
    super(type, position, blockstate);
    this.energy = energy;
  }

  public Energy getEnergy(){
    return energy;
  }

  public final EnergyNetwork[] getAdjacentEnergyNetworks(){
    final Level level = this.level;
    final ArrayList<EnergyNetwork> networks = new ArrayList<>(6);
    BlockPos adjacent;
    AbstractEnergyNetworkTile tile;
    for(Direction direction : Direction.values()){
      adjacent = worldPosition.relative(direction);
      tile = MinecraftUtility.getTileEntity(adjacent, level, AbstractEnergyNetworkTile.class);
      if(tile != null){
        networks.add(tile.getBlockNetwork());
      }
    }
    return networks.toArray(new EnergyNetwork[networks.size()]);
  }

}
