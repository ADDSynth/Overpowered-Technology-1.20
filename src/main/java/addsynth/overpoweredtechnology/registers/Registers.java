package addsynth.overpoweredtechnology.registers;

import java.util.List;
import addsynth.core.compat.Compatibility;
import addsynth.core.game.item.AdvancedItem;
import addsynth.core.game.item.constants.ArmorMaterial;
import addsynth.core.game.item.constants.EquipmentType;
import addsynth.core.game.registry.RegistryUtil;
import addsynth.overpoweredtechnology.OverpoweredTechnology;
import addsynth.overpoweredtechnology.assets.CreativeTab;
import addsynth.overpoweredtechnology.assets.Sounds;
import addsynth.overpoweredtechnology.blocks.*;
import addsynth.overpoweredtechnology.blocks.dimension.tree.*;
import addsynth.overpoweredtechnology.game.core.*;
import addsynth.overpoweredtechnology.game.reference.Names;
import addsynth.overpoweredtechnology.game.reference.OverpoweredBlocks;
import addsynth.overpoweredtechnology.game.reference.OverpoweredItems;
import addsynth.overpoweredtechnology.items.*;
import addsynth.overpoweredtechnology.items.basic.*;
import addsynth.overpoweredtechnology.items.tools.LaserSword;
import addsynth.overpoweredtechnology.items.tools.celestial.*;
import addsynth.overpoweredtechnology.items.tools.infinity.*;
import addsynth.overpoweredtechnology.items.tools.void_tools.*;
import addsynth.overpoweredtechnology.machines.advanced_gem_converter.AdvancedGemConverterBlock;
import addsynth.overpoweredtechnology.machines.advanced_gem_converter.AdvancedGemConverterContainer;
import addsynth.overpoweredtechnology.machines.advanced_ore_refinery.AdvancedOreRefineryBlock;
import addsynth.overpoweredtechnology.machines.advanced_ore_refinery.ContainerOreRefinery;
import addsynth.overpoweredtechnology.machines.black_hole.BlackHoleBlock;
import addsynth.overpoweredtechnology.machines.black_hole.BlackHoleItem;
import addsynth.overpoweredtechnology.machines.crystal_matter_generator.ContainerCrystalGenerator;
import addsynth.overpoweredtechnology.machines.crystal_matter_generator.CrystalMatterGeneratorBlock;
import addsynth.overpoweredtechnology.machines.data_cable.DataCable;
import addsynth.overpoweredtechnology.machines.energy_extractor.ContainerEnergyExtractor;
import addsynth.overpoweredtechnology.machines.energy_extractor.EnergyExtractorBlock;
import addsynth.overpoweredtechnology.machines.fusion.chamber.ContainerFusionChamber;
import addsynth.overpoweredtechnology.machines.fusion.chamber.FusionChamberBlock;
import addsynth.overpoweredtechnology.machines.fusion.control.*;
import addsynth.overpoweredtechnology.machines.fusion.converter.FusionEnergyConverterBlock;
import addsynth.overpoweredtechnology.machines.gem_converter.ContainerGemConverter;
import addsynth.overpoweredtechnology.machines.gem_converter.GemConverterBlock;
import addsynth.overpoweredtechnology.machines.identifier.ContainerIdentifier;
import addsynth.overpoweredtechnology.machines.identifier.IdentifierBlock;
import addsynth.overpoweredtechnology.machines.inverter.ContainerInverter;
import addsynth.overpoweredtechnology.machines.inverter.InverterBlock;
import addsynth.overpoweredtechnology.machines.laser.machine.ContainerLaserHousing;
import addsynth.overpoweredtechnology.machines.laser.machine.LaserHousingBlock;
import addsynth.overpoweredtechnology.machines.magic_infuser.ContainerMagicInfuser;
import addsynth.overpoweredtechnology.machines.magic_infuser.MagicInfuserBlock;
import addsynth.overpoweredtechnology.machines.magic_infuser.recipes.MagicInfuserRecipeSerializer;
import addsynth.overpoweredtechnology.machines.matter_compressor.MatterCompressorBlock;
import addsynth.overpoweredtechnology.machines.matter_compressor.MatterCompressorContainer;
import addsynth.overpoweredtechnology.machines.plasma_generator.ContainerPlasmaGenerator;
import addsynth.overpoweredtechnology.machines.plasma_generator.PlasmaGeneratorBlock;
import addsynth.overpoweredtechnology.machines.portal.control_panel.ContainerPortalControlPanel;
import addsynth.overpoweredtechnology.machines.portal.control_panel.PortalControlPanelBlock;
import addsynth.overpoweredtechnology.machines.portal.frame.ContainerPortalFrame;
import addsynth.overpoweredtechnology.machines.portal.frame.PortalFrameBlock;
import addsynth.overpoweredtechnology.machines.portal.rift.PortalEnergyBlock;
import addsynth.overpoweredtechnology.machines.suspension_bridge.ContainerSuspensionBridge;
import addsynth.overpoweredtechnology.machines.suspension_bridge.EnergyBridge;
import addsynth.overpoweredtechnology.machines.suspension_bridge.EnergySuspensionBridgeBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.registries.MissingMappingsEvent.Mapping;
import net.minecraftforge.registries.RegisterEvent;

@EventBusSubscriber(modid = OverpoweredTechnology.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class Registers {

  @SubscribeEvent
  public static final void register(final RegisterEvent event){
    final ResourceKey key = event.getRegistryKey();
    if(key.equals(ForgeRegistries.Keys.BLOCKS)){
      final IForgeRegistry<Block> registry = event.getForgeRegistry();
      
      registry.register(Names.LIGHT_BLOCK,                 new LightBlock());
      registry.register(Names.NULL_BLOCK,                  new NullBlock());
      registry.register(Names.IRON_FRAME_BLOCK,            new IronFrameBlock());
      registry.register(Names.BLACK_HOLE,                  new BlackHoleBlock());
      
      registry.register(Names.DATA_CABLE,                  new DataCable());
      registry.register(Names.ENERGY_EXTRACTOR,            new EnergyExtractorBlock());
      registry.register(Names.GEM_CONVERTER,               new GemConverterBlock());
      registry.register(Names.IDENTIFIER,                  new IdentifierBlock());
      registry.register(Names.INVERTER,                    new InverterBlock());
      registry.register(Names.MAGIC_INFUSER,               new MagicInfuserBlock());
      
      registry.register(Names.ENERGY_SUSPENSION_BRIDGE,    new EnergySuspensionBridgeBlock());
      registry.register(DeviceColor.WHITE.energy_bridge,   new EnergyBridge(DeviceColor.WHITE));
      registry.register(DeviceColor.RED.energy_bridge,     new EnergyBridge(DeviceColor.RED));
      registry.register(DeviceColor.ORANGE.energy_bridge,  new EnergyBridge(DeviceColor.ORANGE));
      registry.register(DeviceColor.YELLOW.energy_bridge,  new EnergyBridge(DeviceColor.YELLOW));
      registry.register(DeviceColor.GREEN.energy_bridge,   new EnergyBridge(DeviceColor.GREEN));
      registry.register(DeviceColor.CYAN.energy_bridge,    new EnergyBridge(DeviceColor.CYAN));
      registry.register(DeviceColor.BLUE.energy_bridge,    new EnergyBridge(DeviceColor.BLUE));
      registry.register(DeviceColor.MAGENTA.energy_bridge, new EnergyBridge(DeviceColor.MAGENTA));
      
      registry.register(Names.PORTAL_CONTROL_PANEL,        new PortalControlPanelBlock());
      registry.register(Names.PORTAL_FRAME,                new PortalFrameBlock());
      registry.register(Names.PORTAL_RIFT,                 new PortalEnergyBlock());
      registry.register(Names.UNKNOWN_WOOD,                new UnknownWood());
      registry.register(Names.UNKNOWN_LEAVES,              new UnknownLeaves());
      
      registry.register(Names.PLASMA_GENERATOR,            new PlasmaGeneratorBlock());
      registry.register(Names.CRYSTAL_MATTER_GENERATOR,    new CrystalMatterGeneratorBlock());
      registry.register(Names.ADVANCED_ORE_REFINERY,       new AdvancedOreRefineryBlock());
      
      registry.register(Names.LASER_HOUSING,               new LaserHousingBlock());
      Laser.WHITE.registerBlocks(registry);
      Laser.RED.registerBlocks(registry);
      Laser.ORANGE.registerBlocks(registry);
      Laser.YELLOW.registerBlocks(registry);
      Laser.GREEN.registerBlocks(registry);
      Laser.CYAN.registerBlocks(registry);
      Laser.BLUE.registerBlocks(registry);
      Laser.MAGENTA.registerBlocks(registry);
      
      registry.register(Names.FUSION_CONVERTER,            new FusionEnergyConverterBlock());
      registry.register(Names.FUSION_CONTROL_UNIT,         new FusionControlUnit());
      registry.register(Names.FUSION_CHAMBER,              new FusionChamberBlock());
      registry.register(Names.FUSION_CONTROL_LASER,        new FusionLaser());
      registry.register(Names.FUSION_CONTROL_LASER_BEAM,   new FusionControlLaserBeam());
      
      registry.register(Names.MATTER_COMPRESSOR,           new MatterCompressorBlock());
      registry.register(Names.ADVANCED_GEM_CONVERTER,      new AdvancedGemConverterBlock());
    }
    if(key.equals(ForgeRegistries.Keys.ITEMS)){
      final IForgeRegistry<Item> registry = event.getForgeRegistry();
      
      registry.register(Names.CELESTIAL_GEM,              new Item(new Item.Properties()));
      registry.register(Names.ENERGY_CRYSTAL_SHARDS,      new EnergyCrystalShards());
      registry.register(Names.ENERGY_CRYSTAL,             new EnergyCrystal());
      RegistryUtil.register(registry, OverpoweredBlocks.light_block);
      registry.register(Names.VOID_CRYSTAL,               new VoidCrystal());
      RegistryUtil.register(registry, OverpoweredBlocks.null_block);
      
      registry.register(Names.ENERGIZED_POWER_CORE,       new Item(new Item.Properties()));
      registry.register(Names.NULLIFIED_POWER_CORE,       new Item(new Item.Properties()));
      registry.register(Names.ENERGY_GRID,                new Item(new Item.Properties()));
      registry.register(Names.VACUUM_CONTAINER,           new Item(new Item.Properties()));
      registry.register(Names.REINFORCED_CONTAINER,       new Item(new Item.Properties()));
      
      registry.register(Names.BEAM_EMITTER,               new Item(new Item.Properties()));
      registry.register(Names.SCANNING_LASER,             new Item(new Item.Properties()));
      registry.register(Names.DESTRUCTIVE_LASER,          new Item(new Item.Properties()));
      registry.register(Names.HEAVY_LIGHT_EMITTER,        new Item(new Item.Properties()));
      registry.register(Names.ENERGY_STABILIZER,          new Item(new Item.Properties()));
      registry.register(Names.MATTER_ENERGY_TRANSFORMER,  new Item(new Item.Properties()));
      registry.register(Names.HIGH_FREQUENCY_BEAM,        new Item(new Item.Properties()));
      
      registry.register(DeviceColor.WHITE.lens_name,      new LensItem(DeviceColor.WHITE));
      registry.register(DeviceColor.RED.lens_name,        new LensItem(DeviceColor.RED));
      registry.register(DeviceColor.ORANGE.lens_name,     new LensItem(DeviceColor.ORANGE));
      registry.register(DeviceColor.YELLOW.lens_name,     new LensItem(DeviceColor.YELLOW));
      registry.register(DeviceColor.GREEN.lens_name,      new LensItem(DeviceColor.GREEN));
      registry.register(DeviceColor.CYAN.lens_name,       new LensItem(DeviceColor.CYAN));
      registry.register(DeviceColor.BLUE.lens_name,       new LensItem(DeviceColor.BLUE));
      registry.register(DeviceColor.MAGENTA.lens_name,    new LensItem(DeviceColor.MAGENTA));
      
      registry.register(Names.PLASMA,                     new AdvancedItem(ChatFormatting.AQUA));
      registry.register(Names.FUSION_CORE,                new AdvancedItem(ChatFormatting.GOLD));
      registry.register(Names.MATTER_ENERGY_CORE,         new Item(new Item.Properties()));
      registry.register(Names.DIMENSIONAL_FLUX,           new AdvancedItem(ChatFormatting.LIGHT_PURPLE));
      registry.register(Names.DIMENSIONAL_ANCHOR,         new DimensionalAnchor());
      registry.register(Names.UNIMATTER,                  new AdvancedItem(ChatFormatting.GRAY));
      
      RegistryUtil.register(registry, OverpoweredBlocks.data_cable);
      RegistryUtil.register(registry, OverpoweredBlocks.energy_extractor);
      RegistryUtil.register(registry, OverpoweredBlocks.gem_converter);
      RegistryUtil.register(registry, OverpoweredBlocks.identifier);
      RegistryUtil.register(registry, OverpoweredBlocks.inverter);
      RegistryUtil.register(registry, OverpoweredBlocks.magic_infuser);
      RegistryUtil.register(registry, OverpoweredBlocks.energy_suspension_bridge);
      RegistryUtil.register(registry, OverpoweredBlocks.portal_control_panel);
      RegistryUtil.register(registry, OverpoweredBlocks.portal_frame);
      RegistryUtil.register(registry, OverpoweredBlocks.plasma_generator);
      RegistryUtil.register(registry, OverpoweredBlocks.crystal_matter_generator);
      RegistryUtil.register(registry, OverpoweredBlocks.advanced_ore_refinery);
      
      RegistryUtil.register(registry, OverpoweredBlocks.laser_housing);
      RegistryUtil.register(registry, Laser.WHITE.cannon);
      RegistryUtil.register(registry, Laser.RED.cannon);
      RegistryUtil.register(registry, Laser.ORANGE.cannon);
      RegistryUtil.register(registry, Laser.YELLOW.cannon);
      RegistryUtil.register(registry, Laser.GREEN.cannon);
      RegistryUtil.register(registry, Laser.CYAN.cannon);
      RegistryUtil.register(registry, Laser.BLUE.cannon);
      RegistryUtil.register(registry, Laser.MAGENTA.cannon);
      
      RegistryUtil.register(registry, OverpoweredBlocks.fusion_converter);
      RegistryUtil.register(registry, OverpoweredBlocks.fusion_control_unit);
      RegistryUtil.register(registry, OverpoweredBlocks.fusion_chamber);
      RegistryUtil.register(registry, OverpoweredBlocks.fusion_control_laser);
      
      RegistryUtil.register(registry, OverpoweredBlocks.matter_compressor);
      RegistryUtil.register(registry, OverpoweredBlocks.advanced_gem_converter);
      
      RegistryUtil.register(registry, OverpoweredBlocks.iron_frame_block);
      registry.register(Names.BLACK_HOLE,                 new BlackHoleItem());
      
      registry.register(DeviceColor.WHITE.laser_sword,   new LaserSword());
      registry.register(DeviceColor.RED.laser_sword,     new LaserSword());
      registry.register(DeviceColor.ORANGE.laser_sword,  new LaserSword());
      registry.register(DeviceColor.YELLOW.laser_sword,  new LaserSword());
      registry.register(DeviceColor.GREEN.laser_sword,   new LaserSword());
      registry.register(DeviceColor.CYAN.laser_sword,    new LaserSword());
      registry.register(DeviceColor.BLUE.laser_sword,    new LaserSword());
      registry.register(DeviceColor.MAGENTA.laser_sword, new LaserSword());
      
      registry.register(Names.CELESTIAL_SWORD,            new CelestialSword());
      registry.register(Names.CELESTIAL_SHOVEL,           new CelestialShovel());
      registry.register(Names.CELESTIAL_PICKAXE,          new CelestialPickaxe());
      registry.register(Names.CELESTIAL_AXE,              new CelestialAxe());
      registry.register(Names.CELESTIAL_HOE,              new CelestialHoe());
      registry.register(Names.VOID_SWORD,                 new VoidSword());
      registry.register(Names.VOID_SHOVEL,                new VoidShovel());
      registry.register(Names.VOID_PICKAXE,               new VoidPickaxe());
      registry.register(Names.VOID_AXE,                   new VoidAxe());
      registry.register(Names.VOID_HOE,                   new VoidHoe());
      registry.register(Names.INFINITY_SWORD,             new InfinitySword());
      registry.register(Names.INFINITY_SHOVEL,            new InfinityShovel());
      registry.register(Names.INFINITY_PICKAXE,           new InfinityPickaxe());
      registry.register(Names.INFINITY_AXE,               new InfinityAxe());
      registry.register(Names.INFINITY_HOE,               new InfinityHoe());
      
      for(ArmorMaterial material : ArmorMaterial.values()){
        if(material != ArmorMaterial.NETHERITE){
          for(EquipmentType equipment : EquipmentType.values()){
            UnidentifiedItem.register(registry, material, equipment);
          }
        }
      }
      
      if(Compatibility.CURIOS.isLoaded()){
        registry.register(Names.UNIDENTIFIED_RING[0],     new UnidentifiedItem(0));
        registry.register(Names.UNIDENTIFIED_RING[1],     new UnidentifiedItem(1));
        registry.register(Names.UNIDENTIFIED_RING[2],     new UnidentifiedItem(2));
        registry.register(Names.UNIDENTIFIED_RING[3],     new UnidentifiedItem(3));
        registry.register(Names.MAGIC_RING[0],            new Ring());
        registry.register(Names.MAGIC_RING[1],            new Ring());
        registry.register(Names.MAGIC_RING[2],            new Ring());
        registry.register(Names.MAGIC_RING[3],            new Ring());
      }
      
      // Items for advancements only
      registry.register(Names.PORTAL_RIFT,                new BlockItem(OverpoweredBlocks.portal.get(),             new Item.Properties()));
      registry.register(DeviceColor.BLUE.energy_bridge,   new BlockItem(OverpoweredBlocks.blue_energy_bridge.get(), new Item.Properties()));
    }
    if(key.equals(Registries.CREATIVE_MODE_TAB)){
      final Registry<CreativeModeTab> registry = event.getVanillaRegistry();
      CreativeTab.register(registry);
    }
    if(key.equals(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES)){
      /*
        https://github.com/MinecraftForge/MinecraftForge/pull/4681#issuecomment-405115908
        TODO: If anyone needs an example of how to fix the warning caused by this change without breaking old saved games,
        I (not ADDSynth, someone else) just updated all of McJty's mods to use a DataFixer to do so.
      */
      final IForgeRegistry<BlockEntityType> registry = event.getForgeRegistry();
      Tiles.ENERGY_EXTRACTOR.register(registry);
      Tiles.GEM_CONVERTER.register(registry);
      Tiles.IDENTIFIER.register(registry);
      Tiles.INVERTER.register(registry);
      Tiles.MAGIC_INFUSER.register(registry);
      Tiles.ENERGY_SUSPENSION_BRIDGE.register(registry);
      Tiles.LASER_MACHINE.register(registry);
      Tiles.DATA_CABLE.register(registry);
      Tiles.PORTAL_CONTROL_PANEL.register(registry);
      Tiles.PORTAL_FRAME.register(registry);
      Tiles.PORTAL_RIFT.register(registry);
      Tiles.PLASMA_GENERATOR.register(registry);
      Tiles.CRYSTAL_MATTER_REPLICATOR.register(registry);
      Tiles.ADVANCED_ORE_REFINERY.register(registry);
      Tiles.FUSION_ENERGY_CONVERTER.register(registry);
      Tiles.FUSION_CHAMBER.register(registry);
      Tiles.BLACK_HOLE.register(registry);
      Tiles.MATTER_COMPRESSOR.register(registry);
      Tiles.ADVANCED_GEM_CONVERTER.register(registry);
    }
    if(key.equals(ForgeRegistries.Keys.MENU_TYPES)){
      final IForgeRegistry<MenuType> registry = event.getForgeRegistry();
      registry.register(Names.ENERGY_EXTRACTOR,         IForgeMenuType.create(ContainerEnergyExtractor::new));
      registry.register(Names.GEM_CONVERTER,            IForgeMenuType.create(ContainerGemConverter::new));
      registry.register(Names.IDENTIFIER,               IForgeMenuType.create(ContainerIdentifier::new));
      registry.register(Names.INVERTER,                 IForgeMenuType.create(ContainerInverter::new));
      registry.register(Names.MAGIC_INFUSER,            IForgeMenuType.create(ContainerMagicInfuser::new));
      registry.register(Names.ENERGY_SUSPENSION_BRIDGE, IForgeMenuType.create(ContainerSuspensionBridge::new));
      registry.register(Names.LASER_HOUSING,            IForgeMenuType.create(ContainerLaserHousing::new));
      registry.register(Names.PLASMA_GENERATOR,         IForgeMenuType.create(ContainerPlasmaGenerator::new));
      registry.register(Names.ADVANCED_ORE_REFINERY,    IForgeMenuType.create(ContainerOreRefinery::new));
      registry.register(Names.CRYSTAL_MATTER_GENERATOR, IForgeMenuType.create(ContainerCrystalGenerator::new));
      registry.register(Names.FUSION_CHAMBER,           IForgeMenuType.create(ContainerFusionChamber::new));
      registry.register(Names.PORTAL_CONTROL_PANEL,     IForgeMenuType.create(ContainerPortalControlPanel::new));
      registry.register(Names.PORTAL_FRAME,             IForgeMenuType.create(ContainerPortalFrame::new));
      registry.register(Names.MATTER_COMPRESSOR,        IForgeMenuType.create(MatterCompressorContainer::new));
      registry.register(Names.ADVANCED_GEM_CONVERTER,   IForgeMenuType.create(AdvancedGemConverterContainer::new));
    }
    if(key.equals(ForgeRegistries.Keys.RECIPE_TYPES)){
      final IForgeRegistry<RecipeType<?>> registry = event.getForgeRegistry();
      RegistryUtil.registerRecipeType(registry, Names.MAGIC_INFUSER);
    }
    if(key.equals(ForgeRegistries.Keys.RECIPE_SERIALIZERS)){
      final IForgeRegistry<RecipeSerializer> registry = event.getForgeRegistry();
      registry.register(Names.MAGIC_INFUSER, new MagicInfuserRecipeSerializer());
    }
    if(key.equals(ForgeRegistries.Keys.SOUND_EVENTS)){
      final IForgeRegistry<SoundEvent> registry = event.getForgeRegistry();
      registry.register(Sounds.Names.laser_fire, SoundEvent.createFixedRangeEvent(Sounds.Names.laser_fire, 2.0f));
    }
  }

  public static final void onMissingEntries(MissingMappingsEvent event){
    // handle items
    final List<Mapping<Item>> missing_items = event.getMappings(ForgeRegistries.Keys.ITEMS, OverpoweredTechnology.MOD_ID);
    for(Mapping<Item> map : missing_items){
      if(map.getKey().equals(Names.MATTER_ENERGY_CORE_LEGACY)){
        map.remap(OverpoweredItems.matter_energy_core.get());
      }
      if(map.getKey().equals(Names.CRYSTAL_ENERGY_EXTRACTOR_LEGACY)){
        map.remap(OverpoweredBlocks.energy_extractor.get().asItem());
      }
    }
    // handle blocks
    final List<Mapping<Block>> missing_blocks = event.getMappings(ForgeRegistries.Keys.BLOCKS, OverpoweredTechnology.MOD_ID);
    for(Mapping<Block> map : missing_blocks){
      if(map.getKey().equals(Names.CRYSTAL_ENERGY_EXTRACTOR_LEGACY)){
        map.remap(OverpoweredBlocks.energy_extractor.get());
      }
    }
  }

}
