package addsynth.overpoweredtechnology.machines.portal.control_panel;

import addsynth.core.util.math.number.BinaryEncoder;
import addsynth.core.util.network.TileEntityClientMessage;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public final class SyncPortalDataMessage extends TileEntityClientMessage<TilePortalControlPanel> {

  private final boolean[] items;
  private final PortalMessage message;
  private final boolean valid_portal;

  public SyncPortalDataMessage(final BlockPos position, final boolean[] items, final PortalMessage message, final boolean valid){
    super(position, TilePortalControlPanel.class);
    this.items = items;
    this.message = message;
    this.valid_portal = valid;
  }

  public SyncPortalDataMessage(final FriendlyByteBuf buf){
    super(buf.readBlockPos(), TilePortalControlPanel.class);
    items = BinaryEncoder.getArray(buf.readByte(), 8);
    message = buf.readEnum(PortalMessage.class);
    valid_portal = buf.readBoolean();
  }

  @Override
  public final void encode(final FriendlyByteBuf buf){
    buf.writeBlockPos(position);
    buf.writeByte(BinaryEncoder.encodeByte(items));
    buf.writeEnum(message);
    buf.writeBoolean(valid_portal);
  }

  @Override
  protected final void handle(final ClientLevel level, final LocalPlayer player, final TilePortalControlPanel control_panel){
    control_panel.setData(items, message, valid_portal);
  }

}
