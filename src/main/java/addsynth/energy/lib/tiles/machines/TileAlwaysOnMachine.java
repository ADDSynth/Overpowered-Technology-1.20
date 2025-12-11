package addsynth.energy.lib.tiles.machines;

import java.util.function.Predicate;
import addsynth.core.game.inventory.*;
import addsynth.core.game.inventory.machine.IMachineInventory;
import addsynth.core.game.inventory.machine.MachineInventory;
import addsynth.energy.lib.config.MachineData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Machines that are always running cannot be turned off. They switch to an
 *  Idle state when they can't do work. These machines don't have idle energy. */
public abstract class TileAlwaysOnMachine extends TileAbstractWorkMachine
  implements IInputInventory, IOutputInventory, IMachineInventory {

  protected final MachineInventory inventory;

  public TileAlwaysOnMachine(BlockEntityType type, BlockPos position, BlockState blockstate,
                             SlotData[] slots, int output_slots, MachineData data){
    super(type, position, blockstate, MachineState.IDLE, data);
    this.inventory = new MachineInventory(slots, output_slots);
  }

  public TileAlwaysOnMachine(BlockEntityType type, BlockPos position, BlockState blockstate,
                             int input_slots, Predicate<ItemStack> filter, int output_slots, MachineData data){
    super(type, position, blockstate, MachineState.IDLE, data);
    this.inventory = new MachineInventory(input_slots, filter, output_slots);
  }

  @Override
  public void serverTick(ServerLevel level, BlockState blockstate){
    machine_tick();
    if(inventory.tick()){
      changed = true;
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
  protected void machine_tick(){
    switch(state){
    case RUNNING:
      if(canFinishWork()){
        finishWork();
        if(can_work()){
          begin_work();
        }
        else{
          state = MachineState.IDLE;
        }
        changed = true;
      }
      machine_running();
      break;

    case IDLE:
      if(can_work()){
        state = MachineState.RUNNING;
        changed = true;
        begin_work();
      }
      break;
    
    default:
      state = MachineState.IDLE;
      changed = true;
    }
  }

  /** Override this to specify additional instructions while the machine is actively working on something.
   *  This is called every tick on the server side. There is no need to call the super method! */
  protected void machine_running(){}

  protected boolean can_work(){
    return inventory.can_work();
  }

  protected void begin_work(){
    inventory.begin_work();
  }

  protected boolean canFinishWork(){
    return energy.isFull();
  }

  protected void finishWork(){
    energy.setEmpty();
    inventory.finish_work();
  }

  @Override
  public void onInventoryChanged(){
    changed = true;
  }

  @Override
  public final int getJobs(){
    return inventory.getJobs();
  }

  @Override
  public void load(final CompoundTag nbt){
    super.load(nbt);
    inventory.loadFromNBT(nbt);
  }

  @Override
  protected void saveAdditional(final CompoundTag nbt){
    super.saveAdditional(nbt);
    inventory.saveToNBT(nbt);
  }

  @Override
  @NotNull
  public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side){
    if(remove == false){
      if(capability == ForgeCapabilities.ITEM_HANDLER){
        return InventoryUtil.getInventoryCapability(inventory.getInputInventory(), inventory.getOutputInventory(), side);
      }
      return super.getCapability(capability, side);
    }
    return LazyOptional.empty();
  }

  @Override
  public double getRequestedEnergy(){
    if(state == MachineState.RUNNING){
      return energy.getRequestedEnergy();
    }
    return 0;
  }

  @Override
  public void drop_inventory(){
    inventory.drop(worldPosition, level);
  }

  @Override
  public InputInventory getInputInventory(){
    return inventory.getInputInventory();
  }

  @Override
  public OutputInventory getOutputInventory(){
    return inventory.getOutputInventory();
  }

  @Override
  public final CommonInventory getWorkingInventory(){
    return inventory.getWorkingInventory();
  }
  
}
