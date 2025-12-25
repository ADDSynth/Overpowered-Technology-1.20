package addsynth.overpoweredtechnology.machines.crystal_matter_generator;

import java.util.Random;
import javax.annotation.Nullable;
import addsynth.energy.lib.tiles.machines.switchable.TileStandardPassiveMachine;
import addsynth.overpoweredtechnology.config.MachineValues;
import addsynth.overpoweredtechnology.game.core.Gems;
import addsynth.overpoweredtechnology.registers.Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public final class TileCrystalMatterGenerator extends TileStandardPassiveMachine implements MenuProvider {

  private final Random random = new Random();

  public TileCrystalMatterGenerator(BlockPos position, BlockState blockstate){
    super(Tiles.CRYSTAL_MATTER_REPLICATOR.get(), position, blockstate, MachineValues.crystal_matter_generator, 8);
  }

  @Override
  protected final void perform_work(){
    final int first_slot = random.nextInt(8);
    int slot = first_slot;
    ItemStack gem = Gems.getGem(slot);
    while(!output_inventory.can_add(slot, gem)){
      slot = (slot+1) % 8;
      gem = Gems.getGem(slot);
      if(slot == first_slot){
        return;
      }
    }
    output_inventory.insertItem(slot, gem, false);
  }

  @Override
  protected final boolean canWork(){
    int i;
    for(i = 0; i < 8; i++){
      if(output_inventory.getStackInSlot(i).getCount() < 64){
        return true;
      }
    }
    return false;
  }

  @Override
  @Nullable
  public AbstractContainerMenu createMenu(int id, Inventory player_inventory, Player player){
    return new ContainerCrystalGenerator(id, player_inventory, this);
  }

  @Override
  public Component getDisplayName(){
    return getBlockState().getBlock().getName();
  }

}
