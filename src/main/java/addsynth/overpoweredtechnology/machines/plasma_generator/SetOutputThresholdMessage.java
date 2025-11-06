package addsynth.overpoweredtechnology.machines.plasma_generator;

import addsynth.core.util.network.TileEntityNetworkMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public final class SetOutputThresholdMessage extends TileEntityNetworkMessage<TilePlasmaGenerator> {

  private final int output_threshold;

  public SetOutputThresholdMessage(final BlockPos position, final int output_threshold){
    super(position, TilePlasmaGenerator.class);
    this.output_threshold = output_threshold;
  }

  @Override
  public final void encode(final FriendlyByteBuf buf){
    buf.writeBlockPos(position);
    buf.writeInt(output_threshold);
  }

  public static final SetOutputThresholdMessage decode(final FriendlyByteBuf buf){
    return new SetOutputThresholdMessage(buf.readBlockPos(), buf.readInt());
  }

  @Override
  protected final void handle(final ServerLevel level, final ServerPlayer player, final TilePlasmaGenerator tile){
    tile.set_output_number(output_threshold);
  }

}
