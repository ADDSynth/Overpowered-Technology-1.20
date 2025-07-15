package addsynth.overpoweredtechnology.registers;

import addsynth.core.game.registry.BlockEntityHolder;
import addsynth.overpoweredtechnology.game.reference.Names;
import addsynth.overpoweredtechnology.game.reference.OverpoweredBlocks;
import addsynth.overpoweredtechnology.machines.advanced_ore_refinery.TileAdvancedOreRefinery;
import addsynth.overpoweredtechnology.machines.black_hole.TileBlackHole;
import addsynth.overpoweredtechnology.machines.crystal_matter_generator.TileCrystalMatterGenerator;
import addsynth.overpoweredtechnology.machines.data_cable.TileDataCable;
import addsynth.overpoweredtechnology.machines.energy_extractor.TileEnergyExtractor;
import addsynth.overpoweredtechnology.machines.fusion.chamber.TileFusionChamber;
import addsynth.overpoweredtechnology.machines.fusion.converter.TileFusionEnergyConverter;
import addsynth.overpoweredtechnology.machines.gem_converter.TileGemConverter;
import addsynth.overpoweredtechnology.machines.identifier.TileIdentifier;
import addsynth.overpoweredtechnology.machines.inverter.TileInverter;
import addsynth.overpoweredtechnology.machines.laser.machine.TileLaserHousing;
import addsynth.overpoweredtechnology.machines.magic_infuser.TileMagicInfuser;
import addsynth.overpoweredtechnology.machines.matter_compressor.TileMatterCompressor;
import addsynth.overpoweredtechnology.machines.plasma_generator.TilePlasmaGenerator;
import addsynth.overpoweredtechnology.machines.portal.control_panel.TilePortalControlPanel;
import addsynth.overpoweredtechnology.machines.portal.frame.TilePortalFrame;
import addsynth.overpoweredtechnology.machines.portal.rift.TilePortal;
import addsynth.overpoweredtechnology.machines.suspension_bridge.TileSuspensionBridge;

public final class Tiles {

  public static final BlockEntityHolder<TileEnergyExtractor> ENERGY_EXTRACTOR =
    new BlockEntityHolder<>(Names.ENERGY_EXTRACTOR, TileEnergyExtractor::new, OverpoweredBlocks.energy_extractor);

  public static final BlockEntityHolder<TileGemConverter> GEM_CONVERTER =
    new BlockEntityHolder<>(Names.GEM_CONVERTER, TileGemConverter::new, OverpoweredBlocks.gem_converter);

  public static final BlockEntityHolder<TileInverter> INVERTER =
    new BlockEntityHolder<>(Names.INVERTER, TileInverter::new, OverpoweredBlocks.inverter);

  public static final BlockEntityHolder<TileMagicInfuser> MAGIC_INFUSER =
    new BlockEntityHolder<>(Names.MAGIC_INFUSER, TileMagicInfuser::new, OverpoweredBlocks.magic_infuser);

  public static final BlockEntityHolder<TileIdentifier> IDENTIFIER =
    new BlockEntityHolder<>(Names.IDENTIFIER, TileIdentifier::new, OverpoweredBlocks.identifier);

  public static final BlockEntityHolder<TileAdvancedOreRefinery> ADVANCED_ORE_REFINERY =
    new BlockEntityHolder<>(Names.ADVANCED_ORE_REFINERY, TileAdvancedOreRefinery::new, OverpoweredBlocks.advanced_ore_refinery);

  public static final BlockEntityHolder<TileCrystalMatterGenerator> CRYSTAL_MATTER_REPLICATOR =
    new BlockEntityHolder<>(Names.CRYSTAL_MATTER_GENERATOR, TileCrystalMatterGenerator::new, OverpoweredBlocks.crystal_matter_generator);

  public static final BlockEntityHolder<TileSuspensionBridge> ENERGY_SUSPENSION_BRIDGE =
    new BlockEntityHolder<>(Names.ENERGY_SUSPENSION_BRIDGE, TileSuspensionBridge::new, OverpoweredBlocks.energy_suspension_bridge);

  public static final BlockEntityHolder<TileDataCable> DATA_CABLE =
    new BlockEntityHolder<>(Names.DATA_CABLE, TileDataCable::new, OverpoweredBlocks.data_cable);

  public static final BlockEntityHolder<TilePortalControlPanel> PORTAL_CONTROL_PANEL =
    new BlockEntityHolder<>(Names.PORTAL_CONTROL_PANEL, TilePortalControlPanel::new, OverpoweredBlocks.portal_control_panel);

  public static final BlockEntityHolder<TilePortalFrame> PORTAL_FRAME =
    new BlockEntityHolder<>(Names.PORTAL_FRAME, TilePortalFrame::new, OverpoweredBlocks.portal_frame);

  public static final BlockEntityHolder<TilePortal> PORTAL_RIFT =
    new BlockEntityHolder<>(Names.PORTAL_RIFT, TilePortal::new, OverpoweredBlocks.portal);

  public static final BlockEntityHolder<TileLaserHousing> LASER_MACHINE =
    new BlockEntityHolder<>(Names.LASER_HOUSING, TileLaserHousing::new, OverpoweredBlocks.laser_housing);

  public static final BlockEntityHolder<TileFusionEnergyConverter> FUSION_ENERGY_CONVERTER =
    new BlockEntityHolder<>(Names.FUSION_CONVERTER, TileFusionEnergyConverter::new, OverpoweredBlocks.fusion_converter);

  public static final BlockEntityHolder<TileFusionChamber> FUSION_CHAMBER =
    new BlockEntityHolder<>(Names.FUSION_CHAMBER, TileFusionChamber::new, OverpoweredBlocks.fusion_chamber);

  public static final BlockEntityHolder<TileBlackHole> BLACK_HOLE =
    new BlockEntityHolder<>(Names.BLACK_HOLE, TileBlackHole::new, OverpoweredBlocks.black_hole);

  public static final BlockEntityHolder<TilePlasmaGenerator> PLASMA_GENERATOR =
    new BlockEntityHolder<>(Names.PLASMA_GENERATOR, TilePlasmaGenerator::new, OverpoweredBlocks.plasma_generator);

  public static final BlockEntityHolder<TileMatterCompressor> MATTER_COMPRESSOR =
    new BlockEntityHolder<>(Names.MATTER_COMPRESSOR, TileMatterCompressor::new, OverpoweredBlocks.matter_compressor);

}
