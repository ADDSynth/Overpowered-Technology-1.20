package addsynth.overpoweredmod.machines.laser.beam;

import addsynth.overpoweredmod.assets.DamageSources;
import addsynth.overpoweredmod.assets.DamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class LaserBeam extends Block {

  public LaserBeam(){
    super(Block.Properties.of().mapColor(MapColor.FIRE).noCollission().lightLevel((BlockState state) -> 15));
  }

  @Override
  @SuppressWarnings("deprecation")
  public final VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context){
    return Shapes.empty();
  }

  // You cannot set this block isBurning(true) because this will also set fire to any
  //   item Entities that fall in it, negating the purpose of having a mining laser.
  //   also, that only does 1 damage at a time.
  @Override
  @SuppressWarnings("deprecation")
  public final void entityInside(final BlockState state, final Level world, final BlockPos pos, final Entity entity){
    if(entity instanceof ItemEntity == false){
      if(!entity.fireImmune()){
        // See: Entity.lavaHurt();
        entity.hurt(DamageSources.get(world, DamageTypes.LASER), 4); // keep this the same as Lava damage
        entity.setSecondsOnFire(15);
      }
    }
  }

  @Override
  @SuppressWarnings("deprecation")
  public final boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side){
    return adjacentBlockState.getBlock() instanceof LaserBeam ? true : super.skipRendering(state, adjacentBlockState, side);
  }

}
