package addsynth.core.gameplay.blocks.jukebox;

import java.util.function.Supplier;
import addsynth.core.util.game.MinecraftUtility;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class JukeboxMessage {

  public enum Command {
    PLAYSTOP, SKIP_LEFT, SKIP_RIGHT, TOGGLE_SHUFFLE, TOGGLE_REPEAT, TOGGLE_OUTPUT_REDSTONE, ADJUST_BUFFER_TIME,
    RESET, CHANGE_VOLUME
  }

  private BlockPos position;
  private Command command;
  private int time;

  public JukeboxMessage(final BlockPos position, final Command command){
    this.position = position;
    this.command = command;
  }

  public JukeboxMessage(final BlockPos position, final Command command, final int time){
    this.position = position;
    this.command = command;
    this.time = time;
  }

  public static final void encode(final JukeboxMessage message, final FriendlyByteBuf buf){
    buf.writeBlockPos(message.position);
    buf.writeInt(message.command.ordinal());
    buf.writeInt(message.time);
  }

  public static final JukeboxMessage decode(final FriendlyByteBuf buf){
    final Command[] values = Command.values();
    return new JukeboxMessage(buf.readBlockPos(), values[buf.readInt()], buf.readInt());
  }

  public static void handle(JukeboxMessage message, final Supplier<NetworkEvent.Context> context_supplier){
    final NetworkEvent.Context context = context_supplier.get();
    final ServerPlayer player = context.getSender();
    if(player != null){
      @SuppressWarnings("resource")
      final ServerLevel level = player.serverLevel();
      context.enqueueWork(() -> {
        if(level.isLoaded(message.position)){
          final TileJukeboxPlayer jukebox = MinecraftUtility.getTileEntity(message.position, level, TileJukeboxPlayer.class);
          if(jukebox != null){
            switch(message.command){
            case PLAYSTOP:               jukebox.playStop(); break;
            case SKIP_LEFT:              jukebox.skipLeft(); break;
            case SKIP_RIGHT:             jukebox.skipRight(); break;
            case TOGGLE_SHUFFLE:         jukebox.toggleShuffle(); break;
            case TOGGLE_REPEAT:          jukebox.toggleRepeat(); break;
            case TOGGLE_OUTPUT_REDSTONE: jukebox.toggleRedstoneOutput(); break;
            case ADJUST_BUFFER_TIME:     jukebox.setDelayTime(message.time); break;
            case RESET:                  jukebox.reset(); break;
            case CHANGE_VOLUME: break;
            }
          }
        }
      });
    }
  }

}
