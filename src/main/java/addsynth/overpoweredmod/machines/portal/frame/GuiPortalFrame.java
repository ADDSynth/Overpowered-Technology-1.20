package addsynth.overpoweredmod.machines.portal.frame;

import addsynth.core.gui.GuiContainerBase;
import addsynth.overpoweredmod.game.reference.GuiReference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public final class GuiPortalFrame extends GuiContainerBase<ContainerPortalFrame> {

  public GuiPortalFrame(final ContainerPortalFrame container, final Inventory player_inventory, final Component title){
    super(container, player_inventory, title, GuiReference.portal_frame);
  }

  @Override
  protected final void renderLabels(GuiGraphics graphics, final int mouseX, final int mouseY){
    draw_title(graphics);
  }

}
