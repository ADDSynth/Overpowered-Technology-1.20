package addsynth.overpoweredtechnology.machines.magic_infuser;

import addsynth.energy.lib.gui.GuiEnergyBase;
import addsynth.energy.lib.gui.widgets.WorkProgressBar;
import addsynth.overpoweredtechnology.game.reference.GuiReference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public final class GuiMagicInfuser extends GuiEnergyBase<TileMagicInfuser, ContainerMagicInfuser> {

  private final WorkProgressBar work_progress_bar = new WorkProgressBar(8, 84, 160, 5, 8, 194);
  
  public GuiMagicInfuser(final ContainerMagicInfuser container, final Inventory player_inventory, final Component title){
    super(176, 187, container, player_inventory, title, GuiReference.magic_infuser);
  }

  @Override
  protected final void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY){
    draw_background_texture(graphics);
    work_progress_bar.draw(graphics, this, tile);
  }

  @Override
  protected final void renderLabels(GuiGraphics graphics, int mouseX, int mouseY){
    draw_title(graphics);
    draw_energy_usage(graphics);
    draw_status(graphics, tile);
    graphics.renderItem(tile.getWorkingInventory().getStackInSlot(0), 78, 44);
    graphics.renderItem(tile.getWorkingInventory().getStackInSlot(1), 95, 44);
    draw_text_center(graphics, work_progress_bar.getWorkTimeProgress(), center_x, 72);
    draw_time_left_center(graphics, tile, 93);
  }

}
