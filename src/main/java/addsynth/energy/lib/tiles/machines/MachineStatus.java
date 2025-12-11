package addsynth.energy.lib.tiles.machines;

import addsynth.core.util.color.ColorCode;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum MachineStatus {

  GOOD                (false, "Normal"),
  OUTPUT_FULL         (true,  "gui.addsynth_energy.machine_state.output_full"),
  NO_ENERGY           (true,  "gui.addsynth_energy.machine_state.no_energy"),
  NOT_RECEIVING_ENERGY(true,  "gui.addsynth_energy.machine_state.not_receiving_energy");

  private final MutableComponent status;

  private MachineStatus(boolean error, String translation_key){
    status = Component.translatable(translation_key);
  }

  public boolean isError(){
    return this != GOOD;
  }

  public MutableComponent get(){
    return this != GOOD ? status.withStyle(ColorCode.ERROR) : status;
  }

}
