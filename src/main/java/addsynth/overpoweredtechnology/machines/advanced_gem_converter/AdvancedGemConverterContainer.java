package addsynth.overpoweredtechnology.machines.advanced_gem_converter;

import addsynth.core.container.TileEntityContainer;
import addsynth.core.container.slots.InputSlot;
import addsynth.core.container.slots.OutputSlot;
import addsynth.overpoweredtechnology.registers.Containers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class AdvancedGemConverterContainer extends TileEntityContainer<TileAdvancedGemConverter> {

  public AdvancedGemConverterContainer(final int id, final Inventory player_inventory, final TileAdvancedGemConverter tile){
    super(Containers.ADVANCED_GEM_CONVERTER.get(), id, player_inventory, tile);
    common_setup(player_inventory);
  }

  public AdvancedGemConverterContainer(final int id, final Inventory player_inventory, final FriendlyByteBuf buf){
    super(Containers.ADVANCED_GEM_CONVERTER.get(), id, player_inventory, buf);
    common_setup(player_inventory);
  }

  private final void common_setup(final Inventory player_inventory){
    make_player_inventory(player_inventory, 11, 112);
    addSlot(new InputSlot(tile, 0, 17, 32));
    addInputSlots(tile, 1, 15, 60, 4, 2);
    addSlot(new OutputSlot(tile, 0, 154, 64));
  }

}
