package addsynth.energy.lib.energy_network;

import java.util.HashSet;
import addsynth.core.block_network.BlockNetwork;
import addsynth.core.block_network.Node;
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
 *  of receivers, batteries, and generators, and no other data. Currently, Batteries act as part of the
 *  network, so when it performs an Update search, it passes through the batteries, and works on all
 *  batteries at the same time, preventing energy waterfall effects.
 */
public final class EnergyNetwork extends BlockNetwork<AbstractEnergyNetworkTile> {

  public long tick_time;

  private final HashSet<EnergyNode> all_machines = new HashSet<>(); // This is temporary, but temporary solutions are often the most permanent.
  private final HashSet<EnergyNode<TileAbstractGenerator>> free_generators = new HashSet<>();
  private final HashSet<EnergyNode<TileAbstractGenerator>> generators = new HashSet<>();
  private final HashSet<EnergyNode<TileAbstractMachine>> receivers = new HashSet<>();
  private final HashSet<EnergyNode<TileEnergyBattery>> batteries = new HashSet<>();

  public EnergyNetwork(final ServerLevel world, final AbstractEnergyNetworkTile energy_network_tile){
    super(world, energy_network_tile);
  }

  @Override
  protected void clear_custom_data(){
    all_machines.clear();
    free_generators.clear();
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
    remove_invalid_nodes(free_generators);
    
    try{
      // OPTIMIZE This by the requested energy / available energy WITH the Energy Node! Don't collect them EVERY TIME!
      // Step 1: Transfer 'free energy' generators first
      EnergyUtil.transfer_energy(free_generators, receivers);
      
      // Step 1: subtract as much energy as we can from the generators.
      EnergyUtil.transfer_energy(generators, receivers);
      
      // Step 2: if receivers still need energy, subtract it from batteries.
      EnergyUtil.transfer_energy(batteries, receivers);
      
      // Step 3: Transfer 'free energy' generators first
      EnergyUtil.transfer_energy(free_generators, batteries);
      
      // Step 3: put remaining energy from generators into batteries.
      EnergyUtil.transfer_energy(generators, batteries);
      
      // Step 4: balance all batteries
      if(batteries.size() >= 2){
        EnergyUtil.balance_batteries(batteries.toArray(new EnergyNode[batteries.size()]));
      }
    }
    catch(Exception e){
      ADDSynthEnergy.log.fatal("Encountered a fatal error during Energy Network Tick.", e);
    }
    
    tick_time = TimeUtil.get_elapsed_time(start);
  }

  @Override
  protected final void customSearch(final Node node, final ServerLevel world){
    final BlockEntity tile = node.getTile();
    if(tile != null){
      if(tile instanceof IEnergyUser){
        all_machines.add(new EnergyNode(tile));
        if(tile instanceof TileAbstractMachine machine){
          receivers.add(new EnergyNode<>(machine));
        }
        if(tile instanceof TileAbstractGenerator generator){
          if(generator.isFreeEnergy()){
            free_generators.add(new EnergyNode<>(generator));
          }
          else{
            generators.add(new EnergyNode<>(generator));
          }
        }
        if(tile instanceof TileEnergyBattery battery){
          batteries.add(new EnergyNode<>(battery));
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
