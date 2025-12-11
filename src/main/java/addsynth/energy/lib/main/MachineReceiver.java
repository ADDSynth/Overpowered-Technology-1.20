package addsynth.energy.lib.main;

import addsynth.energy.lib.config.MachineData;

public class MachineReceiver extends Receiver {

  protected final MachineData data;

  public MachineReceiver(MachineData data){
    super(data.get_total_energy_needed(), data.get_max_receive());
    this.data = data;
  }

  @Override
  public boolean tick(){
    if(capacity.get() != data.get_total_energy_needed()){
      capacity.set(data.get_total_energy_needed());
      changed = true;
    }
    if(maxReceive.get() != data.get_max_receive()){
      maxReceive.set(data.get_max_receive());
      changed = true;
    }
    if(changed){
      changed = false;
      return true;
    }
    return false;
  }

}
