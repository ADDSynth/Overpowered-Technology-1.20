package addsynth.overpoweredtechnology.machines.suspension_bridge;

import addsynth.core.util.network.TileEntityNetworkMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public final class RotateBridgeMessage extends TileEntityNetworkMessage<TileSuspensionBridge> {

  public RotateBridgeMessage(final BlockPos position){
    super(position, TileSuspensionBridge.class);
  }
  
  @Override
  public final void encode(final FriendlyByteBuf buf){
    buf.writeBlockPos(position);
  }
  
  public static final RotateBridgeMessage decode(final FriendlyByteBuf buf){
    return new RotateBridgeMessage(buf.readBlockPos());
  }

  @Override
  protected final void handle(final ServerLevel level, final ServerPlayer player, final TileSuspensionBridge tile){
    final BridgeNetwork bridge_network = tile.getBlockNetwork();
    if(bridge_network != null){
      bridge_network.rotate(level);
    }
  }

}
