package addsynth.overpoweredtechnology.machines.laser.jobs;

import addsynth.overpoweredtechnology.game.core.Laser;
import addsynth.overpoweredtechnology.machines.laser.beam.LaserBeam;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

final class LaserBeamData {

  private final int color;
  boolean move_forward = true;
  private final Direction direction;
  private MutableBlockPos position;
  private MutableBlockPos end_position;
  boolean delete = false;
  
  LaserBeamData(BlockPos position, int color, Direction direction){
    this.color = color;
    // passed in position is the position of the laser.
    // We must increment the position towards the direction the laser is facing.
    final int x = position.getX() + direction.getStepX();
    final int y = position.getY() + direction.getStepY();
    final int z = position.getZ() + direction.getStepZ();
    this.position = new MutableBlockPos(x, y, z);
     end_position = new MutableBlockPos(x, y, z);
    this.direction = direction;
  }
  
  LaserBeamData(CompoundTag tag){
    color        = Math.max(tag.getInt("color"), 0) % Laser.values().length;
    move_forward = tag.getBoolean("move_forward");
    direction    = Direction.from3DDataValue(tag.getInt("direction"));
    position     = new MutableBlockPos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z"));
    end_position = new MutableBlockPos(tag.getInt("tail_x"), tag.getInt("tail_y"), tag.getInt("tail_z"));
  }
  
  final void tick(final ServerLevel world, final int life){
    Block block;
    // if we can move forward
    if(move_forward){
      // check if next block can be destroyed
      block = world.getBlockState(position).getBlock();
      if(block == Blocks.BEDROCK){
        // if we can't destroy next block, stop moving forward
        move_forward = false;
      }
      else{
        // destroy block
        world.destroyBlock(position, true);
        // put a laser beam there
        world.setBlock(position, Laser.index[color].beam.get().defaultBlockState(), Block.UPDATE_CLIENTS);
      }
      // increment position
      position.move(direction);
    }
    
    // erase end laser beam, if it exists
    if(delete == false){
      if(life >= LaserJobs.beam_size){
        block = world.getBlockState(end_position).getBlock();
        if(block instanceof LaserBeam){
          world.removeBlock(end_position, false);
          // NOTE: Error exists: If you shoot 2 laser beams into each other, it won't look right
          //       when one deletes the other. But this is minor and can be fixed later.
        }
        // increment position
        end_position.move(direction);
        // mark as delete if end position has reached the start position
        delete = end_position.equals(position);
      }
    }
  }
  
  final CompoundTag save(){
    final CompoundTag tag = new CompoundTag();
    tag.putInt("color", color);
    tag.putInt("direction", direction.get3DDataValue());
    tag.putBoolean("move_forward", move_forward);
    // tag.putLong("position", position.asLong());
    // tag.putLong("end_position", end_position.asLong());
    tag.putInt("x", position.getX());
    tag.putInt("y", position.getY());
    tag.putInt("z", position.getZ());
    tag.putInt("tail_x", end_position.getX());
    tag.putInt("tail_y", end_position.getY());
    tag.putInt("tail_z", end_position.getZ());
    return tag;
  }

}
