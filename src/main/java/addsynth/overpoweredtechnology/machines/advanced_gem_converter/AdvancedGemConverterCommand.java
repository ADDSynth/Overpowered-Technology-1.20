package addsynth.overpoweredtechnology.machines.advanced_gem_converter;

import addsynth.core.util.network.TileEntityNetworkMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class AdvancedGemConverterCommand extends TileEntityNetworkMessage<TileAdvancedGemConverter> {

  private final int command;

  public AdvancedGemConverterCommand(final BlockPos position, final int index){
    super(position, TileAdvancedGemConverter.class);
    command = index;
  }

  public AdvancedGemConverterCommand(final TileAdvancedGemConverter tile, final int index){
    super(tile.getBlockPos(), TileAdvancedGemConverter.class);
    command = index;
  }

  @Override
  public final void encode(final FriendlyByteBuf buf){
    buf.writeBlockPos(position);
    buf.writeInt(command);
  }

  public static final AdvancedGemConverterCommand decode(final FriendlyByteBuf buf){
    return new AdvancedGemConverterCommand(buf.readBlockPos(), buf.readInt());
  }

  @Override
  protected final void handle(final ServerLevel level, final ServerPlayer player, final TileAdvancedGemConverter tile){
    switch(command){
    case 0: tile.craftEnergyCrystal(); break;
    case 1: tile.craftLightBlock(); break;
    }
  }

}
