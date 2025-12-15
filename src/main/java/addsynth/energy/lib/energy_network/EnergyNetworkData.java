package addsynth.energy.lib.energy_network;

import addsynth.energy.lib.main.IEnergyUser;
import addsynth.energy.lib.tiles.energy.TileAbstractGenerator;
import addsynth.energy.lib.tiles.energy.TileEnergyBattery;
import addsynth.energy.lib.tiles.machines.TileAbstractMachine;
import net.minecraft.world.level.block.entity.BlockEntity;

public class EnergyNetworkData {

  private long start_time;
  private long tick_time;
  // private long energy_to_transfer;
  private final GeneratorData free_generators = new GeneratorData();
  private final GeneratorData      generators = new GeneratorData();
  private final  ReceiverData       receivers = new ReceiverData();
  private final   BatteryData       batteries = new BatteryData();
  
  public final void clear(){
    free_generators.clear();
    generators.clear();
    receivers.clear();
    batteries.clear();
  }

  public final <M extends BlockEntity & IEnergyUser> void add(final M tile){
    if(tile instanceof TileAbstractMachine machine){
      receivers.add(machine);
    }
    if(tile instanceof TileAbstractGenerator generator){
      if(generator.isFreeEnergy()){
        free_generators.add(generator);
      }
      else{
        generators.add(generator);
      }
    }
    if(tile instanceof TileEnergyBattery battery){
      batteries.add(battery);
    }
  }

  public final void tick(){
    start_time = System.nanoTime();
    
    // Step 1: Update Data
    free_generators.update();
         generators.update();
          receivers.update();
          batteries.update();
    
    // Step 2: Transfer Energy from Generators to Receivers
    transfer(free_generators, receivers);
    transfer(     generators, receivers);
    transfer(      batteries, receivers);
    
    // Step 3: Transfer Remaining Energy from Generators to Batteries
    transfer(free_generators, batteries);
    transfer(     generators, batteries);
    
    // Step 4: Balance Batteries
    batteries.balance();
    
    tick_time = System.nanoTime() - start_time;
  }

  private static final void transfer(IGeneratorData generator_data, IReceiverData receiver_data){
    final long energy_to_transfer = Math.min(generator_data.getTotalAvailableEnergy(), receiver_data.getTotalRequestedEnergy());
    if(energy_to_transfer > 0){
      generator_data.extractEnergy(energy_to_transfer);
      receiver_data.receiveEnergy(energy_to_transfer);
    }
  }

}
