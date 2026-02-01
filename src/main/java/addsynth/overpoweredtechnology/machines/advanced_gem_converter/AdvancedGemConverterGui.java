package addsynth.overpoweredtechnology.machines.advanced_gem_converter;

import addsynth.energy.lib.gui.GuiEnergyBase;
import addsynth.energy.lib.gui.widgets.WorkProgressBar;
import addsynth.overpoweredtechnology.config.Config;
import addsynth.overpoweredtechnology.game.NetworkHandler;
import addsynth.overpoweredtechnology.game.reference.GuiReference;
import addsynth.overpoweredtechnology.game.reference.OverpoweredBlocks;
import addsynth.overpoweredtechnology.game.reference.OverpoweredItems;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class AdvancedGemConverterGui extends GuiEnergyBase<TileAdvancedGemConverter, AdvancedGemConverterContainer> {

  private ItemStack working_stack;
  private WorkProgressBar progress_bar = new WorkProgressBar(15, 53, 69, 2, 43, 199);
  private AbstractButton craftLightBlockButton;
  private AbstractButton craftEnergyCrystalButton;

  public AdvancedGemConverterGui(final AdvancedGemConverterContainer container, final Inventory player_inventory, final Component title){
    super(182, 194, container, player_inventory, title, GuiReference.advanced_gem_converter);
  }

  private final class CraftButton extends AbstractButton {
    private final ItemStack itemstack;
    private final Runnable onPress;
    private final int renderX;
    private final int renderY;
    public CraftButton(int x, int y, final ItemStack itemstack, Runnable onPress){
      super(x, y, 48, 20, Component.empty());
      this.itemstack = itemstack;
      this.onPress = onPress;
      renderX = x + 16;
      renderY = y + 2;
    }
    @Override
    public final void onPress(){
      onPress.run();
    }
    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partial_ticks){
      super.renderWidget(graphics, mouseX, mouseY, partial_ticks);
      graphics.renderFakeItem(itemstack, renderX, renderY);
    }
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput){
    }
  }

  @Override
  protected final void init(){
    super.init();
    craftLightBlockButton = new CraftButton(leftPos + 95, topPos + 50, new ItemStack(OverpoweredBlocks.light_block.get()), () ->
      NetworkHandler.INSTANCE.sendToServer(new AdvancedGemConverterCommand(tile.getBlockPos(), 1))
    );
    craftEnergyCrystalButton = new CraftButton(leftPos + 95, topPos + 75, new ItemStack(OverpoweredItems.energy_crystal.get()), () ->
      NetworkHandler.INSTANCE.sendToServer(new AdvancedGemConverterCommand(tile.getBlockPos(), 0))
    );
    addRenderableWidget(craftLightBlockButton);
    addRenderableWidget(craftEnergyCrystalButton);
  }

  @Override
  protected void containerTick(){
    if(craftLightBlockButton != null){
      craftLightBlockButton.active = tile.canCraftLightBlock();
    }
    if(craftEnergyCrystalButton != null){
      craftEnergyCrystalButton.active = tile.canCraftEnergyCrystal();
    }
  }

  @Override
  protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY){
    draw_background_texture(graphics);
    progress_bar.draw(graphics, this, tile);
  }

  @Override
  protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY){
    draw_title(graphics);
    draw_energy_usage(graphics);
    working_stack = tile.getWorkingInventory().getStackInSlot(0);
    if(Config.blend_working_item.get()){
    }
    graphics.renderItem(working_stack, 65, 32);
    draw_text_center(graphics, progress_bar.getWorkTimeProgress(), 96, 36);
    draw_text_center(graphics, Integer.toString(tile.getLowestValue()), 162, 47);
    draw_time_left_center(graphics, tile, 99); // TODO: Job system probably isn't set up correctly to handle the Advanced Gem Converter on the job when a gem is instantly moved to the slot of the same gem type, so it will report inaccurate time remaining.
  }

}
