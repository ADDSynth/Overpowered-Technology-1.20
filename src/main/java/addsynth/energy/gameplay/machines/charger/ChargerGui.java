package addsynth.energy.gameplay.machines.charger;

import addsynth.core.util.java.StringUtil;
import addsynth.energy.gameplay.reference.EnergyText;
import addsynth.energy.gameplay.reference.GuiReference;
import addsynth.energy.lib.gui.GuiEnergyBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public final class ChargerGui extends GuiEnergyBase<TileCharger, ChargerContainer> {

  public ChargerGui(final ChargerContainer container, final Inventory player_inventory, final Component title){
    super(176, 172, container, player_inventory, title, GuiReference.charger);
  }

  @Override
  protected final void renderLabels(GuiGraphics graphics, int mouseX, int mouseY){
    draw_title(graphics);
    //draw_energy_usage(graphics);
    draw_status(graphics, tile.getStatus(), 17);
    draw_time_left_center(graphics, 76);
  }

  @Override
  protected final void draw_time_left_center(final GuiGraphics graphics, final int y){
    final int ticks = tile.getTimeLeft();
    final String time_left = StringUtil.print_time(ticks);
    draw_text_center(graphics, EnergyText.time_left_text.getString()+": "+time_left, imageWidth/2, y);
  }

}
