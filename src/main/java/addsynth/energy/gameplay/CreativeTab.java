package addsynth.energy.gameplay;

import addsynth.energy.ADDSynthEnergy;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class CreativeTab {

  public static final void register(){
    final ResourceLocation id = ResourceLocation.fromNamespaceAndPath(ADDSynthEnergy.MOD_ID, "creative_tab");
    final ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id);
    final CreativeModeTab creative_tab = CreativeModeTab.builder()
      .title(Component.literal(ADDSynthEnergy.MOD_NAME))
      .icon(() -> new ItemStack(Item.BY_BLOCK.get(EnergyBlocks.wire.get())))
      .displayItems((displayParameters, output) -> {
        output.accept(EnergyBlocks.wire.get());
        output.accept(EnergyBlocks.generator.get());
        output.accept(EnergyBlocks.energy_storage.get());
        output.accept(EnergyBlocks.compressor.get());
        output.accept(EnergyBlocks.electric_furnace.get());
        output.accept(EnergyBlocks.circuit_fabricator.get());
        output.accept(EnergyBlocks.universal_energy_machine.get());
        output.accept(EnergyBlocks.energy_diagnostics_block.get());
        output.accept(EnergyItems.power_core.get());
        output.accept(EnergyItems.advanced_power_core.get());
        output.accept(EnergyItems.power_regulator.get());
        output.accept(EnergyItems.circuit_tier_1.get());
        output.accept(EnergyItems.circuit_tier_2.get());
        output.accept(EnergyItems.circuit_tier_3.get());
        output.accept(EnergyItems.circuit_tier_4.get());
        output.accept(EnergyItems.circuit_tier_5.get());
        output.accept(EnergyItems.circuit_tier_6.get());
        output.accept(EnergyItems.circuit_tier_7.get());
        output.accept(EnergyItems.circuit_tier_8.get());
        output.accept(EnergyItems.circuit_tier_9.get());
      }).build();
    Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, creative_tab);
  }

}
