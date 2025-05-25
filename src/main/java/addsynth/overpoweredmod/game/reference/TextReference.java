package addsynth.overpoweredmod.game.reference;

import net.minecraft.network.chat.Component;

/** The TextReference class should only be used for common words
 *  or commonly used text that can be used in multiple projects.
 *  Text that is only used in one place, should be defined there.
 *  
 *  Also, keep in mind that just because a word has multiple
 *  meanings in my language, does not mean it has the same meanings
 *  in other languages. */
public final class TextReference {

  // Tooltip Subtitles:
  public static final Component  laser_machine = Component.translatable("gui.overpowered.tooltip.laser_machine");
  public static final Component fusion_machine = Component.translatable("gui.overpowered.tooltip.fusion_machine");

  // Blocks
  public static final Component advanced_ore_refinery = OverpoweredBlocks.advanced_ore_refinery.get().getName();
  public static final Component gem_converter         = OverpoweredBlocks.gem_converter.get().getName();
  public static final Component inverter              = OverpoweredBlocks.inverter.get().getName();
  public static final Component magic_infuser         = OverpoweredBlocks.magic_infuser.get().getName();

  // JEI Descriptions
  public static final Component celestial_gem_description         = Component.translatable("gui.overpowered.jei_description.celestial_gem");
  public static final Component energy_crystal_shards_description = Component.translatable("gui.overpowered.jei_description.energy_crystal_shards");
  public static final Component energy_crystal_description        = Component.translatable("gui.overpowered.jei_description.energy_crystal");
  public static final Component light_block_description           = Component.translatable("gui.overpowered.jei_description.light_block");
  public static final Component void_crystal_description          = Component.translatable("gui.overpowered.jei_description.void_crystal");
  public static final Component null_block_description            = Component.translatable("gui.overpowered.jei_description.null_block");

  public static final Component celestial_tools_description = Component.translatable("gui.overpowered.jei_description.celestial_tools");
  public static final Component void_tools_description      = Component.translatable("gui.overpowered.jei_description.void_tools");

  public static final Component scanning_laser_description            = Component.translatable("gui.overpowered.jei_description.scanning_laser");
  public static final Component destructive_laser_description         = Component.translatable("gui.overpowered.jei_description.destructive_laser");
  public static final Component energy_stabilizer_description         = Component.translatable("gui.overpowered.jei_description.energy_stabilizer");
  public static final Component heavy_light_emitter_description       = Component.translatable("gui.overpowered.jei_description.heavy_light_emitter");
  public static final Component matter_energy_transformer_description = Component.translatable("gui.overpowered.jei_description.matter_energy_transformer");

  public static final Component plasma_description                  = Component.translatable("gui.overpowered.jei_description.plasma");
  public static final Component matter_energy_core_description      = Component.translatable("gui.overpowered.jei_description.matter_energy_core");
  public static final Component vacuum_container_description        = Component.translatable("gui.overpowered.jei_description.vacuum_container");
  public static final Component reinforced_container_description    = Component.translatable("gui.overpowered.jei_description.reinforced_container");
  public static final Component dimensional_flux_description        = Component.translatable("gui.overpowered.jei_description.dimensional_flux");
  public static final Component unimatter_description               = Component.translatable("gui.overpowered.jei_description.unimatter");
  public static final Component dimensional_anchor_description      = Component.translatable("gui.overpowered.jei_description.dimensional_anchor");

  public static final Component laser_housing_description = Component.translatable("gui.overpowered.jei_description.laser_housing");
  public static final Component laser_description         = Component.translatable("gui.overpowered.jei_description.laser");

  public static final Component energy_extractor_description         = Component.translatable("gui.overpowered.jei_description.energy_extractor");
  public static final Component data_cable_description               = Component.translatable("gui.overpowered.jei_description.data_cable");
  public static final Component gem_converter_description            = Component.translatable("gui.overpowered.jei_description.gem_converter");
  public static final Component inverter_description                 = Component.translatable("gui.overpowered.jei_description.inverter");
  public static final Component magic_infuser_description            = Component.translatable("gui.overpowered.jei_description.magic_infuser");
  public static final Component identifier_description               = Component.translatable("gui.overpowered.jei_description.identifier");
  public static final Component portal_control_panel_description     = Component.translatable("gui.overpowered.jei_description.portal_control_panel");
  public static final Component portal_frame_description             = Component.translatable("gui.overpowered.jei_description.portal_frame");

  public static final Component energy_suspension_bridge_description = Component.translatable("gui.overpowered.jei_description.energy_suspension_bridge");
  public static final Component advanced_ore_refinery_description    = Component.translatable("gui.overpowered.jei_description.advanced_ore_refinery");
  public static final Component plasma_generator_description         = Component.translatable("gui.overpowered.jei_description.plasma_generator");
  public static final Component matter_compressor_description        = Component.translatable("gui.overpowered.jei_description.matter_compressor");
  public static final Component crystal_matter_generator_description = Component.translatable("gui.overpowered.jei_description.crystal_matter_generator");
  public static final Component black_hole_description               = Component.translatable("gui.overpowered.jei_description.black_hole");
  
  public static final Component fusion_chamber_description       = Component.translatable("gui.overpowered.jei_description.fusion_chamber");
  public static final Component fusion_control_unit_description  = Component.translatable("gui.overpowered.jei_description.fusion_control_unit");
  public static final Component fusion_control_laser_description = Component.translatable("gui.overpowered.jei_description.fusion_control_laser");
  public static final Component fusion_converter_description     = Component.translatable("gui.overpowered.jei_description.fusion_converter");

}
