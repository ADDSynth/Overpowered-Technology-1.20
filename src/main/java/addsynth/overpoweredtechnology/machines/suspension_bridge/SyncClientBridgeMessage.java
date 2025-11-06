package addsynth.overpoweredtechnology.machines.suspension_bridge;

import java.util.List;
import addsynth.core.util.network.NetworkUtil;
import addsynth.core.util.network.BlockNetworkClientMessage;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public final class SyncClientBridgeMessage extends BlockNetworkClientMessage<TileSuspensionBridge> {

  private BridgeMessage bridge_message;
  private final BridgeMessage[] messages;

  public SyncClientBridgeMessage(final BlockPos[] positions, final BridgeMessage bridge_message, final BridgeData[] bridge_data){
    super(positions, TileSuspensionBridge.class);
    this.bridge_message = bridge_message;
    messages = new BridgeMessage[]{
      bridge_data[0].message, bridge_data[1].message, bridge_data[2].message,
      bridge_data[3].message, bridge_data[4].message, bridge_data[5].message
    };
  }

  public SyncClientBridgeMessage(final List<BlockPos> positions, final BridgeMessage bridge_message, final BridgeData[] bridge_data){
    this(positions.toArray(new BlockPos[positions.size()]), bridge_message, bridge_data);
  }

  public SyncClientBridgeMessage(final BlockPos[] positions, final BridgeMessage bridge_message, final BridgeMessage[] messages){
    super(positions, TileSuspensionBridge.class);
    this.bridge_message = bridge_message;
    this.messages = messages;
  }

  public SyncClientBridgeMessage(final FriendlyByteBuf buf){
    super(NetworkUtil.readBlockPositions(buf), TileSuspensionBridge.class);
    bridge_message = buf.readEnum(BridgeMessage.class);
    messages = new BridgeMessage[]{
      buf.readEnum(BridgeMessage.class), buf.readEnum(BridgeMessage.class), buf.readEnum(BridgeMessage.class),
      buf.readEnum(BridgeMessage.class), buf.readEnum(BridgeMessage.class), buf.readEnum(BridgeMessage.class)
    };
  }

  @Override
  public final void encode(final FriendlyByteBuf buf){
    NetworkUtil.writeBlockPositions(buf, positions);
    buf.writeEnum(bridge_message);
    buf.writeEnum(messages[0]);
    buf.writeEnum(messages[1]);
    buf.writeEnum(messages[2]);
    buf.writeEnum(messages[3]);
    buf.writeEnum(messages[4]);
    buf.writeEnum(messages[5]);
  }

  @Override
  protected final void handle(final ClientLevel level, final LocalPlayer player, final TileSuspensionBridge tile){
    tile.setMessages(bridge_message, messages);
  }

}
