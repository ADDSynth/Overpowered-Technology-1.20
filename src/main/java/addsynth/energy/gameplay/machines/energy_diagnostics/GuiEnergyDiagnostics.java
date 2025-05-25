package addsynth.energy.gameplay.machines.energy_diagnostics;

import addsynth.core.gui.GuiBase;
import addsynth.core.gui.section.GuiSection;
import addsynth.core.gui.util.GuiUtil;
import addsynth.core.gui.widgets.buttons.ButtonUtil;
import addsynth.energy.gameplay.reference.GuiReference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public final class GuiEnergyDiagnostics extends GuiBase {

  private final TileEnergyDiagnostics tile;
  private static int draw_i;
  private static int draw_end_i;
  private static int draw_y;
  private static final int entries_per_page = 16;
  private int page = 0;
  private int begin = 0;
  private int end = 10;
  private static EnergyDiagnosticData diag_line;
  private final Button  left_button = ButtonUtil.getBigtLeftArrowButton( 70, 6, null);
  private final Button right_button = ButtonUtil.getBigRightArrowButton(105, 6, null);
  // private static final int x_space = 4;
  private static final int text_y = 30;
  private static final int y_space = 14;
  private static final GuiSection     name_column = GuiSection.dimensions(  6, text_y, 175, 282);
  private static final GuiSection     type_column = GuiSection.dimensions(185, text_y,  44, 282);
  private static final GuiSection   energy_column = GuiSection.dimensions(233, text_y,  75, 282);
  private static final GuiSection capacity_column = GuiSection.dimensions(312, text_y,  75, 282);
  private static final GuiSection  recieve_column = GuiSection.dimensions(391, text_y,  83, 282);
  private static final GuiSection  extract_column = GuiSection.dimensions(478, text_y,  83, 282);
  private static final GuiSection transfer_column = GuiSection.dimensions(565, text_y,  60, 282);

  public GuiEnergyDiagnostics(final TileEnergyDiagnostics tile, final Component title){
    super(631, 288, title, GuiReference.energy_diagnostics);
    this.tile = tile;
  }

  @Override
  protected final void drawGuiBackgroundLayer(GuiGraphics graphics, float partialTicks, int mouse_x, int mouse_y){
    draw_custom_background_texture(graphics, 640, 300);
  }

  @Override
  protected final void drawGuiForegroundLayer(GuiGraphics graphics, int mouse_x, int mouse_y){
    draw_title(graphics);
    graphics.drawString(font, "Page: "+(page+1), 36, 6, GuiUtil.text_color);
    if(tile.network_exists){
      // draw column headers
      draw_text_center(graphics, "TileEntity:",   name_column.horizontal_center, text_y);
      draw_text_center(graphics, "Type:",         type_column.horizontal_center, text_y);
      draw_text_center(graphics, "Energy:",     energy_column.horizontal_center, text_y);
      draw_text_center(graphics, "Capacity:", capacity_column.horizontal_center, text_y);
      draw_text_center(graphics, "Recieve:",   recieve_column.horizontal_center, text_y);
      draw_text_center(graphics, "Extract:",   extract_column.horizontal_center, text_y);
      draw_text_center(graphics, "Transfer:", transfer_column.horizontal_center, text_y);
      // set variables
      begin = 0 + (page * entries_per_page);
      end = Math.min(begin + entries_per_page, tile.diagnostics_data.size());
      draw_end_i = end - begin;
      // draw main list
      for(draw_i = 0; draw_i < draw_end_i; draw_i++){
        diag_line = tile.diagnostics_data.get(begin + draw_i);
        draw_y = text_y + y_space + (draw_i * y_space);
        draw_text_left(graphics, diag_line.name, name_column.left, draw_y);
        draw_text_center(graphics, diag_line.type.toString(), type_column.horizontal_center, draw_y);
        draw_text_right(graphics, String.format("%.2f", diag_line.energy),     energy_column.right, draw_y);
        draw_text_right(graphics, String.format("%.2f", diag_line.capacity), capacity_column.right, draw_y);
        draw_text_right(graphics, String.format("%.2f", diag_line.in) +" / "+String.format("%.2f", diag_line.max_receive),  recieve_column.right, draw_y);
        draw_text_right(graphics, String.format("%.2f", diag_line.out)+" / "+String.format("%.2f", diag_line.max_transmit), extract_column.right, draw_y);
        draw_text_right(graphics, String.format("%.2f", diag_line.transfer), transfer_column.right, draw_y);
      }
      // Draw Totals:
      draw_y = text_y + y_space + (y_space * entries_per_page);
      draw_text_center(graphics, "Totals:", name_column.horizontal_center, draw_y);
      draw_text_right(graphics, String.format("%.2f", tile.totals.energy),     energy_column.right, draw_y);
      draw_text_right(graphics, String.format("%.2f", tile.totals.capacity), capacity_column.right, draw_y);
      draw_text_right(graphics, String.format("%.2f", tile.totals.in) +" / "+String.format("%.2f", tile.totals.max_receive),  recieve_column.right, draw_y);
      draw_text_right(graphics, String.format("%.2f", tile.totals.out)+" / "+String.format("%.2f", tile.totals.max_transmit), extract_column.right, draw_y);
      draw_text_right(graphics, String.format("%.2f", tile.totals.transfer), transfer_column.right, draw_y);
    }
    else{
      draw_text_center(graphics, "Not connected to an Energy Network.", (154 - text_y)/2);
    }
  }

}
