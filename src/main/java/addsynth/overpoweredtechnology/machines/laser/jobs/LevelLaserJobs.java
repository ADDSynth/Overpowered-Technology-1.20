package addsynth.overpoweredtechnology.machines.laser.jobs;

import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

/** Class that manages LaserJobs per-world. */
final class LevelLaserJobs extends SavedData {

  private static final String saveKey = "Laser Jobs";
  /** List of LaserJobs in this Level. */
  private final ArrayList<LaserJob> jobs;

  /** Create a new LevelLaserJobs if CompoundTag does not exist. */
  private LevelLaserJobs(){
    jobs = new ArrayList<>();
  }

  LevelLaserJobs(net.minecraft.resources.ResourceLocation location){
    jobs = new ArrayList<>();
  }

  /** Load LevelLaserJobs from CompoundTag. */
  private LevelLaserJobs(final CompoundTag tag){
    final ListTag laser_tag = tag.getList(saveKey, Tag.TAG_COMPOUND);
    final int size = laser_tag.size();
    jobs = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      jobs.add(new LaserJob(laser_tag.getCompound(i)));
    }
  }

  /** Main load function. Loads if CompoundTag exists, creates a new LevelLaserJobs if it doesn't. */
  static final LevelLaserJobs load(final ServerLevel level){
    return level.getDataStorage().computeIfAbsent(LevelLaserJobs::new, LevelLaserJobs::new, saveKey);
  }

  final void add(final ServerLevel level, final Collection<BlockPos> lasers, final int distance){
    jobs.add(new LaserJob(level, lasers, distance));
    setDirty();
  }

  /** Tick LevelLaserJobs. */
  final void tick(final ServerLevel level){
    if(jobs.size() > 0){
      for(final LaserJob job : jobs){
        job.tick(level);
      }
      jobs.removeIf((LaserJob job) -> job.shouldDelete());
      setDirty();
    }
  }

  /** Save LevelLaserJobs. */
  @Override
  public final CompoundTag save(final CompoundTag nbt){
    final ListTag laser_tag = new ListTag();
    for(LaserJob job : jobs){
      laser_tag.add(job.save());
    }
    nbt.put(saveKey, laser_tag);
    return nbt;
  }

}
