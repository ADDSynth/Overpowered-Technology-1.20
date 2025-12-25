package addsynth.overpoweredtechnology.machines.plasma_generator;

import javax.annotation.Nullable;
import addsynth.energy.lib.tiles.machines.MachineStatus;
import addsynth.energy.lib.tiles.machines.switchable.IAutoShutoff;
import addsynth.energy.lib.tiles.machines.switchable.TileStandardPassiveMachine;
import addsynth.overpoweredtechnology.config.MachineValues;
import addsynth.overpoweredtechnology.game.reference.OverpoweredItems;
import addsynth.overpoweredtechnology.registers.Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public final class TilePlasmaGenerator extends TileStandardPassiveMachine implements IAutoShutoff, MenuProvider {

  private boolean auto_shutoff = false;
  public static final int DEFAULT_OUTPUT_NUMBER = 1;
  private int output_number = DEFAULT_OUTPUT_NUMBER;

  public TilePlasmaGenerator(BlockPos position, BlockState blockstate){
    super(Tiles.PLASMA_GENERATOR.get(), position, blockstate, MachineValues.plasma_generator, 1);
  }

  @Override
  protected final void perform_work(){
    output_inventory.insertItem(0, new ItemStack(OverpoweredItems.plasma.get()), false);
    if(auto_shutoff){
      if(output_inventory.getStackInSlot(0).getCount() >= output_number){
        power_switch = false;
        turn_off();
      }
    }
  }

  @Override
  public final boolean canWork(){
    if(output_inventory.can_add(0, new ItemStack(OverpoweredItems.plasma.get()))){
      status = MachineStatus.GOOD;
      return true;
    }
    status = MachineStatus.OUTPUT_FULL;
    return false;
  }

  @Override
  public final void load(final CompoundTag nbt){
    super.load(nbt);
    auto_shutoff = nbt.getBoolean("Auto Shutoff");
    output_number = nbt.getInt("Output Threshold");
  }

  @Override
  protected final void saveAdditional(final CompoundTag nbt){
    super.saveAdditional(nbt);
    nbt.putBoolean("Auto Shutoff", auto_shutoff);
    nbt.putInt("Output Threshold", output_number);
  }

  @Override
  public final void toggle_auto_shutoff(){
    auto_shutoff = !auto_shutoff;
    changed = true;
  }

  public final void set_output_number(final int output_threshold){
    output_number = output_threshold;
    changed = true;
  }

  @Override
  public final boolean getAutoShutoff(){
    return auto_shutoff;
  }

  public final int get_output_number(){
    return output_number;
  }

  @Override
  @Nullable
  public AbstractContainerMenu createMenu(int id, Inventory player_inventory, Player player){
    return new ContainerPlasmaGenerator(id, player_inventory, this);
  }

  @Override
  public Component getDisplayName(){
    return getBlockState().getBlock().getName();
  }

}
