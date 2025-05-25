package addsynth.overpoweredmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class Values {

  private static final int    DEFAULT_PORTAL_SPAWN_TIME = 40;
  private static final double DEFAULT_UNKNOWN_TREE_SPAWN_CHANCE = 0.0005; // 1 / 2000

  public static ForgeConfigSpec.IntValue portal_spawn_time;
  public static ForgeConfigSpec.DoubleValue unknown_dimension_tree_spawn_chance;

  public Values(final ForgeConfigSpec.Builder builder){
    portal_spawn_time = builder.defineInRange("Portal Spawn Time (in seconds)", DEFAULT_PORTAL_SPAWN_TIME, 5, 3600);
    
    unknown_dimension_tree_spawn_chance = builder.comment(
      "This float value determines the chance a weird tree will spawn for each chunk\nin the Unknown Dimension.")
      .defineInRange("Weird Tree Spawn Chance", DEFAULT_UNKNOWN_TREE_SPAWN_CHANCE, Float.MIN_NORMAL, 1.0f);
  }

}
