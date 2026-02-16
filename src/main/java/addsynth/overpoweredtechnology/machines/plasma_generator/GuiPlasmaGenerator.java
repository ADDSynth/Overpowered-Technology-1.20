package addsynth.overpoweredtechnology.machines.plasma_generator;

import addsynth.core.game.item.constants.ItemConstants;
import addsynth.core.gui.widgets.text_box.UnsignedIntegerTextBox;
import addsynth.energy.lib.gui.GuiEnergyBase;
import addsynth.energy.lib.gui.widgets.AutoShutoffCheckbox;
import addsynth.energy.lib.gui.widgets.OnOffSwitch;
import addsynth.energy.lib.gui.widgets.WorkProgressBar;
import addsynth.overpoweredtechnology.game.NetworkHandler;
import addsynth.overpoweredtechnology.game.reference.GuiReference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public final class GuiPlasmaGenerator extends GuiEnergyBase<TilePlasmaGenerator, ContainerPlasmaGenerator> {

  private final WorkProgressBar work_progress_bar = new WorkProgressBar(8, 93, 166, 5, 7, 204);
  
  private UnsignedIntegerTextBox text_box;
  
  public GuiPlasmaGenerator(final ContainerPlasmaGenerator container, final Inventory player_inventory, final Component title){
    super(183, 196, container, player_inventory, title, GuiReference.plasma_generator);
  }

  @Override
  protected final void init(){
    super.init();
    addRenderableWidget(new OnOffSwitch<>(this, tile));
    addRenderableWidget(new AutoShutoffCheckbox<TilePlasmaGenerator>(this.leftPos + 19, this.topPos + 52, tile));
    
    text_box = new UnsignedIntegerTextBox(this.font, this.leftPos + 139, this.topPos + 51, 35, 15, tile.get_output_number(), 1, ItemConstants.stack_size);
    text_box.setTextColor(16777215);
    text_box.setCallback((Integer value) -> {
      NetworkHandler.INSTANCE.sendToServer(new SetOutputThresholdMessage(tile.getBlockPos(), value));
    });
    addWidget(text_box);
  }

  @Override
  protected final void containerTick(){
    super.containerTick();
    if(text_box != null){
      text_box.tick();
    }
  }

  @Override
  public final void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks){
    super.render(graphics, mouseX, mouseY, partialTicks);
    if(text_box != null){
      text_box.render(graphics, mouseX, mouseY, partialTicks);
    }
  }

  @Override
  protected final void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY){
    draw_background_texture(graphics);
    work_progress_bar.draw(graphics, this, tile);
  }

  @Override
  protected final void renderLabels(GuiGraphics graphics, int mouseX, int mouseY){
    draw_title(graphics);
    draw_status_after_switch(graphics, tile);
    draw_energy_usage_below_switch(graphics);
    draw_text_right(graphics, work_progress_bar.getWorkTimeProgress(), 77, 74);
    draw_time_left_center(graphics, tile, 102);
  }

}
