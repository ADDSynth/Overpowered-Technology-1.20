package addsynth.energy.lib.energy_network;

import addsynth.core.util.math.common.MathUtility;
import addsynth.core.util.math.number.DecimalNumber;
import addsynth.energy.lib.tiles.energy.TileAbstractGenerator;

public class GeneratorData extends EnergyTransferData<TileAbstractGenerator> implements IGeneratorData {

  private long total_energy;
  private long[] energy = new long[0];
  private long[] energy_to_extract;

  @Override
  public final void update(){
    list.removeIf((EnergyNode<TileAbstractGenerator> node) -> node.isInvalid());
    total_energy = 0;
    size = list.size();
    if(energy.length != size){
      energy = new long[size];
    }
    for(i = 0; i < size; i++){
      energy[i] = (long)list.get(i).getTile().getAvailableEnergy() * DecimalNumber.DECIMAL_ACCURACY;
      total_energy += energy[i];
    }
  }

  @Override
  public boolean hasAvailableEnergy(){
    return total_energy > 0;
  }
  
  @Override
  public long getTotalAvailableEnergy(){
    return total_energy;
  }
  
  @Override
  public void extractEnergy(final long transfer_energy){
    total_energy -= transfer_energy;
    energy_to_extract = MathUtility.divide_evenly(transfer_energy, energy);
    for(i = 0; i < size; i++){
      energy[i] -= energy_to_extract[i];
      list.get(i).getEnergy().extractEnergy((double)energy_to_extract[i] / DecimalNumber.DECIMAL_ACCURACY);
    }
  }

}
