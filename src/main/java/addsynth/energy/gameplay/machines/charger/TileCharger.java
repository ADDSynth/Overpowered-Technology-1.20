package addsynth.energy.gameplay.machines.charger;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import addsynth.energy.lib.main.Receiver;
import addsynth.energy.lib.tiles.machines.TileSingleItemMachine;
import addsynth.energy.registers.Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

public final class TileCharger extends TileSingleItemMachine implements MenuProvider {

  public static final Predicate<ItemStack> filter = (ItemStack stack) -> {
    final LazyOptional<IEnergyStorage> optional = stack.getCapability(ForgeCapabilities.ENERGY);
    return optional.isPresent();
  };

  public TileCharger(BlockPos position, BlockState blockstate){
    super(Tiles.CHARGER.get(), position, blockstate, filter, new Receiver(1));
  }

  @Override
  protected final void doWork(){
    if(energy.isFull()){
      final ItemStack stack = getItemStack();
      final LazyOptional<IEnergyStorage> optional = stack.getCapability(ForgeCapabilities.ENERGY);
      final IEnergyStorage stack_energy = optional.orElse(null);
      if(stack_energy != null){
        stack_energy.receiveEnergy(1, false);
      }
      energy.setEmpty();
    }
  }

  @Override
  protected final boolean canFinishWork(){
    if(output_inventory.isEmpty()){
      final ItemStack stack = getItemStack();
      final LazyOptional<IEnergyStorage> optional = stack.getCapability(ForgeCapabilities.ENERGY);
      final IEnergyStorage stack_energy = optional.orElse(null);
      if(stack_energy != null){
        return stack_energy.getEnergyStored() == stack_energy.getMaxEnergyStored();
      }
      return true;
    }
    return false;
  }

  @Override
  protected final void finishWork(){
    final ItemStack stack = input_inventory.extractItemStack(0);
    output_inventory.add(0, stack);
  }

  @Override
  @Nullable
  public final AbstractContainerMenu createMenu(int containerID, Inventory player_inventory, Player player){
    return new ChargerContainer(containerID, player_inventory, this);
  }

  @Override
  public final Component getDisplayName(){
    return getBlockState().getBlock().getName();
  }

}
