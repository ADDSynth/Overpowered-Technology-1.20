package addsynth.energy.lib.main;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;

public enum EnergyType {

  GENERATOR(0, "gui.addsynth_energy.machine_type.generator"),
   RECEIVER(2, "gui.addsynth_energy.machine_type.receiver"),
    BATTERY(1, "gui.addsynth_energy.machine_type.battery");

  public final int order;
  public final Component component;

  private EnergyType(int compare_order, String translation_key){
   this.order = compare_order;
   component = Component.translatable(translation_key);
  }

  public static final EnergyType determine(final BlockEntity tile){
    if(tile instanceof IEnergyGenerator){
      return GENERATOR;
    }
    if(tile instanceof IEnergyConsumer){
      return RECEIVER;
    }
    if(tile instanceof IEnergyUser){
      return BATTERY;
    }
    return null;
  }

}
