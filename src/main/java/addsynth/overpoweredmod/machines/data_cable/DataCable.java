package addsynth.overpoweredmod.machines.data_cable;

import javax.annotation.Nullable;
import addsynth.core.block_network.BlockNetworkUtil;
import addsynth.core.util.block.BlockMatchList;
import addsynth.core.util.block.BlockShape;
import addsynth.energy.lib.blocks.Wire;
import addsynth.overpoweredmod.game.reference.Names;
import addsynth.overpoweredmod.registers.Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class DataCable extends Wire {

  private static final double min_wire_size =  5.5 / 16;
  private static final double max_wire_size = 10.5 / 16;

  /** Data Cable will attach to any blocks in this list. */
  public static final BlockMatchList valid_blocks = new BlockMatchList(
    Names.DATA_CABLE,
    Names.PORTAL_FRAME,
    Names.PORTAL_CONTROL_PANEL,
    Names.FUSION_CONTROL_UNIT,
    Names.FUSION_CONVERTER,
    Names.IRON_FRAME_BLOCK
  );

  public DataCable(){
    super(Block.Properties.of().sound(SoundType.WOOL).mapColor(MapColor.WOOL).strength(0.1f, 0.0f));
  }

  @Override
  protected VoxelShape[] makeShapes(){
    return BlockShape.create_six_sided_binary_voxel_shapes(min_wire_size, max_wire_size);
  }

  @Override
  protected final boolean[] get_valid_sides(final BlockGetter world, final BlockPos pos){
    final boolean[] valid_sides = new boolean[6];
    Block block;
    for(Direction side : Direction.values()){
      block = world.getBlockState(pos.relative(side)).getBlock();
      valid_sides[side.ordinal()] = valid_blocks.test(block);
    }
    return valid_sides;
  }

  @Override
  @Nullable
  public final BlockEntity newBlockEntity(BlockPos position, BlockState blockstate){
    return new TileDataCable(position, blockstate);
  }

  @Override
  @Nullable
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState blockstate, BlockEntityType<T> type){
    return standardTicker(world, type, Tiles.DATA_CABLE.get());
  }

  @Override
  @SuppressWarnings("deprecation")
  public final void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving){
    BlockNetworkUtil.onRemove(super::onRemove, TileDataCable.class, DataCableNetwork::new, state, world, pos, newState, isMoving);
  }

  @Override
  @SuppressWarnings("deprecation")
  public final void neighborChanged(BlockState state, Level world, BlockPos pos, Block blockIn, BlockPos neighbor, boolean isMoving){
    BlockNetworkUtil.neighbor_changed(world, pos, neighbor);
  }

}
