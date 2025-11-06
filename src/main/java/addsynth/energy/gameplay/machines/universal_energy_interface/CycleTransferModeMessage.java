package addsynth.energy.gameplay.machines.universal_energy_interface;

import addsynth.core.util.network.TileEntityNetworkMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public final class CycleTransferModeMessage extends TileEntityNetworkMessage<TileUniversalEnergyInterface> {

  public CycleTransferModeMessage(final BlockPos position){
    super(position, TileUniversalEnergyInterface.class);
  }

  @Override
  public final void encode(final FriendlyByteBuf buf){
    buf.writeBlockPos(position);
  }

  public static final CycleTransferModeMessage decode(final FriendlyByteBuf buf){
    return new CycleTransferModeMessage(buf.readBlockPos());
  }

  @Override
  protected final void handle(final ServerLevel level, final ServerPlayer player, final TileUniversalEnergyInterface tile){
    tile.set_next_transfer_mode();
  }

}
