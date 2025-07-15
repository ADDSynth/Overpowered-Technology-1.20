package addsynth.overpoweredtechnology.registers;

import addsynth.overpoweredtechnology.game.reference.Names;
import addsynth.overpoweredtechnology.machines.advanced_ore_refinery.ContainerOreRefinery;
import addsynth.overpoweredtechnology.machines.crystal_matter_generator.ContainerCrystalGenerator;
import addsynth.overpoweredtechnology.machines.energy_extractor.ContainerEnergyExtractor;
import addsynth.overpoweredtechnology.machines.fusion.chamber.ContainerFusionChamber;
import addsynth.overpoweredtechnology.machines.gem_converter.ContainerGemConverter;
import addsynth.overpoweredtechnology.machines.identifier.ContainerIdentifier;
import addsynth.overpoweredtechnology.machines.inverter.ContainerInverter;
import addsynth.overpoweredtechnology.machines.laser.machine.ContainerLaserHousing;
import addsynth.overpoweredtechnology.machines.magic_infuser.ContainerMagicInfuser;
import addsynth.overpoweredtechnology.machines.matter_compressor.MatterCompressorContainer;
import addsynth.overpoweredtechnology.machines.plasma_generator.ContainerPlasmaGenerator;
import addsynth.overpoweredtechnology.machines.portal.control_panel.ContainerPortalControlPanel;
import addsynth.overpoweredtechnology.machines.portal.frame.ContainerPortalFrame;
import addsynth.overpoweredtechnology.machines.suspension_bridge.ContainerSuspensionBridge;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class Containers {

  public static final RegistryObject<MenuType<ContainerEnergyExtractor>> ENERGY_EXTRACTOR  =
    RegistryObject.create(Names.ENERGY_EXTRACTOR, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<ContainerInverter>> INVERTER =
    RegistryObject.create(Names.INVERTER, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<ContainerGemConverter>> GEM_CONVERTER =
    RegistryObject.create(Names.GEM_CONVERTER, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<ContainerMagicInfuser>> MAGIC_INFUSER =
    RegistryObject.create(Names.MAGIC_INFUSER, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<ContainerIdentifier>> IDENTIFIER =
    RegistryObject.create(Names.IDENTIFIER, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<ContainerSuspensionBridge>> ENERGY_SUSPENSION_BRIDGE =
    RegistryObject.create(Names.ENERGY_SUSPENSION_BRIDGE, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<ContainerOreRefinery>> ADVANCED_ORE_REFINERY =
    RegistryObject.create(Names.ADVANCED_ORE_REFINERY, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<ContainerCrystalGenerator>> CRYSTAL_MATTER_GENERATOR =
    RegistryObject.create(Names.CRYSTAL_MATTER_GENERATOR, ForgeRegistries.MENU_TYPES);
  
  public static final RegistryObject<MenuType<ContainerPortalControlPanel>> PORTAL_CONTROL_PANEL =
    RegistryObject.create(Names.PORTAL_CONTROL_PANEL, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<ContainerPortalFrame>> PORTAL_FRAME =
    RegistryObject.create(Names.PORTAL_FRAME, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<ContainerLaserHousing>> LASER_HOUSING =
    RegistryObject.create(Names.LASER_HOUSING, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<ContainerFusionChamber>> FUSION_CHAMBER =
    RegistryObject.create(Names.FUSION_CHAMBER, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<ContainerPlasmaGenerator>> PLASMA_GENERATOR =
    RegistryObject.create(Names.PLASMA_GENERATOR, ForgeRegistries.MENU_TYPES);

  public static final RegistryObject<MenuType<MatterCompressorContainer>> MATTER_COMPRESSOR =
    RegistryObject.create(Names.MATTER_COMPRESSOR, ForgeRegistries.MENU_TYPES);

}
