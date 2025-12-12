package addsynth.overpoweredtechnology.machines.energy_extractor;

import javax.annotation.Nullable;
import addsynth.core.game.inventory.filter.BasicFilter;
import addsynth.energy.lib.tiles.energy.TileStandardGenerator;
import addsynth.overpoweredtechnology.config.MachineValues;
import addsynth.overpoweredtechnology.game.reference.OverpoweredItems;
import addsynth.overpoweredtechnology.registers.Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

public final class TileEnergyExtractor extends TileStandardGenerator implements MenuProvider {

  private static final BasicFilter input_filter = new BasicFilter(
    OverpoweredItems.energy_crystal_shards,
    OverpoweredItems.energy_crystal,
    OverpoweredItems.light_block
  );

  public TileEnergyExtractor(BlockPos position, BlockState blockstate){
    super(Tiles.ENERGY_EXTRACTOR.get(), position, blockstate, input_filter);
  }

  @Override
  public void serverTick(ServerLevel level, BlockState blockstate){
    if(energy.isEmpty()){
      if(input_inventory.isEmpty() == false){
        setGeneratorData();
        changed = true;
      }
    }
    if(energy.tick()){
      changed = true;
    }
    if(changed){
      update_data();
      changed = false;
    }
  }

  @Override
  protected final void setGeneratorData(){
    final Item item = input_inventory.extractItem(0, 1, false).getItem();
    if(item == OverpoweredItems.energy_crystal.get()){
      energy.setEnergyAndCapacity(MachineValues.energy_crystal_energy.get());
      energy.setMaxExtract(MachineValues.energy_crystal_max_extract.get());
    }
    if(item == OverpoweredItems.energy_crystal_shards.get()){
      energy.setEnergyAndCapacity(MachineValues.energy_crystal_shards_energy.get());
      energy.setMaxExtract(MachineValues.energy_crystal_shards_max_extract.get());
    }
    if(item == OverpoweredItems.light_block.get()){
      energy.setEnergyAndCapacity(MachineValues.light_block_energy.get());
      energy.setMaxExtract(MachineValues.light_block_max_extract.get());
    }
  }

  @Override
  @Nullable
  public AbstractContainerMenu createMenu(final int windowID, final Inventory player_inventory, final Player player){
    return new ContainerEnergyExtractor(windowID, player_inventory, this);
  }

  @Override
  public Component getDisplayName(){
    return getBlockState().getBlock().getName();
  }

}
