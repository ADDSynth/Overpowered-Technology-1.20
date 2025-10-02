package addsynth.core.gameplay.registers;

import addsynth.core.ADDSynthCore;
import addsynth.core.game.registry.BlockItemHolder;
import addsynth.core.gameplay.CreativeTab;
import addsynth.core.gameplay.blocks.CautionBlock;
import addsynth.core.gameplay.blocks.TrophyBlock;
import addsynth.core.gameplay.blocks.jukebox.JukeboxContainer;
import addsynth.core.gameplay.blocks.jukebox.JukeboxPlayer;
import addsynth.core.gameplay.blocks.music_box.MusicBox;
import addsynth.core.gameplay.blocks.music_box.MusicSheet;
import addsynth.core.gameplay.blocks.team_manager.TeamManagerBlock;
import addsynth.core.gameplay.reference.Core;
import addsynth.core.gameplay.reference.Names;
import addsynth.core.gameplay.reference.Trophy;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;

@EventBusSubscriber(modid = ADDSynthCore.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class CoreRegister {

  @SubscribeEvent
  public static final void register(final RegisterEvent event){
    final ResourceKey key = event.getRegistryKey();
    // final boolean vanilla_registry = event.getVanillaRegistry() != null;
    // ADDSynthCore.log.info(StringUtil.build("Registry Event: ", key.location(), ", Type: ", vanilla_registry ? "Vanilla" : "Forge"));
    if(key.equals(ForgeRegistries.Keys.BLOCKS)){
      final IForgeRegistry<Block> registry = event.getForgeRegistry();
      registry.register(Names.CAUTION_BLOCK,   new CautionBlock());
      registry.register(Names.MUSIC_BOX,       new MusicBox());
      registry.register(Names.TEAM_MANAGER,    new TeamManagerBlock());
      registry.register(Names.AUTO_JUKEBOX,    new JukeboxPlayer());
      registry.register(Names.BRONZE_TROPHY,   new TrophyBlock());
      registry.register(Names.SILVER_TROPHY,   new TrophyBlock());
      registry.register(Names.GOLD_TROPHY,     new TrophyBlock());
      registry.register(Names.PLATINUM_TROPHY, new TrophyBlock());
    }
    if(key.equals(ForgeRegistries.Keys.ITEMS)){
      final IForgeRegistry<Item> registry = event.getForgeRegistry();
      BlockItemHolder.register(registry, Core.caution_block);
      BlockItemHolder.register(registry, Core.music_box);
      registry.register(Names.MUSIC_SHEET,     new MusicSheet());
      BlockItemHolder.register(registry, Core.team_manager);
      BlockItemHolder.register(registry, Core.auto_jukebox);
      registry.register(Names.TROPHY_BASE,     new Item(new Item.Properties()));
      BlockItemHolder.register(registry, Trophy.bronze);
      BlockItemHolder.register(registry, Trophy.silver);
      BlockItemHolder.register(registry, Trophy.gold);
      BlockItemHolder.register(registry, Trophy.platinum);
      registry.register(Names.CONCH_SHELL,     new Item(new Item.Properties()));
      registry.register(Names.SAND_DOLLAR,     new Item(new Item.Properties()));
      registry.register(Names.COWRIE,          new Item(new Item.Properties()));
      registry.register(Names.LIONS_PAW,       new Item(new Item.Properties()));
      registry.register(Names.SCALLOP,         new Item(new Item.Properties()));
      registry.register(Names.WHITE_SCALLOP,   new Item(new Item.Properties()));
      registry.register(Names.WENTLETRAP,      new Item(new Item.Properties()));
      registry.register(Names.VENUS_COMB,      new Item(new Item.Properties()));
      registry.register(Names.PEARL,           new Item(new Item.Properties()));
    }
    if(key.equals(Registries.CREATIVE_MODE_TAB)){
      final Registry<CreativeModeTab> registry = event.getVanillaRegistry();
      CreativeTab.register(registry);
    }
    if(key.equals(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES)){
      final IForgeRegistry<BlockEntityType> registry = event.getForgeRegistry();
      Tiles.MUSIC_BOX.register(registry);
      Tiles.AUTO_JUKEBOX.register(registry);
    }
    if(key.equals(ForgeRegistries.Keys.MENU_TYPES)){
      final IForgeRegistry<MenuType> registry = event.getForgeRegistry();
      registry.register(Names.AUTO_JUKEBOX, IForgeMenuType.create(JukeboxContainer::new));
    }
  }

}
