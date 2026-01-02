package addsynth.overpoweredtechnology.machines.laser.jobs;

import java.util.ArrayList;
import java.util.Collection;
import addsynth.overpoweredtechnology.machines.laser.cannon.AbstractLaserCannon;
import addsynth.overpoweredtechnology.machines.laser.cannon.LaserCannon;
import addsynth.overpoweredtechnology.machines.laser.machine.LaserNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

final class LaserJob {

  private final int distance;
  private final ArrayList<LaserBeamData> beams;
  private int life = 0;
  
  LaserJob(final ServerLevel world, final Collection<BlockPos> positions, final int distance){
    this.distance = distance;
    beams = new ArrayList<LaserBeamData>(positions.size());
    BlockState state;
    LaserCannon block;
    for(final BlockPos position : positions){
      state = world.getBlockState(position);
      if(state.getBlock() instanceof LaserCannon){
        block = (LaserCannon)state.getBlock();
        beams.add(new LaserBeamData(position, block.color, state.getValue(AbstractLaserCannon.FACING)));
      }
    }
  }

  LaserJob(final CompoundTag tag){
    distance = Mth.clamp(tag.getInt("distance"), 1, LaserNetwork.max_laser_distance);
    life = Math.max(tag.getInt("life"), 0);
    final ListTag beam_list = tag.getList("beams", Tag.TAG_COMPOUND);
    int i;
    final int length = beam_list.size();
    beams = new ArrayList<LaserBeamData>(length);
    for(i = 0; i < length; i++){
      beams.add(new LaserBeamData(beam_list.getCompound(i)));
    }
  }
  
  final void tick(final ServerLevel world){
    // tick all beams
    for(LaserBeamData beam : beams){
      beam.tick(world, life);
    }

    // increment life
    life++;
    
    // if we've reached end-of-life, stop incrementing begin position
    if(life == distance){
      for(LaserBeamData beam : beams){
        beam.move_forward = false;
      }
    }
    
    // delete any laser beams who's end position has reached the start position
    beams.removeIf((LaserBeamData b) -> b.delete);
  }

  final CompoundTag save(){
    final CompoundTag tag = new CompoundTag();
    tag.putInt("distance", distance);
    tag.putInt("life", life);
    final ListTag beam_list = new ListTag();
    for(LaserBeamData beam : beams){
      beam_list.add(beam.save());
    }
    tag.put("beams", beam_list);
    return tag;
  }

  final boolean shouldDelete(){
    return (beams == null ? true : beams.isEmpty()) || life >= distance + LaserJobs.beam_size;
  }

}
