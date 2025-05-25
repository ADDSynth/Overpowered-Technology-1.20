package addsynth.material;

import java.util.List;
import addsynth.core.compat.Compatibility;
import addsynth.material.compat.recipe.BronzeModAbsentCondition;
import addsynth.material.compat.recipe.SteelModAbsentCondition;
import addsynth.material.reference.Names;
import addsynth.material.types.Gem;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.registries.MissingMappingsEvent.Mapping;
import net.minecraftforge.registries.RegisterEvent;

@EventBusSubscriber(modid = ADDSynthMaterials.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class MaterialsRegister {

  @SubscribeEvent
  public static final void register(final RegisterEvent event){
    final ResourceKey key = event.getRegistryKey();
    if(key.equals(ForgeRegistries.Keys.BLOCKS)){
      final IForgeRegistry<Block> registry = event.getForgeRegistry();
      
      // metal blocks
      Material.TIN.registerBlock(registry);
      Material.ALUMINUM.registerBlock(registry);
      Material.SILVER.registerBlock(registry);
      Material.PLATINUM.registerBlock(registry);
      Material.TITANIUM.registerBlock(registry);
      Material.STEEL.registerBlock(registry);
      Material.BRONZE.registerBlock(registry);
      
      // metal ores
      Material.TIN.registerOre(registry);
      Material.ALUMINUM.registerOre(registry);
      Material.SILVER.registerOre(registry);
      Material.PLATINUM.registerOre(registry);
      Material.TITANIUM.registerOre(registry);
      
      // gem blocks
      Material.RUBY.registerBlock(registry);
      Material.TOPAZ.registerBlock(registry);
      Material.CITRINE.registerBlock(registry);
      Material.SAPPHIRE.registerBlock(registry);
      
      // gem ores
      Material.RUBY.registerOre(registry);
      Material.TOPAZ.registerOre(registry);
      Material.CITRINE.registerOre(registry);
      Material.SAPPHIRE.registerOre(registry);
      registry.register(Names.AMETHYST_ORE, Gem.getOreBlock());

      // other materials
      Material.SILICON.registerOre(registry);
      Material.ROSE_QUARTZ.registerOre(registry);
      
    }
    if(key.equals(ForgeRegistries.Keys.ITEMS)){
      final IForgeRegistry<Item> registry = event.getForgeRegistry();
  
      // metal ingots
      Material.TIN.registerIngot(registry);
      Material.ALUMINUM.registerIngot(registry);
      Material.SILVER.registerIngot(registry);
      Material.PLATINUM.registerIngot(registry);
      Material.TITANIUM.registerIngot(registry);
      Material.STEEL.registerIngot(registry);
      Material.BRONZE.registerIngot(registry);

      // metal blocks
      Material.TIN.registerBlockItem(registry);
      Material.ALUMINUM.registerBlockItem(registry);
      Material.SILVER.registerBlockItem(registry);
      Material.PLATINUM.registerBlockItem(registry);
      Material.TITANIUM.registerBlockItem(registry);
      Material.STEEL.registerBlockItem(registry);
      Material.BRONZE.registerBlockItem(registry);

      // metal ores
      Material.TIN.registerOreItem(registry);
      Material.ALUMINUM.registerOreItem(registry);
      Material.SILVER.registerOreItem(registry);
      Material.PLATINUM.registerOreItem(registry);
      Material.TITANIUM.registerOreItem(registry);

      // metal plates
      if(Compatibility.ADDSYNTH_ENERGY.isLoaded()){
        registry.register(Names.IRON_PLATE,   new Item(new Item.Properties()));
        registry.register(Names.COPPER_PLATE, new Item(new Item.Properties()));
        registry.register(Names.GOLD_PLATE,   new Item(new Item.Properties()));
        Material.TIN.registerPlate(registry);
        Material.ALUMINUM.registerPlate(registry);
        Material.SILVER.registerPlate(registry);
        Material.PLATINUM.registerPlate(registry);
        Material.TITANIUM.registerPlate(registry);
        Material.STEEL.registerPlate(registry);
        Material.BRONZE.registerPlate(registry);
      }

      // gems
      Material.RUBY.registerGem(registry);
      Material.TOPAZ.registerGem(registry);
      Material.CITRINE.registerGem(registry);
      Material.SAPPHIRE.registerGem(registry);
      
      // gem blocks
      Material.RUBY.registerBlockItem(registry);
      Material.TOPAZ.registerBlockItem(registry);
      Material.CITRINE.registerBlockItem(registry);
      Material.SAPPHIRE.registerBlockItem(registry);
      
      // gem ores
      Material.RUBY.registerOreItem(registry);
      Material.TOPAZ.registerOreItem(registry);
      Material.CITRINE.registerOreItem(registry);
      Material.SAPPHIRE.registerOreItem(registry);
      registry.register(Names.AMETHYST_ORE, new BlockItem(Material.AMETHYST.ore.get(), new Item.Properties()));
      
      // other materials
      Material.SILICON.registerItem(registry);
      Material.SILICON.registerOreItem(registry);
      Material.ROSE_QUARTZ.registerItem(registry);
      Material.ROSE_QUARTZ.registerOreItem(registry);
    }
    if(key.equals(ForgeRegistries.Keys.RECIPE_SERIALIZERS)){
      CraftingHelper.register(SteelModAbsentCondition.Serializer.INSTANCE);
      CraftingHelper.register(BronzeModAbsentCondition.Serializer.INSTANCE);
    }
  }

  public static final void onMissingEntries(MissingMappingsEvent event){
    // handle blocks
    final List<Mapping<Block>> missing_blocks = event.getMappings(Keys.BLOCKS, ADDSynthMaterials.MOD_ID);
    for(Mapping<Block> map : missing_blocks){
      if(map.getKey().equals(Names.AMETHYST_BLOCK_LEGACY)){
        map.remap(Blocks.AMETHYST_BLOCK);
      }
    }
    
    // handle items
    final List<Mapping<Item>> missing_items = event.getMappings(Keys.ITEMS, ADDSynthMaterials.MOD_ID);
    for(Mapping<Item> map : missing_items){
      if(map.getKey().equals(Names.AMETHYST_BLOCK_LEGACY)){
        map.remap(Items.AMETHYST_BLOCK);
      }
      if(map.getKey().equals(Names.AMETHYST_LEGACY)){
        map.remap(Items.AMETHYST_SHARD);
      }
    }
  }

}
