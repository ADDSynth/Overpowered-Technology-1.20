package addsynth.overpoweredtechnology;

import java.io.File;
// import addsynth.core.compat.Compatibility;
// import addsynth.core.compat.EMCValue;
import addsynth.core.util.CommonUtil;
import addsynth.core.util.constants.DevStage;
import addsynth.core.util.game.Game;
import addsynth.overpoweredtechnology.assets.CreativeTab;
import addsynth.overpoweredtechnology.assets.CustomStats;
import addsynth.overpoweredtechnology.compatability.CompatabilityManager;
import addsynth.overpoweredtechnology.config.*;
import addsynth.overpoweredtechnology.game.NetworkHandler;
import addsynth.overpoweredtechnology.game.OverpoweredSavedData;
import addsynth.overpoweredtechnology.machines.advanced_ore_refinery.GuiAdvancedOreRefinery;
import addsynth.overpoweredtechnology.machines.crystal_matter_generator.GuiCrystalMatterGenerator;
import addsynth.overpoweredtechnology.machines.energy_extractor.GuiEnergyExtractor;
import addsynth.overpoweredtechnology.machines.fusion.chamber.GuiFusionChamber;
import addsynth.overpoweredtechnology.machines.gem_converter.GuiGemConverter;
import addsynth.overpoweredtechnology.machines.identifier.GuiIdentifier;
import addsynth.overpoweredtechnology.machines.inverter.GuiInverter;
import addsynth.overpoweredtechnology.machines.laser.machine.GuiLaserHousing;
import addsynth.overpoweredtechnology.machines.magic_infuser.GuiMagicInfuser;
import addsynth.overpoweredtechnology.machines.magic_infuser.recipes.MagicInfuserRecipes;
import addsynth.overpoweredtechnology.machines.matter_compressor.GuiMatterCompressor;
import addsynth.overpoweredtechnology.machines.plasma_generator.GuiPlasmaGenerator;
import addsynth.overpoweredtechnology.machines.portal.control_panel.GuiPortalControlPanel;
import addsynth.overpoweredtechnology.machines.portal.frame.GuiPortalFrame;
import addsynth.overpoweredtechnology.machines.suspension_bridge.GuiEnergySuspensionBridge;
import addsynth.overpoweredtechnology.registers.Containers;
import addsynth.overpoweredtechnology.registers.Registers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Mod(value = OverpoweredTechnology.MOD_ID)
public class OverpoweredTechnology {

  public static final String MOD_ID = "overpowered_technology";
  public static final String MOD_NAME = "Overpowered Technology";
  public static final String VERSION = "6";
  public static final String VERSION_DATE = "April 5, 2025";
  public static final DevStage DEV_STAGE = DevStage.DEVELOPMENT;
    
  public static final Logger log = LogManager.getLogger(MOD_NAME);

  public static final ResourceLocation getLocation(final String path){
    return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
  }

  public OverpoweredTechnology(final FMLJavaModLoadingContext context){
    OverpoweredTechnology.log.info("Begin constructing "+OverpoweredTechnology.class.getSimpleName()+" class object...");
    final IEventBus bus = context.getModEventBus();
    bus.addListener(OverpoweredTechnology::main_setup);
    bus.addListener(OverpoweredTechnology::client_setup);
    bus.addListener(CompatabilityManager::inter_mod_communications);
    MinecraftForge.EVENT_BUS.addListener(OverpoweredTechnology::serverStarted);
    MinecraftForge.EVENT_BUS.addListener(Registers::onMissingEntries);
    init_config(context);
    OverpoweredTechnology.log.info("Done constructing "+OverpoweredTechnology.class.getSimpleName()+" class object.");
  }

  private static final void init_config(final ModLoadingContext context){
    new File(FMLPaths.CONFIGDIR.get().toString(), MOD_NAME).mkdir();
    Game.registerConfig(context,                  Config::new, MOD_NAME, "main.toml");
    Game.registerConfig(context,           MachineValues::new, MOD_NAME, "machine_values.toml");
    Game.registerConfig(context, UnidentifiedItemsConfig::new, MOD_NAME, "unidentified_items.toml");
    Game.registerConfig(context,                  Values::new, MOD_NAME, "values.toml");
  }
  
  private static final void main_setup(final FMLCommonSetupEvent event){
    log.info("Begin "+MOD_NAME+" main setup...");
    
    CommonUtil.displayModInfo(log, MOD_NAME, "ADDSynth", VERSION, DEV_STAGE, VERSION_DATE);
    
    NetworkHandler.registerMessages();
    // WeirdDimension.register();
    MagicInfuserRecipes.INSTANCE.register();
    
    // Register Stats
    // Can't add Overpowered Technology Name to stats because then the text overlaps the stat values.
    Game.registerCustomStat(CustomStats.GEMS_CONVERTED);
    Game.registerCustomStat(CustomStats.LASERS_FIRED);
    Game.registerCustomStat(CustomStats.ITEMS_IDENTIFIED);
    // Game.registerCustomStat(BLACK_HOLE_EVENTS);
    
    log.info("Finished "+MOD_NAME+" main setup.");
  }

  private static final void serverStarted(final ServerStartedEvent event){
    @SuppressWarnings("resource")
    final MinecraftServer server = event.getServer();
    
    // load world saved data
    OverpoweredSavedData.load(server);

    /*
    // check items missing EMC
    if(Compatibility.PROJECT_E.isLoaded()){
      if(DEV_STAGE.isDevelopment){
        EMCValue.check_items(MOD_ID);
      }
    }
    */
  }

  private static final void client_setup(final FMLClientSetupEvent event){
    register_guis();
    CreativeTab.register();
  }

  private static final void register_guis(){
    MenuScreens.register(Containers.ENERGY_EXTRACTOR.get(),           GuiEnergyExtractor::new);
    MenuScreens.register(Containers.GEM_CONVERTER.get(),              GuiGemConverter::new);
    MenuScreens.register(Containers.INVERTER.get(),                   GuiInverter::new);
    MenuScreens.register(Containers.MAGIC_INFUSER.get(),              GuiMagicInfuser::new);
    MenuScreens.register(Containers.IDENTIFIER.get(),                 GuiIdentifier::new);
    MenuScreens.register(Containers.ENERGY_SUSPENSION_BRIDGE.get(),   GuiEnergySuspensionBridge::new);
    MenuScreens.register(Containers.PORTAL_CONTROL_PANEL.get(),       GuiPortalControlPanel::new);
    MenuScreens.register(Containers.PORTAL_FRAME.get(),               GuiPortalFrame::new);
    MenuScreens.register(Containers.LASER_HOUSING.get(),              GuiLaserHousing::new);
    MenuScreens.register(Containers.PLASMA_GENERATOR.get(),           GuiPlasmaGenerator::new);
    MenuScreens.register(Containers.ADVANCED_ORE_REFINERY.get(),      GuiAdvancedOreRefinery::new);
    MenuScreens.register(Containers.CRYSTAL_MATTER_GENERATOR.get(),   GuiCrystalMatterGenerator::new);
    MenuScreens.register(Containers.FUSION_CHAMBER.get(),             GuiFusionChamber::new);
    MenuScreens.register(Containers.MATTER_COMPRESSOR.get(),          GuiMatterCompressor::new);
  }

}
