package addsynth.energy.gameplay.config;

import addsynth.energy.lib.config.MachineDataConfig;
import addsynth.energy.lib.config.MachineType;
import addsynth.energy.lib.config.SimpleBatteryConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public final class Config {

  public static final MachineDataConfig compressor         = new MachineDataConfig("Compressor",         MachineType.ALWAYS_ON,  200, 10, 0, 0); // 2,000
  public static final MachineDataConfig circuit_fabricator = new MachineDataConfig("Circuit Fabricator", MachineType.ALWAYS_ON, 1000, 25, 0, 0);

  public static class SOLAR_PANEL {
    
    public enum PhaseDisplay {DEGREES, PERCENTAGE}
    
    public static ForgeConfigSpec.BooleanValue decrease_life;
    public static ForgeConfigSpec.DoubleValue dirty_modifier;
    public static ForgeConfigSpec.DoubleValue energy;
    public static ForgeConfigSpec.IntValue max_life;
    public static ForgeConfigSpec.DoubleValue heavy_rain_modifier;
    public static ForgeConfigSpec.EnumValue<PhaseDisplay> phase_display;
    
    private static final int DEFAULT_TIME = 24_192_000;
    
    public static final void build(ForgeConfigSpec.Builder builder){
      builder.push("Solar Panel");
      decrease_life = builder.comment(
        "Enable whether Solar Panels will get dustier over time. How dirty a solar panel gets will affect it's\n"+
        "efficiency. You can clean solar panels by putting water on it or waiting until it rains."
      ).define("Solar Panels get Dirty Over Time", true);
      dirty_modifier = builder.comment(
        "How much will maximum dirtiness will affect efficiency, as a multiplier."
      ).defineInRange("Dust Efficiency Multiplier", 0.7, 0, 1.0);
      max_life = builder.comment(
        "The time it takes for solar panels to get from clean to 100% dirty, measured in ticks."
      ).defineInRange("Max Life", DEFAULT_TIME, 100, Integer.MAX_VALUE);
      energy = builder.defineInRange("Energy per Tick", 0.5, 0, Double.MAX_VALUE);
      heavy_rain_modifier = builder.defineInRange("Heavy Rain Efficiency Multiplier", 0.5, 0, 1.0);
      phase_display = builder.comment(
        "Client Only: Determines whether the Phase in Solar Panel Controller is displayed in degrees 0-359 or as a percentage of day."
      ).defineEnum("Phase Display", PhaseDisplay.DEGREES);
      builder.pop();
    }
    
    public static final boolean displayPhaseInDegrees(){
      return phase_display.get() == PhaseDisplay.DEGREES;
    }
  }

  public static final SimpleBatteryConfig energy_storage = new SimpleBatteryConfig("Energy Storage Block", 200_000, 100);

  private static final int DEFAULT_UNIVERSAL_ENERGY_INTERFACE_BUFFER = 1_000;
  public static ForgeConfigSpec.IntValue     universal_energy_interface_buffer;

  public Config(final ForgeConfigSpec.Builder builder){
  
    compressor.build(builder);
    circuit_fabricator.build(builder);
    SOLAR_PANEL.build(builder);
    energy_storage.build(builder);
    
    builder.push("Universal Energy Interface");
    universal_energy_interface_buffer     = builder.defineInRange("Universal Energy Interface Buffer",
                                              DEFAULT_UNIVERSAL_ENERGY_INTERFACE_BUFFER, 0, Integer.MAX_VALUE);
    builder.pop();
  }

}
