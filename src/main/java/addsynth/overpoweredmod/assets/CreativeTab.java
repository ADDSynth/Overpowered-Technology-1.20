package addsynth.overpoweredmod.assets;

import addsynth.core.compat.Compatibility;
import addsynth.core.game.item.constants.ArmorMaterial;
import addsynth.core.game.item.constants.EquipmentType;
import addsynth.overpoweredmod.OverpoweredTechnology;
import addsynth.overpoweredmod.game.core.Laser;
import addsynth.overpoweredmod.game.reference.OverpoweredBlocks;
import addsynth.overpoweredmod.game.reference.OverpoweredItems;
import addsynth.overpoweredmod.items.UnidentifiedItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class CreativeTab {

  public static final void register(){
    final ResourceLocation location = ResourceLocation.fromNamespaceAndPath(OverpoweredTechnology.MOD_ID, "creative_tab");
    final ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, location);
    final CreativeModeTab creative_tab = CreativeModeTab.builder()
      .title(Component.literal(OverpoweredTechnology.MOD_NAME))
      .icon(() -> new ItemStack(OverpoweredItems.celestial_gem.get(), 1))
      .displayItems((displayParameters, output) -> {
        output.accept(OverpoweredItems.celestial_gem.get());
        output.accept(OverpoweredItems.energy_crystal_shards.get());
        output.accept(OverpoweredItems.energy_crystal.get());
        output.accept(OverpoweredItems.light_block.get());
        output.accept(OverpoweredItems.void_crystal.get());
        output.accept(OverpoweredBlocks.null_block.get());
        output.accept(OverpoweredItems.energized_power_core.get());
        output.accept(OverpoweredItems.nullified_power_core.get());
        output.accept(OverpoweredItems.energy_grid.get());
        output.accept(OverpoweredItems.vacuum_container.get());
        output.accept(OverpoweredItems.reinforced_container.get());
        output.accept(OverpoweredItems.beam_emitter.get());
        output.accept(OverpoweredItems.scanning_laser.get());
        output.accept(OverpoweredItems.destructive_laser.get());
        output.accept(OverpoweredItems.heavy_light_emitter.get());
        output.accept(OverpoweredItems.energy_stabilizer.get());
        output.accept(OverpoweredItems.matter_energy_transformer.get());
        output.accept(OverpoweredItems.high_frequency_beam.get());
        output.accept(OverpoweredItems.focus_lens.get());
        output.accept(OverpoweredItems.red_lens.get());
        output.accept(OverpoweredItems.orange_lens.get());
        output.accept(OverpoweredItems.yellow_lens.get());
        output.accept(OverpoweredItems.green_lens.get());
        output.accept(OverpoweredItems.cyan_lens.get());
        output.accept(OverpoweredItems.blue_lens.get());
        output.accept(OverpoweredItems.magenta_lens.get());
        output.accept(OverpoweredItems.plasma.get());
        output.accept(OverpoweredItems.fusion_core.get());
        output.accept(OverpoweredItems.matter_energy_core.get());
        output.accept(OverpoweredItems.dimensional_flux.get());
        output.accept(OverpoweredItems.dimensional_anchor.get());
        output.accept(OverpoweredItems.unimatter.get());
        output.accept(OverpoweredBlocks.data_cable.get());
        output.accept(OverpoweredBlocks.energy_extractor.get());
        output.accept(OverpoweredBlocks.gem_converter.get());
        output.accept(OverpoweredBlocks.identifier.get());
        output.accept(OverpoweredBlocks.inverter.get());
        output.accept(OverpoweredBlocks.magic_infuser.get());
        output.accept(OverpoweredBlocks.energy_suspension_bridge.get());
        output.accept(OverpoweredBlocks.portal_control_panel.get());
        output.accept(OverpoweredBlocks.portal_frame.get());
        output.accept(OverpoweredBlocks.plasma_generator.get());
        output.accept(OverpoweredBlocks.advanced_ore_refinery.get());
        output.accept(OverpoweredBlocks.crystal_matter_generator.get());
        output.accept(OverpoweredBlocks.laser_housing.get());
        output.accept(Laser.WHITE.cannon.get());
        output.accept(Laser.RED.cannon.get());
        output.accept(Laser.ORANGE.cannon.get());
        output.accept(Laser.YELLOW.cannon.get());
        output.accept(Laser.GREEN.cannon.get());
        output.accept(Laser.CYAN.cannon.get());
        output.accept(Laser.BLUE.cannon.get());
        output.accept(Laser.MAGENTA.cannon.get());
        output.accept(OverpoweredBlocks.fusion_converter.get());
        output.accept(OverpoweredBlocks.fusion_chamber.get());
        output.accept(OverpoweredBlocks.fusion_control_unit.get());
        output.accept(OverpoweredBlocks.fusion_control_laser.get());
        output.accept(OverpoweredBlocks.matter_compressor.get());
        output.accept(OverpoweredBlocks.iron_frame_block.get());
        output.accept(OverpoweredItems.black_hole.get());
        output.accept(OverpoweredItems.celestial_sword.get());
        output.accept(OverpoweredItems.celestial_shovel.get());
        output.accept(OverpoweredItems.celestial_pickaxe.get());
        output.accept(OverpoweredItems.celestial_axe.get());
        output.accept(OverpoweredItems.celestial_hoe.get());
        output.accept(OverpoweredItems.void_sword.get());
        output.accept(OverpoweredItems.void_shovel.get());
        output.accept(OverpoweredItems.void_pickaxe.get());
        output.accept(OverpoweredItems.void_axe.get());
        output.accept(OverpoweredItems.void_hoe.get());
        for(ArmorMaterial material : ArmorMaterial.values()){
          if(material != ArmorMaterial.NETHERITE){
            for(EquipmentType equipment : EquipmentType.values()){
              output.accept(UnidentifiedItem.get(material, equipment));
            }
          }
        }
        if(Compatibility.CURIOS.isLoaded()){
          output.accept(OverpoweredItems.ring_0.get());
          output.accept(OverpoweredItems.ring_1.get());
          output.accept(OverpoweredItems.ring_2.get());
          output.accept(OverpoweredItems.ring_3.get());
        }
      }).build();
    Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, creative_tab);
  }

}
