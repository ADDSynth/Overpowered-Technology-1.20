package addsynth.energy.lib.energy_network;

import addsynth.core.util.java.ArrayUtil;
import addsynth.core.util.math.common.MathUtility;
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
  private long generator_energy;
  private long receiver_energy;
  private long energy_to_transfer;
  private long[] energy_values;
  private long[] energy;
  private long[] energy_transfer;
  private int size;
  private int total_size;
  private int i;
  private final GeneratorData free_generators = new GeneratorData();
  private final GeneratorData      generators = new GeneratorData();
  private final  ReceiverData       receivers = new ReceiverData();
  private final   BatteryData       batteries = new BatteryData();
  private final CustomTransferData     custom = new CustomTransferData();
  
  public final void clear(){
    free_generators.clear();
    generators.clear();
    receivers.clear();
    batteries.clear();
    custom.clear();
  }

  @SuppressWarnings("unchecked") // sadly, this is the best I can do, again.
  public final <M extends BlockEntity & IEnergyUser> void add(final M tile){
    if(tile instanceof TileAbstractMachine machine){
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
    else if(tile instanceof TileUniversalEnergyInterface energy_interface){
      custom.add(energy_interface);
    }
  }

  public final void tick(){
    start_time = System.nanoTime();
    
    // Step 1: Update Data
    free_generators.update();
         generators.update();
          receivers.update();
          batteries.update();
             custom.update();
    
    // Step 2: Transfer Energy from Generators to Receivers
    transfer(free_generators, receivers, EnergyTransferStage.FREE_GENERATOR, EnergyTransferStage.RECEIVER);
    transfer(     generators, receivers, EnergyTransferStage.GENERATOR,      EnergyTransferStage.RECEIVER);
    transfer(      batteries, receivers, EnergyTransferStage.BATTERY,        EnergyTransferStage.RECEIVER);
    
    // Step 3: Transfer Remaining Energy from Generators to Batteries
    transfer(free_generators, batteries, EnergyTransferStage.FREE_GENERATOR, EnergyTransferStage.BATTERY);
    transfer(     generators, batteries, EnergyTransferStage.GENERATOR,      EnergyTransferStage.BATTERY);
    
    // Step 4: Balance Batteries
    if(Config.balance_batteries.get()){
      batteries.balance();
      // FEATURE: currently isn't being balanced with Universal Energy Interfaces set to Battery mode.
    }
    
    tick_time = System.nanoTime() - start_time;
  }

  private final void transfer(IGeneratorData generator_data, IReceiverData receiver_data, EnergyTransferStage extract_stage, EnergyTransferStage receive_stage){
    generator_energy = generator_data.getTotalAvailableEnergy() + custom.getAvailableEnergy(extract_stage);
     receiver_energy =  receiver_data.getTotalRequestedEnergy() + custom.getRequestedEnergy(receive_stage);
    energy_to_transfer = Math.min(generator_energy, receiver_energy);
    if(energy_to_transfer > 0){
      extractEnergy(energy_to_transfer, generator_data);
      receiveEnergy(energy_to_transfer, receiver_data);
    }
  }

  private final void extractEnergy(final long total_energy, final IGeneratorData generator_data){
    energy_values = generator_data.getGeneratorValues();
    size = energy_values.length;
    energy = ArrayUtil.combine_arrays(energy_values, custom.getGeneratorValues());
    total_size = energy.length;
    energy_transfer = MathUtility.divide_evenly(total_energy, energy);
    for(i = 0; i < total_size; i++){
      if(i < size){
        generator_data.extractEnergy(i, energy_transfer[i]);
      }
      else{
        custom.extractEnergy(i - size, energy_transfer[i]);
      }
    }
  }
  
  private final void receiveEnergy(final long total_energy, final IReceiverData receiver_data){
    energy_values = receiver_data.getReceiverValues();
    size = energy_values.length;
    energy = ArrayUtil.combine_arrays(energy_values, custom.getReceiverValues());
    total_size = energy.length;
    energy_transfer = MathUtility.divide_evenly(total_energy, energy);
    for(i = 0; i < total_size; i++){
      if(i < size){
        receiver_data.receiveEnergy(i, energy_transfer[i]);
      }
      else{
        custom.receiveEnergy(i - size, energy_transfer[i]);
      }
    }
  }

}
