package addsynth.energy.lib.energy_network;

import java.util.HashSet;
import addsynth.core.util.math.common.MathUtility;
import addsynth.core.util.math.number.DecimalNumber;
import addsynth.energy.lib.main.Energy;
import addsynth.energy.lib.main.IEnergyConsumer;
import addsynth.energy.lib.main.IEnergyGenerator;
import net.minecraft.world.level.block.entity.BlockEntity;

/*
  I could either transfer all energy all at once, but consider the following:
  1 Generator, generates 500 energy.
  1 Battery, can receive and extract 500 energy.
  1 Machine, requests 22 energy.
  Total Receive = 522, total Extract = 1000
  But receive is the lower number so we use that.
  Therefore, 522 gets divided amongst all energy producing machines.
  2 machines produce energy. 261 get extracted from the Generator, and 261 from the battery.
  Energy is NOT transferred from the Generator to the batteries.
  
  Therefore, you MUST use a 3-way system:
  First transfer energy from generators to the receivers.
  Second, if receivers still need energy, transfer it from batteries.
  Lastly, transfer remaining energy from Generators from batteries.
  But you run into another problem:
  1 Generator produces 100, and 1 Battery produces 100.
  1 Machine requests 5 energy per tick.
  First the generator transfers 5 energy to the machine.
  But then, the batteries can ALSO transfer 5 energy to the machine, so
  it gets 10 Energy in total for this tick.
  
  Since the second method is best, we use that. But must figure out a way to
  check if the machine has already received its energy for this tick.
*/

public final class EnergyUtil {

  @SuppressWarnings("unchecked") // I'm sure this is only temporary
  public static final <G extends BlockEntity & IEnergyGenerator, R extends BlockEntity & IEnergyConsumer> void transfer_energy(final HashSet<EnergyNode<G>> from, final HashSet<EnergyNode<R>> to){
    transfer_energy(from.toArray(new EnergyNode[from.size()]), to.toArray(new EnergyNode[to.size()]));
  }

  public static final <G extends BlockEntity & IEnergyGenerator, R extends BlockEntity & IEnergyConsumer> void transfer_energy(final EnergyNode<G>[] from, final EnergyNode<R>[] to){
  
    int i;
    EnergyNode node;
    G generator;
    R receiver;
  
    final int from_size = from.length;
    final int to_size   = to.length;

    final long[] available_energy = new long[from_size];
    final long[] requested_energy = new long[to_size];
    long total_available_energy = 0;
    long total_requested_energy = 0;

    // Part 1a: Collect Generator Info
    for(i = 0; i < from_size; i++){
      generator = from[i].getTile();
      if(generator != null){
        available_energy[i] = (long)(generator.getAvailableEnergy() * DecimalNumber.DECIMAL_ACCURACY);
        total_available_energy += available_energy[i];
      }
    }
    
    // Part 1b: Collect Receiver Info
    for(i = 0; i < to_size; i++){
      receiver = to[i].getTile();
      if(receiver != null){
        requested_energy[i] = (long)(receiver.getRequestedEnergy() * DecimalNumber.DECIMAL_ACCURACY);
        total_requested_energy += requested_energy[i];
      }
    }
    
    // Part 2: Determine energy to transfer
    final long energy_to_transfer = Math.min(total_available_energy, total_requested_energy);
    if(energy_to_transfer == 0){
      return;
    }
    final long[] energy_to_extract = MathUtility.divide_evenly(energy_to_transfer, available_energy);
    final long[] energy_to_receive = MathUtility.divide_evenly(energy_to_transfer, requested_energy);
    
    // Part 3a: Extract energy from Generators
    for(i = 0; i < from_size; i++){
      node = from[i];
      node.getEnergy().extractEnergy((double)energy_to_extract[i] / DecimalNumber.DECIMAL_ACCURACY);
    }
    
    // Part 3b: Insert energy into Receivers
    for(i = 0; i < to_size; i++){
      node = to[i];
      node.getEnergy().receiveEnergy((double)energy_to_receive[i] / DecimalNumber.DECIMAL_ACCURACY);
    }
    
  }

  public static final void balance_batteries(final EnergyNode[] batteries){

    int i;
    final int length = batteries.length;
    Energy energy_storage;

    long total_energy = 0;
    long total_capacity = 0;
    final long[] capacity = new long[length];

    for(i = 0; i < length; i++){
      energy_storage = batteries[i].getEnergy();
      total_energy   += (long)(energy_storage.getEnergy()   * DecimalNumber.DECIMAL_ACCURACY);
      capacity[i]     = (long)(energy_storage.getCapacity() * DecimalNumber.DECIMAL_ACCURACY);
      total_capacity += capacity[i];
    }
    
    if(total_energy == total_capacity){
      return;
    }
    
    final long[] energy_to_insert = MathUtility.divide_evenly(total_energy, capacity);
    
    for(i = 0; i < length; i++){
      energy_storage = batteries[i].getEnergy();
      energy_storage.setEnergy((double)energy_to_insert[i] / DecimalNumber.DECIMAL_ACCURACY);
    }
  }

}
