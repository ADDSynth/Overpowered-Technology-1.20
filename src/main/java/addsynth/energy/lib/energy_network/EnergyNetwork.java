package addsynth.energy.lib.energy_network;

import java.util.HashSet;
import addsynth.core.block_network.BlockNetwork;
import addsynth.core.block_network.Node;
import addsynth.core.util.time.TimeUtil;
import addsynth.energy.ADDSynthEnergy;
import addsynth.energy.lib.energy_network.tiles.AbstractEnergyNetworkTile;
import addsynth.energy.lib.main.Energy;
import addsynth.energy.lib.main.IBattery;
import addsynth.energy.lib.main.IEnergyConsumer;
import addsynth.energy.lib.main.IEnergyGenerator;
import addsynth.energy.lib.main.IEnergyUser;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

// original inspiration from canitzp:
// https://github.com/canitzp/Metalworks/blob/master/src/main/java/de/canitzp/metalworks/block/cable/Network.java

/** The EnergyNetwork is responsible for transferring energy to and from machines. It only keeps a list
 *  of receivers, batteries, and generators, and no other data. Currently, Batteries act as part of the
 *  network, so when it performs an Update search, it passes through the batteries, and works on all
 *  batteries at the same time, preventing energy waterfall effects.
 */
public final class EnergyNetwork extends BlockNetwork<AbstractEnergyNetworkTile> {

  public long tick_time;

  private final HashSet<EnergyNode> all_machines = new HashSet<>(); // This is temporary, but temporary solutions are often the most permanent.
  private final HashSet<EnergyNode> free_generators = new HashSet<>();
  private final HashSet<EnergyNode> generators = new HashSet<>();
  private final HashSet<EnergyNode> receivers = new HashSet<>();
  private final HashSet<EnergyNode> batteries = new HashSet<>();

  public EnergyNetwork(final ServerLevel world, final AbstractEnergyNetworkTile energy_network_tile){
    super(world, energy_network_tile);
  }

  @Override
  protected void clear_custom_data(){
    all_machines.clear();
    generators.clear();
    receivers.clear();
    batteries.clear();
  }

  @Override
  protected final void tick(final ServerLevel world){
    final long start = TimeUtil.get_start_time();
    
    remove_invalid_nodes(all_machines);
    remove_invalid_nodes(batteries);
    remove_invalid_nodes(receivers);
    remove_invalid_nodes(generators);
    
    // Step 1: Gather all consumer data from all networks
    // Step 2: Gather all 'free' energy data, which could be different per network because it's porportional to the consumer energy requested.
    // Step 3: Transfer all 'free' energy.
    // Step 4: If consumers still need energy, gather all 'non-free' energy data (which could be different per network because it's porportional)
    // Step 5: Transfer all 'non-free' energy.
    // Step 6: if consumers still need energy, transfer from batteries.
    // Step 7: If PREVIOUS step 6 executed, skip 7 and 8.
    
    // every tick, we gather ALL data.
    // Consumers will check if either side has any free energy sources.
    // Generators will compare the requested energy from all networks.

    // DO NOT query non-free generators, unless we need to.
    try{
      // TEST: Step 1 and 2 should probably be reversed.
    
      // Step 1: subtract as much energy as we can from the generators.
      EnergyUtil.transfer_energy(generators, receivers);
      
      // Step 2: if receivers still need energy, subtract it from batteries.
      EnergyUtil.transfer_energy(batteries, receivers);
      
      // Step 3: put remaining energy from generators into batteries.
      EnergyUtil.transfer_energy(generators, batteries);
      
      // Step 4: balance all batteries
      if(batteries.size() >= 2){
        EnergyUtil.balance_batteries(batteries);
      }
    }
    catch(Exception e){
      ADDSynthEnergy.log.fatal("Encountered a fatal error during Energy Network Tick.", e);
    }
    
    tick_time = TimeUtil.get_elapsed_time(start);
  }

  @Override
  @SuppressWarnings("unchecked")
  protected final void customSearch(final Node node, final ServerLevel world){
    final BlockEntity tile = node.getTile();
    if(tile != null){
      if(EnergyNode.add(all_machines, tile)){
        if(tile instanceof IEnergyConsumer){
          receivers.add(new EnergyNode(tile));
        }
        if(tile instanceof IEnergyGenerator generator){
          if(generator.isFreeEnergy()){
            free_generators.add(new EnergyNode(tile));
          }
          else{
            generators.add(new EnergyNode(tile));
          }
        }
        if(tile instanceof IBattery){
          batteries.add(new EnergyNode(tile));
        }
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
