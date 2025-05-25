package addsynth.overpoweredmod.machines.plasma_generator;

import addsynth.core.game.item.constants.ItemConstants;
import addsynth.core.util.math.common.CommonMath;
import addsynth.energy.lib.gui.GuiEnergyBase;
import addsynth.energy.lib.gui.widgets.AutoShutoffCheckbox;
import addsynth.energy.lib.gui.widgets.OnOffSwitch;
import addsynth.energy.lib.gui.widgets.WorkProgressBar;
import addsynth.overpoweredmod.game.NetworkHandler;
import addsynth.overpoweredmod.game.reference.GuiReference;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public final class GuiPlasmaGenerator extends GuiEnergyBase<TilePlasmaGenerator, ContainerPlasmaGenerator> {

  private final WorkProgressBar work_progress_bar = new WorkProgressBar(8, 93, 166, 5, 7, 204);
  
  private EditBox text_box;
  
  public GuiPlasmaGenerator(final ContainerPlasmaGenerator container, final Inventory player_inventory, final Component title){
    super(183, 196, container, player_inventory, title, GuiReference.plasma_generator);
  }

  // FEATURE: Override the Auto Shutoff checkbox to automatically set the Number text box to a valid range when checked.

  private static final class OutputNumberThresholdTextBox extends EditBox {
  
    private final TilePlasmaGenerator tile;
    
    public OutputNumberThresholdTextBox(Font fontIn, int x, int y, int width, int height, TilePlasmaGenerator tile){
      super(fontIn, x, y, width, height, Component.empty());
      this.tile = tile;
      setValue(Integer.toString(tile.get_output_number())); // have not set responder yet, so it won't react
      setTextColor(16777215);
      setResponder(this::text_field_changed);
    }
    
    private final void text_field_changed(final String text){
      int captured_value = 0;
      try{
        captured_value = Integer.parseUnsignedInt(text);
      }
      catch(NumberFormatException e){
        return; // do nothing if input is invalid
      }

      final int adjusted_value = CommonMath.clamp(captured_value, 1, ItemConstants.stack_size);
      if(adjusted_value != captured_value){ // if valid but outside range
        setValue(Integer.toString(adjusted_value)); // will call the responder again
        return;
      }
      if(captured_value != tile.get_output_number()){
        NetworkHandler.INSTANCE.sendToServer(new SetOutputThresholdMessage(tile.getBlockPos(), captured_value));
      }
    }
  }

  @Override
  protected final void init(){
    super.init();
    addRenderableWidget(new OnOffSwitch<>(this, tile));
    addRenderableWidget(new AutoShutoffCheckbox<TilePlasmaGenerator>(this.leftPos + 19, this.topPos + 52, tile));
    
    this.text_box = new OutputNumberThresholdTextBox(this.font, this.leftPos + 139, this.topPos + 51, 35, 15, tile);
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
    draw_status_after_switch(graphics, tile.getStatus());
    draw_energy_usage_below_switch(graphics);
    draw_text_right(graphics, work_progress_bar.getWorkTimeProgress(), 77, 74);
    draw_time_left(graphics, 102);
  }

}
