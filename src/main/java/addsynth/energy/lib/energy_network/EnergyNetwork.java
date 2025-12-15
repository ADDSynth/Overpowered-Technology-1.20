package addsynth.energy.lib.energy_network;

import java.util.HashSet;
import addsynth.core.block_network.BlockNetwork;
import addsynth.core.block_network.node.Node;
import addsynth.core.util.time.TimeUtil;
import addsynth.energy.ADDSynthEnergy;
import addsynth.energy.lib.energy_network.tiles.AbstractEnergyNetworkTile;
import addsynth.energy.lib.main.IEnergyUser;
import addsynth.energy.lib.tiles.energy.TileAbstractGenerator;
import addsynth.energy.lib.tiles.energy.TileEnergyBattery;
import addsynth.energy.lib.tiles.machines.TileAbstractMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

// original inspiration from canitzp:
// https://github.com/canitzp/Metalworks/blob/master/src/main/java/de/canitzp/metalworks/block/cable/Network.java

/** The EnergyNetwork is responsible for transferring energy to and from machines. It only keeps a list
 *  of receivers, batteries, and generators, and no other data.
 *  All machines and wire are considered to be part of an energy network, except in the case where the
 *  space we searched before is a machine, and the space we're searching now is also a machine. This
 *  ensures at least 1 wire must be present to connect two machines. We also can't pass though blocks that
 *  are part of their own block network, but this is a limitation I'm prepared to accept at this time.
 */
public final class EnergyNetwork extends BlockNetwork<AbstractEnergyNetworkTile> {

  public long tick_time;

  private final HashSet<EnergyNode> all_machines = new HashSet<>();
  private final EnergyNetworkData   transfer_data = new EnergyNetworkData();

  public EnergyNetwork(final ServerLevel world, final AbstractEnergyNetworkTile energy_network_tile){
    super(world, energy_network_tile);
  }

  @Override
  protected void clear_custom_data(){
    all_machines.clear();
    transfer_data.clear();
  }

  @Override
  protected final void tick(final ServerLevel world){
    transfer_data.tick();
  }

  @Override
  protected final void customSearch(final Node node, final ServerLevel world){
    final BlockEntity tile = node.getTile();
    if(tile != null){
      if(tile instanceof IEnergyUser){
        all_machines.add(new EnergyNode(tile));
        transfer_data.add(tile);
      }
    }
  }

  @Override
  public void neighbor_was_changed(final ServerLevel world, final BlockPos current_position, final BlockPos position_of_neighbor){
    final BlockEntity tile = world.getBlockEntity(position_of_neighbor);
    if(tile != null){
      if(tile instanceof IEnergyUser){
        updateBlockNetwork(world, current_position);
      }
    }
  }

  public final EnergyNode[] getDiagnosticsData(){
    return all_machines.toArray(new EnergyNode[all_machines.size()]);
  }

}
