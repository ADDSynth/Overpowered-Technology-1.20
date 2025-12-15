package addsynth.energy.lib.energy_network;

import addsynth.core.util.math.common.MathUtility;
import addsynth.core.util.math.number.DecimalNumber;
import addsynth.energy.lib.tiles.battery.TileEnergyBattery;

public class BatteryData extends EnergyTransferData<TileEnergyBattery> implements IGeneratorData, IReceiverData {

  private long total_generator_energy;
  private long total_receiver_energy;
  private long total_energy;
  private long[] generator_energy = new long[0];
  private long[] receiver_energy = new long[0];
  private long[] capacity = new long[0];
  private long[] transfer;
  private TileEnergyBattery tile;
  
  @Override
  public final void update(){
    list.removeIf((EnergyNode<TileEnergyBattery> node) -> node.isInvalid());
    total_generator_energy = 0;
    total_receiver_energy = 0;
    total_energy = 0;
    size = list.size();
    if(generator_energy.length != size){
      generator_energy = new long[size];
      receiver_energy = new long[size];
      capacity = new long[size];
    }
    for(i = 0; i < size; i++){
      tile = list.get(i).getTile();
      generator_energy[i] = (long)tile.getAvailableEnergy() * DecimalNumber.DECIMAL_ACCURACY;
      total_generator_energy += generator_energy[i];
      total_energy += generator_energy[i];
      receiver_energy[i] = (long)tile.getRequestedEnergy() * DecimalNumber.DECIMAL_ACCURACY;
      total_receiver_energy += receiver_energy[i];
    }
  }
  
  @Override
  public boolean hasAvailableEnergy(){
    return total_generator_energy > 0;
  }
  
  @Override
  public boolean hasRequestedEnergy(){
    return total_receiver_energy > 0;
  }
  
  @Override
  public long getTotalAvailableEnergy(){
    return total_generator_energy;
  }
  
  @Override
  public long getTotalRequestedEnergy(){
    return total_receiver_energy;
  }
  
  @Override
  public void extractEnergy(final long transfer_energy){
    total_generator_energy -= transfer_energy;
    total_energy -= transfer_energy;
    transfer = MathUtility.divide_evenly(transfer_energy, generator_energy);
    for(i = 0; i < size; i++){
      generator_energy[i] -= transfer[i];
      list.get(i).getEnergy().extractEnergy((double)transfer[i] / DecimalNumber.DECIMAL_ACCURACY);
    }
  }

  @Override
  public void receiveEnergy(final long transfer_energy){
    total_receiver_energy -= transfer_energy;
    total_energy += transfer_energy;
    transfer = MathUtility.divide_evenly(transfer_energy, receiver_energy);
    for(i = 0; i < size; i++){
      receiver_energy[i] -= transfer[i];
      list.get(i).getEnergy().receiveEnergy((double)transfer[i] / DecimalNumber.DECIMAL_ACCURACY);
    }
  }

  public final void balance(){
    if(size > 1){
      for(i = 0; i < size; i++){
        capacity[i] = (long)list.get(i).getEnergy().getCapacity() * DecimalNumber.DECIMAL_ACCURACY;
      }
      transfer = MathUtility.divide_evenly(total_energy, capacity);
      for(i = 0; i < size; i++){
        list.get(i).getEnergy().setEnergy((double)transfer[i] / DecimalNumber.DECIMAL_ACCURACY);
      }
    }
  }

}
