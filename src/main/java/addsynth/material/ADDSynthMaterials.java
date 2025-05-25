package addsynth.material;

import java.util.stream.Stream;
import addsynth.core.ADDSynthCore;
// import addsynth.core.compat.Compatibility;
// import addsynth.core.compat.EMCValue;
import addsynth.core.util.CommonUtil;
import addsynth.core.util.constants.DevStage;
import addsynth.material.compat.MaterialsCompat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms.IMCMessage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value = ADDSynthMaterials.MOD_ID)
public final class ADDSynthMaterials {

  public static final String MOD_ID = "addsynth_materials";
  public static final String MOD_NAME = "ADDSynth Materials";
  public static final String VERSION = "1.0";
  public static final String VERSION_DATE = ADDSynthCore.VERSION_DATE;
  public static final DevStage DEV_STAGE = ADDSynthCore.DEV_STAGE;

  public static final Logger log = LogManager.getLogger(MOD_NAME);

  public ADDSynthMaterials(final FMLJavaModLoadingContext context){
    final IEventBus bus = context.getModEventBus();
    bus.addListener(ADDSynthMaterials::main_setup);
    bus.addListener(ADDSynthMaterials::client_setup);
    bus.addListener(MaterialsCompat::sendIMCMessages);
    bus.addListener(ADDSynthMaterials::process_imc_messages);
    MinecraftForge.EVENT_BUS.addListener(ADDSynthMaterials::onServerStarted);
    MinecraftForge.EVENT_BUS.addListener(MaterialsRegister::onMissingEntries);
  }

  private static final void main_setup(final FMLCommonSetupEvent event){
    CommonUtil.displayModInfo(log, MOD_NAME, "ADDSynth", VERSION, DEV_STAGE, VERSION_DATE);
  }

  public static void onServerStarted(final ServerStartedEvent event){
    /*
    if(Compatibility.PROJECT_E.isLoaded()){
      if(DEV_STAGE.isDevelopment){
        EMCValue.check_items(MOD_ID);
      }
    }
    */
  }

  private static final void client_setup(final FMLClientSetupEvent event){
    CreativeTab.register();
  }

  private static final void process_imc_messages(final InterModProcessEvent event){
    final Stream<IMCMessage> message_stream = event.getIMCStream();
    message_stream.forEach(message -> {
      final String sender  = message.senderModId();
      final String type    = message.method();
      final Object payload = message.messageSupplier().get();
    });
  }

}
