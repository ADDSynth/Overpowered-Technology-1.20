package addsynth.energy.lib.energy_network;

import addsynth.energy.gameplay.config.Config;
import addsynth.energy.gameplay.machines.universal_energy_interface.TileUniversalEnergyInterface;
import addsynth.energy.lib.main.IEnergyUser;
import addsynth.energy.lib.tiles.battery.TileEnergyBattery;
import addsynth.energy.lib.tiles.generators.TileAbstractGenerator;
import addsynth.energy.lib.tiles.machines.TileAbstractMachine;
import addsynth.energy.lib.tiles.machines.block_network.AbstractBlockNetworkMachine;
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

  @SuppressWarnings("unchecked") // sadly, this is the best I can do, again.
  public final <M extends BlockEntity & IEnergyUser> void add(final M tile){
    // handle special custom TileEntity first
    if(tile instanceof TileUniversalEnergyInterface energy_interface){
      free_generators.add(energy_interface);
      generators.add(energy_interface);
      receivers.add(energy_interface);
      batteries.add(energy_interface);
    }
    else if(tile instanceof TileAbstractMachine machine){
      receivers.add(machine);
    }
    // add block network tiles
    else if(tile instanceof AbstractBlockNetworkMachine block_network_machine){
      receivers.add(block_network_machine);
    }
    else if(tile instanceof TileAbstractGenerator generator){
      if(generator.isFreeEnergy()){
        free_generators.add(generator);
      }
      else{
        generators.add(generator);
      }
    }
    else if(tile instanceof TileEnergyBattery battery){
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
    transfer(free_generators, receivers, EnergyTransferStage.FREE_GENERATOR_TO_RECEIVERS);
    transfer(     generators, receivers, EnergyTransferStage.GENERATOR_TO_RECEIVER);
    transfer(      batteries, receivers, EnergyTransferStage.BATTERY_TO_RECEIVER);
    
    // Step 3: Transfer Remaining Energy from Generators to Batteries
    transfer(free_generators, batteries, EnergyTransferStage.FREE_GENERATOR_TO_BATTERY);
    transfer(     generators, batteries, EnergyTransferStage.GENERATOR_TO_BATTERY);
    
    // Step 4: Balance Batteries
    if(Config.balance_batteries.get()){
      batteries.balance();
    }
    
    tick_time = System.nanoTime() - start_time;
  }

  private static final void transfer(IGeneratorData generator_data, IReceiverData receiver_data, EnergyTransferStage stage){
    final long energy_to_transfer = Math.min(generator_data.getTotalAvailableEnergy(), receiver_data.getTotalRequestedEnergy());
    if(energy_to_transfer > 0){
      generator_data.extractEnergy(energy_to_transfer);
      receiver_data.receiveEnergy(energy_to_transfer);
    }
  }

}
