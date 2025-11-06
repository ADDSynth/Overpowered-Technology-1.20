package addsynth.overpoweredtechnology.machines.portal.control_panel;

import addsynth.core.util.game.data.AdvancementUtil;
import addsynth.core.util.network.TileEntityNetworkMessage;
import addsynth.overpoweredtechnology.assets.CustomAdvancements;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public final class GeneratePortalMessage extends TileEntityNetworkMessage<TilePortalControlPanel> {

  public GeneratePortalMessage(final BlockPos position){
    super(position, TilePortalControlPanel.class);
  }

  @Override
  public final void encode(final FriendlyByteBuf buf){
    buf.writeBlockPos(position);
  }

  public static final GeneratePortalMessage decode(final FriendlyByteBuf buf){
    return new GeneratePortalMessage(buf.readBlockPos());
  }

  @Override
  protected final void handle(final ServerLevel level, final ServerPlayer player, final TilePortalControlPanel tile){
    tile.generate_portal();
    AdvancementUtil.grantAdvancement(player, CustomAdvancements.PORTAL);
  }

}
