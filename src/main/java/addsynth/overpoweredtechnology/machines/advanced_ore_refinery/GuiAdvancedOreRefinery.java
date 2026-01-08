package addsynth.overpoweredtechnology.machines.advanced_ore_refinery;

import addsynth.energy.lib.gui.GuiEnergyBase;
import addsynth.energy.lib.gui.widgets.WorkProgressBar;
import addsynth.overpoweredtechnology.game.reference.GuiReference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public final class GuiAdvancedOreRefinery extends GuiEnergyBase<TileAdvancedOreRefinery, ContainerOreRefinery> {

  private final WorkProgressBar work_progress_bar = new WorkProgressBar(8, 83, 160, 5, 8, 194);

  public GuiAdvancedOreRefinery(final ContainerOreRefinery container, final Inventory player_inventory, final Component title){
    super(176, 186, container, player_inventory, title, GuiReference.advanced_ore_refinery);
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
    graphics.renderItem(tile.getWorkingInventory().getStackInSlot(0), 76, 43);
    draw_text_center(graphics, work_progress_bar.getWorkTimeProgress(), 69);
    draw_time_left_center(graphics, 92, tile);
  }

}
