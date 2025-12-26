package addsynth.material;

import addsynth.material.compat.MaterialsCompat;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class CreativeTab {

  private static final ResourceLocation location = ADDSynthMaterials.getLocation("creative_tab");
  public  static final ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, location);

  public static final void register(final Registry<CreativeModeTab> registry){
    final CreativeModeTab.Builder creative_tab_builder = CreativeModeTab.builder()
      .title(Component.literal(ADDSynthMaterials.MOD_NAME))
      .icon(() -> new ItemStack(Material.SAPPHIRE.gem.get()))
      .displayItems((displayParameters, output) -> {
        // ingots
        output.accept(Material.IRON.ingot);
        output.accept(Material.COPPER.ingot);
        output.accept(Material.GOLD.ingot);
        output.accept(Material.TIN.ingot.get());
        output.accept(Material.ALUMINUM.ingot.get());
        output.accept(Material.LEAD.ingot.get());
        output.accept(Material.NICKEL.ingot.get());
        output.accept(Material.ZINC.ingot.get());
        output.accept(Material.SILVER.ingot.get());
        output.accept(Material.COBALT.ingot.get());
        output.accept(Material.PLATINUM.ingot.get());
        output.accept(Material.TITANIUM.ingot.get());
        output.accept(Material.NEODYMIUM.ingot.get());
        output.accept(Material.STEEL.ingot.get());
        output.accept(Material.BRONZE.ingot.get());
        output.accept(Material.BRASS.ingot.get());
        output.accept(Material.INVAR.ingot.get());
        // metal blocks
        output.accept(Material.IRON.block);
        output.accept(Material.COPPER.block);
        output.accept(Material.GOLD.block);
        output.accept(Material.TIN.block.get());
        output.accept(Material.ALUMINUM.block.get());
        output.accept(Material.LEAD.block.get());
        output.accept(Material.NICKEL.block.get());
        output.accept(Material.ZINC.block.get());
        output.accept(Material.SILVER.block.get());
        output.accept(Material.COBALT.block.get());
        output.accept(Material.PLATINUM.block.get());
        output.accept(Material.TITANIUM.block.get());
        output.accept(Material.NEODYMIUM.block.get());
        output.accept(Material.STEEL.block.get());
        output.accept(Material.BRONZE.block.get());
        output.accept(Material.BRASS.block.get());
        output.accept(Material.INVAR.block.get());
        // metal ores
        output.accept(Material.TIN.ore.get());
        output.accept(Material.ALUMINUM.ore.get());
        output.accept(Material.LEAD.ore.get());
        output.accept(Material.NICKEL.ore.get());
        output.accept(Material.ZINC.ore.get());
        output.accept(Material.SILVER.ore.get());
        output.accept(Material.COBALT.ore.get());
        output.accept(Material.PLATINUM.ore.get());
        output.accept(Material.TITANIUM.ore.get());
        output.accept(Material.NEODYMIUM.ore.get());
        // deepslate metal ores
        output.accept(Material.TIN.deepslate_ore.get());
        output.accept(Material.ALUMINUM.deepslate_ore.get());
        output.accept(Material.LEAD.deepslate_ore.get());
        output.accept(Material.NICKEL.deepslate_ore.get());
        output.accept(Material.ZINC.deepslate_ore.get());
        output.accept(Material.SILVER.deepslate_ore.get());
        output.accept(Material.COBALT.deepslate_ore.get());
        output.accept(Material.PLATINUM.deepslate_ore.get());
        output.accept(Material.TITANIUM.deepslate_ore.get());
        output.accept(Material.NEODYMIUM.deepslate_ore.get());
        // metal plates
        if(MaterialsCompat.addsynth_energy.isLoaded()){
          output.accept(Material.IRON.plate.get());
          output.accept(Material.COPPER.plate.get());
          output.accept(Material.GOLD.plate.get());
          output.accept(Material.TIN.plate.get());
          output.accept(Material.ALUMINUM.plate.get());
          output.accept(Material.LEAD.plate.get());
          output.accept(Material.NICKEL.plate.get());
          output.accept(Material.ZINC.plate.get());
          output.accept(Material.SILVER.plate.get());
          output.accept(Material.COBALT.plate.get());
          output.accept(Material.PLATINUM.plate.get());
          output.accept(Material.TITANIUM.plate.get());
          output.accept(Material.NEODYMIUM.plate.get());
          output.accept(Material.STEEL.plate.get());
          output.accept(Material.BRONZE.plate.get());
          output.accept(Material.BRASS.plate.get());
          output.accept(Material.INVAR.plate.get());
        }
        // gems
        output.accept(Material.RUBY.gem.get());
        output.accept(Material.TOPAZ.gem.get());
        output.accept(Material.CITRINE.gem.get());
        output.accept(Material.EMERALD.gem);
        output.accept(Material.DIAMOND.gem);
        output.accept(Material.SAPPHIRE.gem.get());
        output.accept(Material.AMETHYST.gem);
        output.accept(Material.QUARTZ.gem);
        // gem blocks
        output.accept(Material.RUBY.block.get());
        output.accept(Material.TOPAZ.block.get());
        output.accept(Material.CITRINE.block.get());
        output.accept(Material.EMERALD.block);
        output.accept(Material.DIAMOND.block);
        output.accept(Material.SAPPHIRE.block.get());
        output.accept(Material.AMETHYST.block);
        output.accept(Material.QUARTZ.block);
        // gem ores
        output.accept(Material.RUBY.ore.get());
        output.accept(Material.TOPAZ.ore.get());
        output.accept(Material.CITRINE.ore.get());
        output.accept(Material.SAPPHIRE.ore.get());
        output.accept(Material.AMETHYST.ore.get());
        // gem deepslate ore
        output.accept(Material.RUBY.deepslate_ore.get());
        output.accept(Material.TOPAZ.deepslate_ore.get());
        output.accept(Material.CITRINE.deepslate_ore.get());
        output.accept(Material.SAPPHIRE.deepslate_ore.get());
        output.accept(Material.AMETHYST.deepslate_ore.get());
        // other materials
        output.accept(Material.SILICON.item.get());
        output.accept(Material.SILICON.ore.get());
        output.accept(Material.SILICON.deepslate_ore.get());
        output.accept(Material.ROSE_QUARTZ.item.get());
        output.accept(Material.ROSE_QUARTZ.ore.get());
        output.accept(Material.ROSE_QUARTZ.deepslate_ore.get());
      });
    if(MaterialsCompat.addsynthcore.isLoaded()){
      creative_tab_builder.withTabsBefore(addsynth.core.gameplay.CreativeTab.key);
    }
    Registry.register(registry, key, creative_tab_builder.build());
  }

}
