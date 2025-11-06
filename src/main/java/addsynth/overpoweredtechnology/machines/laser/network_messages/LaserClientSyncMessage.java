package addsynth.overpoweredtechnology.machines.laser.network_messages;

import java.util.List;
import addsynth.core.util.network.NetworkUtil;
import addsynth.core.util.network.BlockNetworkClientMessage;
import addsynth.overpoweredtechnology.machines.laser.machine.TileLaserHousing;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public final class LaserClientSyncMessage extends BlockNetworkClientMessage<TileLaserHousing> {

  private final int number_of_lasers;

  public LaserClientSyncMessage(final BlockPos[] positions, final int number_of_lasers){
    super(positions, TileLaserHousing.class);
    this.number_of_lasers = number_of_lasers;
  }

  public LaserClientSyncMessage(final List<BlockPos> positions, final int number_of_lasers){
    this(positions.toArray(new BlockPos[positions.size()]), number_of_lasers);
  }

  @Override
  public final void encode(final FriendlyByteBuf buf){
    NetworkUtil.writeBlockPositions(buf, positions);
    buf.writeInt(number_of_lasers);
  }

  public static final LaserClientSyncMessage decode(final FriendlyByteBuf buf){
    return new LaserClientSyncMessage(NetworkUtil.readBlockPositions(buf), buf.readInt());
  }

  @Override
  protected final void handle(final ClientLevel level, final LocalPlayer player, final TileLaserHousing tile){
    tile.number_of_lasers = number_of_lasers;
  }

}
