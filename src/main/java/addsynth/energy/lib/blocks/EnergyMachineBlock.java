package addsynth.energy.lib.blocks;

import addsynth.core.block_network.BlockNetworkUtil;
import addsynth.energy.lib.energy_network.EnergyNetwork;
import addsynth.energy.lib.tiles.AbstractEnergyNetworkTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

/** Now that most machines can be a part of the Energy Network, they must also
 *  have BlockNetwork-logic. This block will handle that.
 */
public abstract class EnergyMachineBlock extends MachineBlock {

  /** Specify your own Block Properties. Required if block is transparent! */
  public EnergyMachineBlock(final Block.Properties properties){
    super(properties);
  }

  /** Standard constructor. SoundType = Metal, and standard block hardness. */
  public EnergyMachineBlock(final MapColor color){
    super(color);
  }

  @Override
  public final void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving){
    BlockNetworkUtil.onRemove(super::onRemove, AbstractEnergyNetworkTile.class, EnergyNetwork::new, state, world, pos, newState, isMoving);
  }

  @Override
  @SuppressWarnings("deprecation")
  public final void neighborChanged(BlockState state, Level world, BlockPos pos, Block blockIn, BlockPos neighbor, boolean isMoving){
    BlockNetworkUtil.neighbor_changed(world, pos, neighbor);
  }

}
