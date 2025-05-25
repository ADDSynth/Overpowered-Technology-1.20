package addsynth.core.gameplay.registers;

import addsynth.core.ADDSynthCore;
import addsynth.core.game.registry.BlockItemHolder;
import addsynth.core.gameplay.Core;
import addsynth.core.gameplay.Trophy;
import addsynth.core.gameplay.blocks.CautionBlock;
import addsynth.core.gameplay.blocks.TrophyBlock;
import addsynth.core.gameplay.music_box.MusicBox;
import addsynth.core.gameplay.music_box.MusicSheet;
import addsynth.core.gameplay.reference.Names;
import addsynth.core.gameplay.team_manager.TeamManagerBlock;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
    if(key.equals(ForgeRegistries.Keys.BLOCKS)){
      final IForgeRegistry<Block> registry = event.getForgeRegistry();
      registry.register(Names.CAUTION_BLOCK,   new CautionBlock());
      registry.register(Names.MUSIC_BOX,       new MusicBox());
      registry.register(Names.TEAM_MANAGER,    new TeamManagerBlock());
      registry.register(Names.BRONZE_TROPHY,   new TrophyBlock());
      registry.register(Names.SILVER_TROPHY,   new TrophyBlock());
      registry.register(Names.GOLD_TROPHY,     new TrophyBlock());
      registry.register(Names.PLATINUM_TROPHY, new TrophyBlock());
      // registry.register(Names.TEST_BLOCK, new TestBlock());
    }
    if(key.equals(ForgeRegistries.Keys.ITEMS)){
      final IForgeRegistry<Item> registry = event.getForgeRegistry();
      BlockItemHolder.register(registry, Core.caution_block);
      BlockItemHolder.register(registry, Core.music_box);
      registry.register(Names.MUSIC_SHEET,     new MusicSheet());
      BlockItemHolder.register(registry, Core.team_manager);
      registry.register(Names.TROPHY_BASE,     new Item(new Item.Properties()));
      BlockItemHolder.register(registry, Trophy.bronze);
      BlockItemHolder.register(registry, Trophy.silver);
      BlockItemHolder.register(registry, Trophy.gold);
      BlockItemHolder.register(registry, Trophy.platinum);
      // registry.register(Names.TEST_BLOCK, newBlockItem(Core.test_block));
    }
    if(key.equals(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES)){
      final IForgeRegistry<BlockEntityType> registry = event.getForgeRegistry();
      Tiles.MUSIC_BOX.register(registry);
    }
    if(key.equals(ForgeRegistries.Keys.MENU_TYPES)){
      final IForgeRegistry<MenuType> registry = event.getForgeRegistry();
    }
  }

}
