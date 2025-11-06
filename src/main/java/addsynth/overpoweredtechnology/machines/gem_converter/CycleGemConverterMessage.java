package addsynth.overpoweredtechnology.machines.gem_converter;

import addsynth.core.util.network.TileEntityNetworkMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public final class CycleGemConverterMessage extends TileEntityNetworkMessage<TileGemConverter> {

  private final boolean cycle_direction;

  public CycleGemConverterMessage(final BlockPos position, final boolean cycle_direction){
    super(position, TileGemConverter.class);
    this.cycle_direction = cycle_direction;
  }

  @Override
  public final void encode(final FriendlyByteBuf buf){
    buf.writeBlockPos(position);
    buf.writeBoolean(cycle_direction);
  }

  public static final CycleGemConverterMessage decode(final FriendlyByteBuf buf){
    return new CycleGemConverterMessage(buf.readBlockPos(), buf.readBoolean());
  }

  @Override
  protected final void handle(final ServerLevel level, final ServerPlayer player, final TileGemConverter tile){
    tile.cycle(cycle_direction);
  }

}
