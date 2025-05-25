package addsynth.overpoweredmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class MobDropConfigEntry {

  public final String mob_name;
  private final double default_drop_chance;
  public ForgeConfigSpec.BooleanValue drop;
  public ForgeConfigSpec.DoubleValue chance;

  public MobDropConfigEntry(final String mob_name, final double default_drop_chance){
    this.mob_name = mob_name;
    this.default_drop_chance = default_drop_chance;
  }

  public static MobDropConfigEntry common(final String mob_name){
    return new MobDropConfigEntry(mob_name, 0.01);
  }
  public static MobDropConfigEntry hard(final String mob_name){
    return new MobDropConfigEntry(mob_name, 1);
  }
  public static MobDropConfigEntry boss(final String mob_name){
    return new MobDropConfigEntry(mob_name, 7);
  }

  public final void build(final ForgeConfigSpec.Builder builder){
    builder.push(mob_name);
    drop = builder.define("Enable Custom Drops", true);
    chance = builder.defineInRange("Chance", default_drop_chance, 0, 100);
    builder.pop();
  }

}
