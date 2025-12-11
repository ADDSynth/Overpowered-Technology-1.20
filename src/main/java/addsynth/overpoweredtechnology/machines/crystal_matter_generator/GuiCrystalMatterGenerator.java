package addsynth.overpoweredtechnology.machines.crystal_matter_generator;

import addsynth.energy.lib.gui.GuiEnergyBase;
import addsynth.energy.lib.gui.widgets.OnOffSwitch;
import addsynth.energy.lib.gui.widgets.WorkProgressBar;
import addsynth.overpoweredtechnology.game.reference.GuiReference;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public final class GuiCrystalMatterGenerator extends GuiEnergyBase<TileCrystalMatterGenerator, ContainerCrystalGenerator> {

  private final WorkProgressBar work_progress_bar = new WorkProgressBar(8, 89, 166, 5, 8, 222);
  
  public GuiCrystalMatterGenerator(final ContainerCrystalGenerator container, final Inventory player_inventory, final Component title){
    super(182, 192, container, player_inventory, title, GuiReference.crystal_matter_generator);
  }

  @Override
  protected final void init(){
    super.init();
    addRenderableWidget(new OnOffSwitch<>(this, tile));
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
    draw_energy_usage(graphics, 6, 38);
    draw_text_center(graphics, work_progress_bar.getWorkTimeProgress(), 77);
    draw_time_left_center(graphics, 98);
  }

}
