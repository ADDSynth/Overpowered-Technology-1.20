package addsynth.energy.gameplay.machines.solar_panel;

import java.util.function.Supplier;
import addsynth.core.util.game.MinecraftUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

/** Since all solar panel data can be calculated on the server, there's no need to save
 *  anything. Send all relevant data to the client. */
public final class SolarPanelData {

  private final BlockPos position;
  public SolarPanelStatus status;
  public double phase;
  public double energy;
  public int panel_count;
  public int blocked_count;
  public double theoretical_energy;

  public SolarPanelData(BlockPos position){
    this.position = position;
  }

  public final void set(SolarPanelStatus status, double phase, double energy, int panel_count, int blocked_count, double efficiency){
    this.status = status;
    this.phase = phase;
    this.energy = energy;
    this.panel_count = panel_count;
    this.blocked_count = blocked_count;
    this.theoretical_energy = efficiency;
  }

  public static void encode(SolarPanelData message, FriendlyByteBuf buf){
    buf.writeBlockPos(message.position);
    buf.writeInt(message.status.ordinal());
    buf.writeDouble(message.phase);
    buf.writeDouble(message.energy);
    buf.writeInt(message.panel_count);
    buf.writeInt(message.blocked_count);
    buf.writeDouble(message.theoretical_energy);
  }

  public static SolarPanelData decode(FriendlyByteBuf buf){
    SolarPanelData data = new SolarPanelData(buf.readBlockPos());
    data.status = SolarPanelStatus.values()[buf.readInt()];
    data.phase = buf.readDouble();
    data.energy = buf.readDouble();
    data.panel_count = buf.readInt();
    data.blocked_count = buf.readInt();
    data.theoretical_energy = buf.readDouble();
    return data;
  }

  public static void handle(SolarPanelData message, Supplier<NetworkEvent.Context> context_supplier){
    final NetworkEvent.Context context = context_supplier.get();
    context.enqueueWork(() -> {
      
      @SuppressWarnings("resource")
      final Minecraft minecraft = Minecraft.getInstance();
      @SuppressWarnings("null")
      final Level world = minecraft.player.level();
      
      SolarPanelControllerTile tile;
      if(world.isLoaded(message.position)){
        tile = MinecraftUtility.getTileEntity(message.position, world, SolarPanelControllerTile.class);
        if(tile != null){
          tile.setFromServer(message);
        }
      }
    });
    context.setPacketHandled(true);
  }

}
