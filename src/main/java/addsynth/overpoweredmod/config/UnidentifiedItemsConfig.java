package addsynth.overpoweredmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class UnidentifiedItemsConfig {

  private static final int DEFAULT_LEATHER_ARMOR_WEIGHT = 30;
  private static final int DEFAULT_GOLD_ARMOR_WEIGHT    = 18;
  private static final int DEFAULT_IRON_ARMOR_WEIGHT    =  9;
  private static final int DEFAULT_DIAMOND_ARMOR_WEIGHT =  3;
  private static final int DEFAULT_RING_WEIGHT          =  4;
  /*
  Without Rings:
      Leather: 10 / 23 (43%)
         Gold:  6 / 23 (26%)
    Chainmail:  3 / 23 (13%)
         Iron:  3 / 23 (13%)
      Diamond:  1 / 23 ( 4%)
  With Rings:
    Armor: 69 / 73 (95%)
        Leather: 30 / 73 (41%)
           Gold: 18 / 73 (25%)
      Chainmail:  9 / 73 (12%)
           Iron:  9 / 73 (12%)
        Diamond:  3 / 73 ( 4%)
    Rings:  4 / 73 (5%)
  You get 1 custom loot drop every 100 mobs on average.
  You get 1 Ring every 1,825 mobs on average.
  */

  private static final int DEFAULT_COMMON_RING_WEIGHT = 10;
  private static final int DEFAULT_GOOD_RING_WEIGHT   =  6;
  private static final int DEFAULT_RARE_RING_WEIGHT   =  3;
  private static final int DEFAULT_UNIQUE_RING_WEIGHT =  1;
  // add Legendary?

  // Undead
  public static final MobDropConfigEntry ZOMBIE           = MobDropConfigEntry.common("Zombie");
  public static final MobDropConfigEntry ZOMBIE_VILLAGER  = MobDropConfigEntry.common("Zombie Villager");
  public static final MobDropConfigEntry HUSK             = MobDropConfigEntry.common("Husk");
  public static final MobDropConfigEntry SKELETON         = MobDropConfigEntry.common("Skeleton");
  public static final MobDropConfigEntry STRAY            = MobDropConfigEntry.common("Stray");
  public static final MobDropConfigEntry PHANTOM          = MobDropConfigEntry.common("Phantom");
  public static final MobDropConfigEntry DROWNED          = MobDropConfigEntry.common("Drowned");
  public static final MobDropConfigEntry SKELETON_HORSE   = MobDropConfigEntry.common("Skeleton Horse");
  
  // Illagers
  public static final MobDropConfigEntry PILLAGER         = MobDropConfigEntry.common("Pillager");
  public static final MobDropConfigEntry VINDICATOR       = MobDropConfigEntry.common("Vindicator");
  public static final MobDropConfigEntry EVOKER           = MobDropConfigEntry.hard("Evoker");
  public static final MobDropConfigEntry VEX              = MobDropConfigEntry.common("Vex");
  public static final MobDropConfigEntry RAVAGER          = MobDropConfigEntry.hard("Ravager");
  public static final MobDropConfigEntry ILLUSIONER       = MobDropConfigEntry.hard("Illusioner");
  
  // Overworld Mobs
  public static final MobDropConfigEntry CREEPER          = MobDropConfigEntry.common("Creeper");
  public static final MobDropConfigEntry SPIDER           = MobDropConfigEntry.common("Spider");
  public static final MobDropConfigEntry CAVE_SPIDER      = MobDropConfigEntry.common("Cave Spider");
  public static final MobDropConfigEntry WITCH            = MobDropConfigEntry.common("Witch");
  public static final MobDropConfigEntry GUARDIAN         = MobDropConfigEntry.common("Guardian");
  public static final MobDropConfigEntry ELDER_GUARDIAN   = MobDropConfigEntry.hard("Elder Guardian");
  
  // Nether Mobs
  public static final MobDropConfigEntry ZOMBIFIED_PIGLIN = MobDropConfigEntry.common("Zombified Piglin");
  public static final MobDropConfigEntry PIGLIN           = MobDropConfigEntry.common("Piglin");
  public static final MobDropConfigEntry PIGLIN_BRUTE     = MobDropConfigEntry.hard("Piglin Brute");
  public static final MobDropConfigEntry BLAZE            = MobDropConfigEntry.common("Blaze");
  public static final MobDropConfigEntry GHAST            = MobDropConfigEntry.common("Ghast");
  public static final MobDropConfigEntry MAGMA_CUBE       = MobDropConfigEntry.common("Magma Cube");
  public static final MobDropConfigEntry WITHER_SKELETON  = MobDropConfigEntry.common("Wither Skeleton");
  public static final MobDropConfigEntry HOGLIN           = MobDropConfigEntry.common("Hoglin");
  public static final MobDropConfigEntry ZOGLIN           = MobDropConfigEntry.common("Zoglin");
  
  // End Mobs
  public static final MobDropConfigEntry ENDERMAN         = MobDropConfigEntry.common("Enderman");
  public static final MobDropConfigEntry SHULKER          = MobDropConfigEntry.common("Shulker");
  
  // Boss Mobs
  public static final MobDropConfigEntry END_DRAGON       = MobDropConfigEntry.boss("End Dragon");
  public static final MobDropConfigEntry WITHER           = MobDropConfigEntry.boss("Wither");
  public static final MobDropConfigEntry WARDEN           = MobDropConfigEntry.boss("Warden");

  public static ForgeConfigSpec.IntValue   leather_armor_drop_weight;
  public static ForgeConfigSpec.IntValue      gold_armor_drop_weight;
  public static ForgeConfigSpec.IntValue chainmail_armor_drop_weight;
  public static ForgeConfigSpec.IntValue      iron_armor_drop_weight;
  public static ForgeConfigSpec.IntValue   diamond_armor_drop_weight;
  public static ForgeConfigSpec.IntValue            ring_drop_weight;

  public static ForgeConfigSpec.IntValue common_ring_weight;
  public static ForgeConfigSpec.IntValue   good_ring_weight;
  public static ForgeConfigSpec.IntValue   rare_ring_weight;
  public static ForgeConfigSpec.IntValue unique_ring_weight;

  public UnidentifiedItemsConfig(final ForgeConfigSpec.Builder builder){
    builder.push("Mob Drop Settings");
      builder.push("Overworld Mobs");
        builder.push("Undead");
          ZOMBIE.build(builder);
          ZOMBIE_VILLAGER.build(builder);
          HUSK.build(builder);
          SKELETON.build(builder);
          STRAY.build(builder);
          DROWNED.build(builder);
          PHANTOM.build(builder);
          SKELETON_HORSE.build(builder);
        builder.pop();
        builder.push("Illagers");
          PILLAGER.build(builder);
          VINDICATOR.build(builder);
          EVOKER.build(builder);
          VEX.build(builder);
          RAVAGER.build(builder);
          ILLUSIONER.build(builder); // Unused mob. Only spawns if a player uses the /summon command.
        builder.pop();
        SPIDER.build(builder);
        CAVE_SPIDER.build(builder);
        CREEPER.build(builder);
        WITCH.build(builder);
        GUARDIAN.build(builder);
        ELDER_GUARDIAN.build(builder);
      builder.pop();
      builder.push("Nether Mobs");
        ZOMBIFIED_PIGLIN.build(builder);
        PIGLIN.build(builder);
        PIGLIN_BRUTE.build(builder);
        MAGMA_CUBE.build(builder);
        GHAST.build(builder);
        BLAZE.build(builder);
        WITHER_SKELETON.build(builder);
        HOGLIN.build(builder);
        ZOGLIN.build(builder);
      builder.pop();
      builder.push("End Mobs");
        ENDERMAN.build(builder);
        SHULKER.build(builder);
      builder.pop();
      builder.push("Boss Mobs");
        END_DRAGON.build(builder);
        WITHER.build(builder);
        WARDEN.build(builder);
      builder.pop();
    builder.pop();
    
    builder.comment("If you do not have the Curios mod installed, then Ring Weight will not be counted with the other weights.");
    builder.push("Item Type Drop Weights");
        leather_armor_drop_weight = builder.defineInRange("Leather Armor",   DEFAULT_LEATHER_ARMOR_WEIGHT, 0, Integer.MAX_VALUE);
           gold_armor_drop_weight = builder.defineInRange("Gold Armor",      DEFAULT_GOLD_ARMOR_WEIGHT,    0, Integer.MAX_VALUE);
      chainmail_armor_drop_weight = builder.defineInRange("Chainmail Armor", DEFAULT_IRON_ARMOR_WEIGHT,    0, Integer.MAX_VALUE);
           iron_armor_drop_weight = builder.defineInRange("Iron Armor",      DEFAULT_IRON_ARMOR_WEIGHT,    0, Integer.MAX_VALUE);
        diamond_armor_drop_weight = builder.defineInRange("Diamond Armor",   DEFAULT_DIAMOND_ARMOR_WEIGHT, 0, Integer.MAX_VALUE);
                 ring_drop_weight = builder.defineInRange("Rings",           DEFAULT_RING_WEIGHT,          0, Integer.MAX_VALUE);
    builder.pop();
    builder.comment("When an Unidentified Ring is inserted into the Identifier,\n"+
                    "these weight values determine what kind of Ring is produced.");
    builder.push("Ring Type Weights");
      common_ring_weight = builder.defineInRange("Common Rings", DEFAULT_COMMON_RING_WEIGHT, 0, Integer.MAX_VALUE);
        good_ring_weight = builder.defineInRange("Good Rings",   DEFAULT_GOOD_RING_WEIGHT,   0, Integer.MAX_VALUE);
        rare_ring_weight = builder.defineInRange("Rare Rings",   DEFAULT_RARE_RING_WEIGHT,   0, Integer.MAX_VALUE);
      unique_ring_weight = builder.defineInRange("Unique Rings", DEFAULT_UNIQUE_RING_WEIGHT, 0, Integer.MAX_VALUE);
    builder.pop();
  }

}
