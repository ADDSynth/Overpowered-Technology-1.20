package addsynth.overpoweredtechnology.machines.inverter;

import addsynth.core.gui.util.GuiUtil;
import addsynth.energy.lib.gui.GuiEnergyBase;
import addsynth.energy.lib.gui.widgets.WorkProgressBar;
import addsynth.overpoweredtechnology.config.Config;
import addsynth.overpoweredtechnology.game.reference.GuiReference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public final class GuiInverter extends GuiEnergyBase<TileInverter, ContainerInverter> {

  private final WorkProgressBar work_progress_bar = new WorkProgressBar(8, 84, 160, 5, 8, 194);
  
  public GuiInverter(final ContainerInverter container, final Inventory player_inventory, final Component title){
    super(176, 187, container, player_inventory, title, GuiReference.inverter);
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
    draw_status(graphics, tile.getStatus());
    
    final ItemStack s1 = tile.getWorkingInventory().getStackInSlot(0);
    if(Config.blend_working_item.get()){
      final ItemStack s2 = TileInverter.getInverted(s1);
      GuiUtil.blendItemStacks(graphics, s1, s2, 77, 44, work_progress_bar.getWorkTime());
    }
    else{
      graphics.renderItem(s1, 77, 44);
    }
    
    draw_text_center(graphics, work_progress_bar.getWorkTimeProgress(), center_x, 70);
    draw_time_left(graphics, 93);
  }

}
