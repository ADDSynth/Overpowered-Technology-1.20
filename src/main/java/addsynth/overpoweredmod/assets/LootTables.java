package addsynth.overpoweredmod.assets;

import addsynth.core.compat.Compatibility;
import addsynth.core.game.item.constants.ArmorMaterial;
import addsynth.core.game.item.constants.EquipmentType;
import addsynth.overpoweredmod.OverpoweredTechnology;
import addsynth.overpoweredmod.config.MobDropConfigEntry;
import addsynth.overpoweredmod.config.UnidentifiedItemsConfig;
import addsynth.overpoweredmod.game.reference.OverpoweredItems;
import addsynth.overpoweredmod.items.UnidentifiedItem;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OverpoweredTechnology.MOD_ID)
public final class LootTables {

// https://mcforge.readthedocs.io/en/latest/items/loot_tables/
// https://minecraft.gamepedia.com/Loot_table
// https://minecraft.gamepedia.com/Status_effect#Luck
// https://github.com/Vazkii/Botania/blob/master/src/main/java/vazkii/botania/common/core/loot/LootHandler.java

  private static final LootPool build_loot_pool(final float drop_chance){
    final int   leather_weight = UnidentifiedItemsConfig.leather_armor_drop_weight.get();
    final int      gold_weight = UnidentifiedItemsConfig.gold_armor_drop_weight.get();
    final int chainmail_weight = UnidentifiedItemsConfig.chainmail_armor_drop_weight.get();
    final int      iron_weight = UnidentifiedItemsConfig.iron_armor_drop_weight.get();
    final int   diamond_weight = UnidentifiedItemsConfig.diamond_armor_drop_weight.get();
    final LootPool.Builder loot = new LootPool.Builder();
    // Not how I would've designed it, but this is the best I can do.
    // As long as items of the same type have the same weight, and each type has
    // the same number of items, then they all have an equal chance of being picked.
    // For instance: [30, 18, 9, 9, 3, 4] is the same as [120, 72, 36, 36, 12, 16]
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.LEATHER,   EquipmentType.HELMET    )).setWeight(leather_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.LEATHER,   EquipmentType.CHESTPLATE)).setWeight(leather_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.LEATHER,   EquipmentType.LEGGINGS  )).setWeight(leather_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.LEATHER,   EquipmentType.BOOTS     )).setWeight(leather_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.GOLD,      EquipmentType.HELMET    )).setWeight(gold_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.GOLD,      EquipmentType.CHESTPLATE)).setWeight(gold_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.GOLD,      EquipmentType.LEGGINGS  )).setWeight(gold_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.GOLD,      EquipmentType.BOOTS     )).setWeight(gold_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.CHAINMAIL, EquipmentType.HELMET    )).setWeight(chainmail_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.CHAINMAIL, EquipmentType.CHESTPLATE)).setWeight(chainmail_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.CHAINMAIL, EquipmentType.LEGGINGS  )).setWeight(chainmail_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.CHAINMAIL, EquipmentType.BOOTS     )).setWeight(chainmail_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.IRON,      EquipmentType.HELMET    )).setWeight(iron_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.IRON,      EquipmentType.CHESTPLATE)).setWeight(iron_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.IRON,      EquipmentType.LEGGINGS  )).setWeight(iron_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.IRON,      EquipmentType.BOOTS     )).setWeight(iron_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.DIAMOND,   EquipmentType.HELMET    )).setWeight(diamond_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.DIAMOND,   EquipmentType.CHESTPLATE)).setWeight(diamond_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.DIAMOND,   EquipmentType.LEGGINGS  )).setWeight(diamond_weight));
    loot.add(LootItem.lootTableItem(UnidentifiedItem.get(ArmorMaterial.DIAMOND,   EquipmentType.BOOTS     )).setWeight(diamond_weight));
    if(Compatibility.CURIOS.isLoaded()){
      final int      ring_weight = UnidentifiedItemsConfig.ring_drop_weight.get();
      loot.add(LootItem.lootTableItem(OverpoweredItems.ring_0.get()).setWeight(ring_weight));
      loot.add(LootItem.lootTableItem(OverpoweredItems.ring_1.get()).setWeight(ring_weight));
      loot.add(LootItem.lootTableItem(OverpoweredItems.ring_2.get()).setWeight(ring_weight));
      loot.add(LootItem.lootTableItem(OverpoweredItems.ring_3.get()).setWeight(ring_weight));
    }
    loot.when(LootItemKilledByPlayerCondition.killedByPlayer());
    loot.when(LootItemRandomChanceCondition.randomChance(drop_chance));
    loot.name("overpowered_custom_loot_table");
    return loot.build();
  }

  private static final boolean debug_loot_tables = false;
  private static final String prefix = "minecraft:entities/";
  
  @SubscribeEvent
  public static final void inject_loot(final LootTableLoadEvent event){
    final String name = event.getName().toString();
    if(name.startsWith(prefix)){
      if(debug_loot_tables){
        OverpoweredTechnology.log.info("Loading Loot Table: "+name);
      }
      final String mob = name.substring(prefix.length());
      if(mob.equals("zombie")          ){addDrops(UnidentifiedItemsConfig.ZOMBIE,           event); return;}
      if(mob.equals("zombie_villager") ){addDrops(UnidentifiedItemsConfig.ZOMBIE_VILLAGER,  event); return;}
      if(mob.equals("husk")            ){addDrops(UnidentifiedItemsConfig.HUSK,             event); return;}
      if(mob.equals("spider")          ){addDrops(UnidentifiedItemsConfig.SPIDER,           event); return;}
      if(mob.equals("cave_spider")     ){addDrops(UnidentifiedItemsConfig.CAVE_SPIDER,      event); return;}
      if(mob.equals("creeper")         ){addDrops(UnidentifiedItemsConfig.CREEPER,          event); return;}
      if(mob.equals("skeleton")        ){addDrops(UnidentifiedItemsConfig.SKELETON,         event); return;}
      if(mob.equals("zombified_piglin")){addDrops(UnidentifiedItemsConfig.ZOMBIFIED_PIGLIN, event); return;}
      if(mob.equals("piglin")          ){addDrops(UnidentifiedItemsConfig.PIGLIN,           event); return;}
      if(mob.equals("piglin_brute")    ){addDrops(UnidentifiedItemsConfig.PIGLIN_BRUTE,     event); return;}
      if(mob.equals("blaze")           ){addDrops(UnidentifiedItemsConfig.BLAZE,            event); return;}
      if(mob.equals("witch")           ){addDrops(UnidentifiedItemsConfig.WITCH,            event); return;}
      if(mob.equals("ghast")           ){addDrops(UnidentifiedItemsConfig.GHAST,            event); return;}
      if(mob.equals("enderman")        ){addDrops(UnidentifiedItemsConfig.ENDERMAN,         event); return;}
      if(mob.equals("stray")           ){addDrops(UnidentifiedItemsConfig.STRAY,            event); return;}
      if(mob.equals("guardian")        ){addDrops(UnidentifiedItemsConfig.GUARDIAN,         event); return;}
      if(mob.equals("elder_guardian")  ){addDrops(UnidentifiedItemsConfig.ELDER_GUARDIAN,   event); return;}
      if(mob.equals("wither_skeleton") ){addDrops(UnidentifiedItemsConfig.WITHER_SKELETON,  event); return;}
      if(mob.equals("magma_cube")      ){addDrops(UnidentifiedItemsConfig.MAGMA_CUBE,       event); return;}
      if(mob.equals("hoglin")          ){addDrops(UnidentifiedItemsConfig.HOGLIN,           event); return;}
      if(mob.equals("zoglin")          ){addDrops(UnidentifiedItemsConfig.ZOGLIN,           event); return;}
      if(mob.equals("shulker")         ){addDrops(UnidentifiedItemsConfig.SHULKER,          event); return;}
      if(mob.equals("vex")             ){addDrops(UnidentifiedItemsConfig.VEX,              event); return;}
      if(mob.equals("evoker")          ){addDrops(UnidentifiedItemsConfig.EVOKER,           event); return;}
      if(mob.equals("vindicator")      ){addDrops(UnidentifiedItemsConfig.VINDICATOR,       event); return;}
      if(mob.equals("illusioner")      ){addDrops(UnidentifiedItemsConfig.ILLUSIONER,       event); return;}
      if(mob.equals("drowned")         ){addDrops(UnidentifiedItemsConfig.DROWNED,          event); return;}
      if(mob.equals("phantom")         ){addDrops(UnidentifiedItemsConfig.PHANTOM,          event); return;}
      if(mob.equals("skeleton_horse")  ){addDrops(UnidentifiedItemsConfig.SKELETON_HORSE,   event); return;}
      if(mob.equals("pillager")        ){addDrops(UnidentifiedItemsConfig.PILLAGER,         event); return;}
      if(mob.equals("ravager")         ){addDrops(UnidentifiedItemsConfig.RAVAGER,          event); return;}
      if(mob.equals("ender_dragon")    ){addDrops(UnidentifiedItemsConfig.END_DRAGON,       event); return;}
      if(mob.equals("wither")          ){addDrops(UnidentifiedItemsConfig.WITHER,           event); return;}
      if(mob.equals("warden")          ){addDrops(UnidentifiedItemsConfig.WARDEN,           event); return;}
    }
  }

  private static final void addDrops(final MobDropConfigEntry config, final LootTableLoadEvent event){
    if(config.drop.get()){
      event.getTable().addPool(build_loot_pool(config.chance.get().floatValue()));
      if(debug_loot_tables){
        OverpoweredTechnology.log.info("  Successfully injected custom loot pool into Loot Table for: "+config.mob_name);
      }
    }
  }

}
