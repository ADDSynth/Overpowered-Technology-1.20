package addsynth.overpoweredmod.machines.matter_compressor;

import addsynth.core.gui.widgets.rect.ProgressBar;
import addsynth.core.util.java.StringUtil;
import addsynth.core.util.math.common.RoundMode;
import addsynth.energy.lib.gui.GuiEnergyBase;
import addsynth.overpoweredmod.config.Config;
import addsynth.overpoweredmod.game.reference.GuiReference;
import addsynth.overpoweredmod.game.reference.OverpoweredBlocks;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public final class GuiMatterCompressor extends GuiEnergyBase<TileMatterCompressor, MatterCompressorContainer> {

  private static final Component black_hole_text = OverpoweredBlocks.black_hole.get().getName();
  private static final Component matter_text     = Component.translatable("gui.overpowered.matter_compressor.matter");

  private final ProgressBar progress_bar = new ProgressBar(8, 83, 166, 11, 7, 190);

  public GuiMatterCompressor(final MatterCompressorContainer container, final Inventory player_inventory, final Component title){
    super(183, 182, container, player_inventory, title, GuiReference.matter_compressor);
  }

  @Override
  protected final void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY){
    draw_background_texture(graphics);
    progress_bar.draw(graphics, this, ProgressBar.Direction.LEFT_TO_RIGHT, tile.getProgress(), RoundMode.Floor);
  }

  @Override
  protected final void renderLabels(GuiGraphics graphics, int mouseX, int mouseY){
    draw_title(graphics);
    draw_text_right(graphics, black_hole_text.getString()+":", 76, 27);
    // final int slash = font.width("/"); // 6
    // final int space = font.width(" "); // 4
    
    draw_text_left(graphics, matter_text.getString()+":", 6, 71);
    draw_text_right(graphics, tile.getMatter(), center_x - 7, 71);
    draw_text_left(graphics, "/ "+Config.max_matter.get(), center_x - 3, 71);
    draw_text_right(graphics, StringUtil.toPercentageString(tile.getProgress(), RoundMode.Floor), 71);
  }

}
