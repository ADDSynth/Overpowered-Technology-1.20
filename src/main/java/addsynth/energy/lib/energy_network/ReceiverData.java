package addsynth.energy.lib.energy_network;

import addsynth.core.util.math.common.MathUtility;
import addsynth.core.util.math.number.DecimalNumber;
import addsynth.energy.lib.tiles.machines.TileAbstractMachine;

public class ReceiverData extends EnergyTransferData<TileAbstractMachine> implements IReceiverData {

  private long total_energy;
  private long[] energy = new long[0];
  private long[] energy_to_receive;
  
  @Override
  public final void update(){
    list.removeIf((EnergyNode<TileAbstractMachine> node) -> node.isInvalid());
    total_energy = 0;
    size = list.size();
    if(energy.length != size){
      energy = new long[size];
    }
    for(i = 0; i < size; i++){
      energy[i] = (long)list.get(i).getTile().getRequestedEnergy() * DecimalNumber.DECIMAL_ACCURACY;
      total_energy += energy[i];
    }
  }
  
  @Override
  public boolean hasRequestedEnergy(){
    return total_energy > 0;
  }
  
  @Override
  public long getTotalRequestedEnergy(){
    return total_energy;
  }
  
  @Override
  public void receiveEnergy(final long transfer_energy){
    total_energy -= transfer_energy;
    energy_to_receive = MathUtility.divide_evenly(transfer_energy, energy);
    for(i = 0; i < size; i++){
      energy[i] -= energy_to_receive[i];
      list.get(i).getEnergy().receiveEnergy((double)energy_to_receive[i] / DecimalNumber.DECIMAL_ACCURACY);
    }
  }

}
