package addsynth.overpoweredmod.registers;

import java.util.List;
import addsynth.core.compat.Compatibility;
import addsynth.core.game.item.constants.ArmorMaterial;
import addsynth.core.game.item.constants.EquipmentType;
import addsynth.core.game.registry.BlockItemHolder;
import addsynth.core.game.registry.RegistryUtil;
import addsynth.overpoweredmod.OverpoweredTechnology;
import addsynth.overpoweredmod.assets.Sounds;
import addsynth.overpoweredmod.blocks.*;
import addsynth.overpoweredmod.blocks.dimension.tree.*;
import addsynth.overpoweredmod.game.core.*;
import addsynth.overpoweredmod.game.reference.Names;
import addsynth.overpoweredmod.game.reference.OverpoweredBlocks;
import addsynth.overpoweredmod.game.reference.OverpoweredItems;
import addsynth.overpoweredmod.items.*;
import addsynth.overpoweredmod.items.basic.*;
import addsynth.overpoweredmod.items.tools.*;
import addsynth.overpoweredmod.machines.advanced_ore_refinery.AdvancedOreRefineryBlock;
import addsynth.overpoweredmod.machines.advanced_ore_refinery.ContainerOreRefinery;
import addsynth.overpoweredmod.machines.black_hole.BlackHoleBlock;
import addsynth.overpoweredmod.machines.black_hole.BlackHoleItem;
import addsynth.overpoweredmod.machines.crystal_matter_generator.ContainerCrystalGenerator;
import addsynth.overpoweredmod.machines.crystal_matter_generator.CrystalMatterGeneratorBlock;
import addsynth.overpoweredmod.machines.data_cable.DataCable;
import addsynth.overpoweredmod.machines.energy_extractor.ContainerEnergyExtractor;
import addsynth.overpoweredmod.machines.energy_extractor.EnergyExtractorBlock;
import addsynth.overpoweredmod.machines.fusion.chamber.ContainerFusionChamber;
import addsynth.overpoweredmod.machines.fusion.chamber.FusionChamberBlock;
import addsynth.overpoweredmod.machines.fusion.control.FusionControlLaserBeam;
import addsynth.overpoweredmod.machines.fusion.control.FusionControlUnit;
import addsynth.overpoweredmod.machines.fusion.converter.FusionEnergyConverterBlock;
import addsynth.overpoweredmod.machines.gem_converter.ContainerGemConverter;
import addsynth.overpoweredmod.machines.gem_converter.GemConverterBlock;
import addsynth.overpoweredmod.machines.identifier.ContainerIdentifier;
import addsynth.overpoweredmod.machines.identifier.IdentifierBlock;
import addsynth.overpoweredmod.machines.inverter.ContainerInverter;
import addsynth.overpoweredmod.machines.inverter.InverterBlock;
import addsynth.overpoweredmod.machines.laser.cannon.LaserCannon;
import addsynth.overpoweredmod.machines.laser.machine.ContainerLaserHousing;
import addsynth.overpoweredmod.machines.laser.machine.LaserHousingBlock;
import addsynth.overpoweredmod.machines.magic_infuser.ContainerMagicInfuser;
import addsynth.overpoweredmod.machines.magic_infuser.MagicInfuserBlock;
import addsynth.overpoweredmod.machines.magic_infuser.recipes.MagicInfuserRecipeSerializer;
import addsynth.overpoweredmod.machines.matter_compressor.MatterCompressorBlock;
import addsynth.overpoweredmod.machines.matter_compressor.MatterCompressorContainer;
import addsynth.overpoweredmod.machines.plasma_generator.ContainerPlasmaGenerator;
import addsynth.overpoweredmod.machines.plasma_generator.PlasmaGeneratorBlock;
import addsynth.overpoweredmod.machines.portal.control_panel.ContainerPortalControlPanel;
import addsynth.overpoweredmod.machines.portal.control_panel.PortalControlPanelBlock;
import addsynth.overpoweredmod.machines.portal.frame.ContainerPortalFrame;
import addsynth.overpoweredmod.machines.portal.frame.PortalFrameBlock;
import addsynth.overpoweredmod.machines.portal.rift.PortalEnergyBlock;
import addsynth.overpoweredmod.machines.suspension_bridge.ContainerSuspensionBridge;
import addsynth.overpoweredmod.machines.suspension_bridge.EnergyBridge;
import addsynth.overpoweredmod.machines.suspension_bridge.EnergySuspensionBridgeBlock;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
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
      registry.register(Names.FUSION_CONTROL_LASER,        new LaserCannon());
      registry.register(Names.FUSION_CONTROL_LASER_BEAM,   new FusionControlLaserBeam());
      
      registry.register(Names.MATTER_COMPRESSOR,           new MatterCompressorBlock());
    }
    if(key.equals(ForgeRegistries.Keys.ITEMS)){
      final IForgeRegistry<Item> registry = event.getForgeRegistry();
      
      registry.register(Names.CELESTIAL_GEM,              new Item(new Item.Properties()));
      registry.register(Names.ENERGY_CRYSTAL_SHARDS,      new EnergyCrystalShards());
      registry.register(Names.ENERGY_CRYSTAL,             new EnergyCrystal());
      BlockItemHolder.register(registry, OverpoweredBlocks.light_block);
      registry.register(Names.VOID_CRYSTAL,               new VoidCrystal());
      BlockItemHolder.register(registry, OverpoweredBlocks.null_block);
      
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
      
      registry.register(Names.PLASMA,                     new PlasmaItem());
      registry.register(Names.FUSION_CORE,                new FusionCore());
      registry.register(Names.MATTER_ENERGY_CORE,         new Item(new Item.Properties()));
      registry.register(Names.DIMENSIONAL_FLUX,           new DimensionalFlux());
      registry.register(Names.DIMENSIONAL_ANCHOR,         new DimensionalAnchor());
      registry.register(Names.UNIMATTER,                  new Item(new Item.Properties()));
      
      BlockItemHolder.register(registry, OverpoweredBlocks.data_cable);
      BlockItemHolder.register(registry, OverpoweredBlocks.energy_extractor);
      BlockItemHolder.register(registry, OverpoweredBlocks.gem_converter);
      BlockItemHolder.register(registry, OverpoweredBlocks.identifier);
      BlockItemHolder.register(registry, OverpoweredBlocks.inverter);
      BlockItemHolder.register(registry, OverpoweredBlocks.magic_infuser);
      BlockItemHolder.register(registry, OverpoweredBlocks.energy_suspension_bridge);
      BlockItemHolder.register(registry, OverpoweredBlocks.portal_control_panel);
      BlockItemHolder.register(registry, OverpoweredBlocks.portal_frame);
      BlockItemHolder.register(registry, OverpoweredBlocks.plasma_generator);
      BlockItemHolder.register(registry, OverpoweredBlocks.crystal_matter_generator);
      BlockItemHolder.register(registry, OverpoweredBlocks.advanced_ore_refinery);
      
      BlockItemHolder.register(registry, OverpoweredBlocks.laser_housing);
      BlockItemHolder.register(registry, Laser.WHITE.cannon);
      BlockItemHolder.register(registry, Laser.RED.cannon);
      BlockItemHolder.register(registry, Laser.ORANGE.cannon);
      BlockItemHolder.register(registry, Laser.YELLOW.cannon);
      BlockItemHolder.register(registry, Laser.GREEN.cannon);
      BlockItemHolder.register(registry, Laser.CYAN.cannon);
      BlockItemHolder.register(registry, Laser.BLUE.cannon);
      BlockItemHolder.register(registry, Laser.MAGENTA.cannon);
      
      BlockItemHolder.register(registry, OverpoweredBlocks.fusion_converter);
      BlockItemHolder.register(registry, OverpoweredBlocks.fusion_control_unit);
      BlockItemHolder.register(registry, OverpoweredBlocks.fusion_chamber);
      BlockItemHolder.register(registry, OverpoweredBlocks.fusion_control_laser);
      
      BlockItemHolder.register(registry, OverpoweredBlocks.matter_compressor);
      
      BlockItemHolder.register(registry, OverpoweredBlocks.iron_frame_block);
      registry.register(Names.BLACK_HOLE,                 new BlackHoleItem());
      
      registry.register(Names.CELESTIAL_SWORD,            new OverpoweredSword());
      registry.register(Names.CELESTIAL_SHOVEL,           new OverpoweredShovel());
      registry.register(Names.CELESTIAL_PICKAXE,          new OverpoweredPickaxe());
      registry.register(Names.CELESTIAL_AXE,              new OverpoweredAxe());
      registry.register(Names.CELESTIAL_HOE,              new OverpoweredHoe());
      registry.register(Names.VOID_SWORD,                 new NullSword());
      registry.register(Names.VOID_SHOVEL,                new NullShovel());
      registry.register(Names.VOID_PICKAXE,               new NullPickaxe());
      registry.register(Names.VOID_AXE,                   new NullAxe());
      registry.register(Names.VOID_HOE,                   new NullHoe());
      
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
