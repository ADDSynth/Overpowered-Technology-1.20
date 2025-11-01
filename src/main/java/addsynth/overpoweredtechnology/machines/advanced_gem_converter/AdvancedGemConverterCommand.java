package addsynth.overpoweredtechnology.machines.advanced_gem_converter;

import java.util.function.Supplier;
import addsynth.core.util.game.MinecraftUtility;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class AdvancedGemConverterCommand {

  private final BlockPos position;
  private final int command;

  public AdvancedGemConverterCommand(final BlockPos position, final int index){
    this.position = position;
    command = index;
  }

  public AdvancedGemConverterCommand(final TileAdvancedGemConverter tile, final int index){
    position = tile.getBlockPos();
    command = index;
  }

  public static final void encode(final AdvancedGemConverterCommand message, final FriendlyByteBuf buf){
    buf.writeBlockPos(message.position);
    buf.writeInt(message.command);
  }

  public static final AdvancedGemConverterCommand decode(final FriendlyByteBuf buf){
    return new AdvancedGemConverterCommand(buf.readBlockPos(), buf.readInt());
  }

  public static final void handle(AdvancedGemConverterCommand message, final Supplier<NetworkEvent.Context> context_supplier){
    final NetworkEvent.Context context = context_supplier.get();
    final ServerPlayer player = context.getSender();
    if(player != null){
      context.enqueueWork(() -> {
        @SuppressWarnings("resource")
        final ServerLevel level = player.serverLevel();
        if(level.isLoaded(message.position)){
          final TileAdvancedGemConverter tile = MinecraftUtility.getTileEntity(message.position, level, TileAdvancedGemConverter.class);
          if(tile != null){
            switch(message.command){
            case 0: tile.craftEnergyCrystal(); break;
            case 1: tile.craftLightBlock(); break;
            }
          }
        }
      });
    }
    context.setPacketHandled(true);
  }

}
