package addsynth.overpoweredtechnology.machines.laser.network_messages;

import addsynth.core.util.network.TileEntityNetworkMessage;
import addsynth.overpoweredtechnology.machines.laser.machine.LaserNetwork;
import addsynth.overpoweredtechnology.machines.laser.machine.TileLaserHousing;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public final class SetLaserDistanceMessage extends TileEntityNetworkMessage<TileLaserHousing> {

  private final int laser_distance;

  public SetLaserDistanceMessage(final BlockPos position, final int laser_distance){
    super(position, TileLaserHousing.class);
    this.laser_distance = laser_distance;
  }

  @Override
  public final void encode(final FriendlyByteBuf buf){
    buf.writeBlockPos(position);
    buf.writeInt(laser_distance);
  }

  public static final SetLaserDistanceMessage decode(final FriendlyByteBuf buf){
    return new SetLaserDistanceMessage(buf.readBlockPos(), buf.readInt());
  }

  @Override
  protected final void handle(final ServerLevel level, final ServerPlayer player, final TileLaserHousing tile){
    final LaserNetwork network = tile.getBlockNetwork();
    if(network != null){
      network.setLaserDistance(laser_distance);
    }
  }

}
