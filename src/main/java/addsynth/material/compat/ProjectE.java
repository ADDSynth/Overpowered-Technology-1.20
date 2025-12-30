package addsynth.material.compat;

import addsynth.core.compat.Compatibility;
import addsynth.core.compat.EMCValue;
import addsynth.material.ADDSynthMaterials;
import addsynth.material.Material;
import net.minecraftforge.fml.InterModComms;
import moze_intel.projecte.api.imc.CustomEMCRegistration;
import moze_intel.projecte.api.imc.IMCMethods;
import moze_intel.projecte.api.nss.NSSItem;

public final class ProjectE {

  public static final void register_emc_values(){

    final String sender = ADDSynthMaterials.MOD_ID;
    final String mod = Compatibility.PROJECT_E.modid;
    final String message = IMCMethods.REGISTER_CUSTOM_EMC;
    
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.RUBY.gem.get()),     EMCValue.gemstone)); // ProjectE defaults to 2024 emc
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.TOPAZ.gem.get()),    EMCValue.gemstone));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.CITRINE.gem.get()),  EMCValue.gemstone));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.SAPPHIRE.gem.get()), EMCValue.gemstone)); // ProjectE defaults to 2024 emc
    
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.RUBY.block.get()),     EMCValue.gem_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.TOPAZ.block.get()),    EMCValue.gem_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.CITRINE.block.get()),  EMCValue.gem_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.SAPPHIRE.block.get()), EMCValue.gem_block));

    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.TIN.ingot.get()),       EMCValue.common_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.ALUMINUM.ingot.get()),  EMCValue.common_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.LEAD.ingot.get()),      EMCValue.common_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.NICKEL.ingot.get()),    EMCValue.common_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.ZINC.ingot.get()),      EMCValue.common_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.SILVER.ingot.get()),    EMCValue.uncommon_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.COBALT.ingot.get()),    EMCValue.uncommon_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.PLATINUM.ingot.get()),  EMCValue.rare_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.TITANIUM.ingot.get()),  EMCValue.rare_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.NEODYMIUM.ingot.get()), EMCValue.rare_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.STEEL.ingot.get()),     EMCValue.strong_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.BRONZE.ingot.get()),    EMCValue.strong_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.BRASS.ingot.get()),     EMCValue.strong_metal));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.INVAR.ingot.get()),     EMCValue.strong_metal));

    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.TIN.block.get()),       EMCValue.common_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.ALUMINUM.block.get()),  EMCValue.common_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.LEAD.block.get()),      EMCValue.common_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.NICKEL.block.get()),    EMCValue.common_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.ZINC.block.get()),      EMCValue.common_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.SILVER.block.get()),    EMCValue.uncommon_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.COBALT.block.get()),    EMCValue.uncommon_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.PLATINUM.block.get()),  EMCValue.rare_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.TITANIUM.block.get()),  EMCValue.rare_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.NEODYMIUM.block.get()), EMCValue.rare_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.STEEL.block.get()),     EMCValue.strong_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.BRONZE.block.get()),    EMCValue.strong_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.BRASS.block.get()),     EMCValue.strong_metal_block));
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.INVAR.block.get()),     EMCValue.strong_metal_block));
    
    if(Compatibility.ADDSYNTH_ENERGY.isLoaded()){
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.IRON.plate.get()),     EMCValue.common_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.GOLD.plate.get()),     EMCValue.uncommon_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.COPPER.plate.get()),   EMCValue.common_metal));

      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.TIN.plate.get()),       EMCValue.common_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.ALUMINUM.plate.get()),  EMCValue.common_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.LEAD.plate.get()),      EMCValue.common_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.NICKEL.plate.get()),    EMCValue.common_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.ZINC.plate.get()),      EMCValue.common_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.SILVER.plate.get()),    EMCValue.uncommon_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.COBALT.plate.get()),     EMCValue.uncommon_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.PLATINUM.plate.get()),  EMCValue.rare_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.TITANIUM.plate.get()),  EMCValue.rare_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.NEODYMIUM.plate.get()), EMCValue.rare_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.STEEL.plate.get()),     EMCValue.strong_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.BRONZE.plate.get()),    EMCValue.strong_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.BRASS.plate.get()),     EMCValue.strong_metal));
      InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.INVAR.plate.get()),     EMCValue.strong_metal));
    }
    
    InterModComms.sendTo(sender, mod, message, () -> new CustomEMCRegistration(NSSItem.createItem(Material.SILICON.item.get()), EMCValue.silicon));
  }

}
