package addsynth.overpoweredtechnology.machines.advanced_gem_converter;

import java.util.function.Supplier;
import addsynth.core.util.game.MinecraftUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

public class GemConverterSyncClientMessage {

  private final BlockPos position;
  private final int lowest_level;
  private final int next_slot;

  public GemConverterSyncClientMessage(final BlockPos position, final int lowest_level, final int next_slot){
    this.position = position;
    this.lowest_level = lowest_level;
    this.next_slot = next_slot;
  }

  public static final void encode(final GemConverterSyncClientMessage message, final FriendlyByteBuf buf){
    buf.writeBlockPos(message.position);
    buf.writeInt(message.lowest_level);
    buf.writeInt(message.next_slot);
  }

  public static final GemConverterSyncClientMessage decode(final FriendlyByteBuf buf){
    return new GemConverterSyncClientMessage(buf.readBlockPos(), buf.readInt(), buf.readInt());
  }

  public static final void handle(final GemConverterSyncClientMessage message, final Supplier<NetworkEvent.Context> context_supplier){
    final NetworkEvent.Context context = context_supplier.get();
    context.enqueueWork(() -> {
      @SuppressWarnings("resource")
      final Minecraft minecraft = Minecraft.getInstance();
      @SuppressWarnings({"null", "resource"})
      final Level level = minecraft.player.level();
      if(level.isLoaded(message.position)){
        final TileAdvancedGemConverter tile = MinecraftUtility.getTileEntity(message.position, level, TileAdvancedGemConverter.class);
        if(tile != null){
          tile.syncClient(message.lowest_level, message.next_slot);
        }
      }
    });
    context.setPacketHandled(true);
  }

}
