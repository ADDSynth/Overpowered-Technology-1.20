package addsynth.energy.lib.tiles;

import addsynth.core.game.tiles.TileBase;
import addsynth.core.util.game.tileentity.ITickingTileEntity;
import addsynth.energy.lib.main.Energy;
import addsynth.energy.lib.main.IEnergyUser;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractEnergyTile extends TileBase implements IEnergyUser, ITickingTileEntity  {

  protected final Energy energy;

  public AbstractEnergyTile(BlockEntityType type, BlockPos position, BlockState blockstate, Energy energy){
    super(type, position, blockstate);
    this.energy = energy;
  }

  @Override
  public void load(final CompoundTag nbt){
    super.load(nbt);
    if(energy != null){ energy.loadFromNBT(nbt);}
  }

  @Override
  protected void saveAdditional(final CompoundTag nbt){
    super.saveAdditional(nbt);
    if(energy != null){ energy.saveToNBT(nbt);}
  }
  
  @Override
  public Energy getEnergy(){
    return energy;
  }

}
