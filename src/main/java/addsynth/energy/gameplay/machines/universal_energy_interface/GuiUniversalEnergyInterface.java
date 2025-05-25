package addsynth.energy.gameplay.machines.universal_energy_interface;

import addsynth.energy.gameplay.NetworkHandler;
import addsynth.energy.gameplay.reference.EnergyText;
import addsynth.energy.gameplay.reference.GuiReference;
import addsynth.energy.lib.gui.GuiEnergyBase;
import addsynth.energy.lib.gui.widgets.EnergyProgressBar;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public final class GuiUniversalEnergyInterface extends GuiEnergyBase<TileUniversalEnergyInterface, ContainerUniversalEnergyInterface> {

  private static final int button_width = 90;
  private Button transfer_mode_button;
  private final EnergyProgressBar energy_bar = new EnergyProgressBar(156, 18, 12, 34, 206, 28);
  
  private static final int line_1 = 21;
  private static final int line_2 = 41;

  public GuiUniversalEnergyInterface(final ContainerUniversalEnergyInterface container, final Inventory player_inventory, final Component title){
    super(176, 60, container, player_inventory, title, GuiReference.universal_interface);
  }

  @Override
  protected final void init(){
    super.init();
    final int button_x = leftPos + center_x - (button_width / 2) + 4;
    transfer_mode_button = Button.builder(tile.get_transfer_mode().title, (Button button) -> {
      NetworkHandler.INSTANCE.sendToServer(new CycleTransferModeMessage(tile.getBlockPos()));
    }).bounds(button_x, topPos + 17, button_width, 16).build();
    addRenderableWidget(transfer_mode_button);
  }

  @Override
  protected final void containerTick(){
    transfer_mode_button.setMessage(tile.get_transfer_mode().title);
  }

  @Override
  protected final void renderBg(GuiGraphics graphics, final float partialTicks, final int mouseX, final int mouseY){
    draw_background_texture(graphics);
    energy_bar.drawVertical(graphics, this, energy);
  }

  @Override
  protected final void renderLabels(GuiGraphics graphics, final int mouseX, final int mouseY){
    draw_title(graphics);
    draw_text_left(graphics, EnergyText.mode_text.getString()+":", 6, line_1);
    draw_text_left(graphics, EnergyText.energy_text.getString()+":", 6, line_2);
    draw_text_right(graphics, energy.getEnergy() + " / "+energy.getCapacity(), 130, line_2);
  }

}
