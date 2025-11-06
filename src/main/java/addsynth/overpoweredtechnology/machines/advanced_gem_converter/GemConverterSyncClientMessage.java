package addsynth.overpoweredtechnology.machines.advanced_gem_converter;

import addsynth.core.util.network.TileEntityClientMessage;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class GemConverterSyncClientMessage extends TileEntityClientMessage<TileAdvancedGemConverter> {

  private final int lowest_level;
  private final int next_slot;

  public GemConverterSyncClientMessage(final BlockPos position, final int lowest_level, final int next_slot){
    super(position, TileAdvancedGemConverter.class);
    this.lowest_level = lowest_level;
    this.next_slot = next_slot;
  }

  @Override
  public final void encode(final FriendlyByteBuf buf){
    buf.writeBlockPos(position);
    buf.writeInt(lowest_level);
    buf.writeInt(next_slot);
  }

  public static final GemConverterSyncClientMessage decode(final FriendlyByteBuf buf){
    return new GemConverterSyncClientMessage(buf.readBlockPos(), buf.readInt(), buf.readInt());
  }

  @Override
  protected final void handle(final ClientLevel level, final LocalPlayer player, final TileAdvancedGemConverter tile){
    tile.syncClient(lowest_level, next_slot);
  }

}
