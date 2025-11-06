package addsynth.overpoweredtechnology.game;

import addsynth.core.util.network.ADDSynthNetworkHandler;
import addsynth.overpoweredtechnology.OverpoweredTechnology;
import addsynth.overpoweredtechnology.machines.advanced_gem_converter.AdvancedGemConverterCommand;
import addsynth.overpoweredtechnology.machines.advanced_gem_converter.GemConverterSyncClientMessage;
import addsynth.overpoweredtechnology.machines.gem_converter.CycleGemConverterMessage;
import addsynth.overpoweredtechnology.machines.laser.network_messages.LaserClientSyncMessage;
import addsynth.overpoweredtechnology.machines.laser.network_messages.SetLaserDistanceMessage;
import addsynth.overpoweredtechnology.machines.plasma_generator.SetOutputThresholdMessage;
import addsynth.overpoweredtechnology.machines.portal.control_panel.GeneratePortalMessage;
import addsynth.overpoweredtechnology.machines.portal.control_panel.SyncPortalDataMessage;
import addsynth.overpoweredtechnology.machines.suspension_bridge.RotateBridgeMessage;
import addsynth.overpoweredtechnology.machines.suspension_bridge.SyncClientBridgeMessage;
import net.minecraftforge.network.simple.SimpleChannel;

// https://docs.minecraftforge.net/en/latest/networking/simpleimpl/

public final class NetworkHandler extends ADDSynthNetworkHandler {

  public static final SimpleChannel INSTANCE = createChannel(OverpoweredTechnology.getLocation("network_channel"));

  public static final void registerMessages(){
    registerServerMessage( 0, INSTANCE, CycleGemConverterMessage.class,      CycleGemConverterMessage::decode);
    registerServerMessage( 1, INSTANCE, SetLaserDistanceMessage.class,       SetLaserDistanceMessage::decode);
    registerClientMessage( 2, INSTANCE, LaserClientSyncMessage.class,        LaserClientSyncMessage::decode);
    registerServerMessage( 3, INSTANCE, GeneratePortalMessage.class,         GeneratePortalMessage::decode);
    registerClientMessage( 4, INSTANCE, SyncPortalDataMessage.class,         SyncPortalDataMessage::new);
    registerClientMessage( 6, INSTANCE, SyncClientBridgeMessage.class,       SyncClientBridgeMessage::new);
    registerServerMessage( 7, INSTANCE, RotateBridgeMessage.class,           RotateBridgeMessage::decode);
    registerServerMessage( 8, INSTANCE, SetOutputThresholdMessage.class,     SetOutputThresholdMessage::decode);
    registerClientMessage( 9, INSTANCE, GemConverterSyncClientMessage.class, GemConverterSyncClientMessage::decode);
    registerServerMessage(10, INSTANCE, AdvancedGemConverterCommand.class,   AdvancedGemConverterCommand::decode);
  }

}
