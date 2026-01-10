package addsynth.overpoweredtechnology.machines.laser.jobs;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import addsynth.core.util.world.WorldUtil;
import addsynth.overpoweredtechnology.OverpoweredTechnology;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/** This is the main class for the LaserJobs system. */
@EventBusSubscriber(modid = OverpoweredTechnology.MOD_ID, bus = Bus.FORGE)
public final class LaserJobs {

  static final int beam_size = 10;
  // Now accessed by each Level's thread, so it must be synchronized.
  private static final ConcurrentHashMap<ResourceLocation, LevelLaserJobs> laser_jobs = new ConcurrentHashMap<>(3);

  private LaserJobs(){
    // Stupid JAVA thing #7? Marking this class as static would solve this.
    throw new AssertionError("No instances for you!");
  }

  public static final void addNew(final ServerLevel world, final Collection<BlockPos> lasers, final int distance){
    laser_jobs.computeIfAbsent(world.dimension().location(), LevelLaserJobs::new).add(world, lasers, distance);
  }

  @SubscribeEvent
  public static final void tick(final LevelTickEvent event){
    WorldUtil.tickLevel(event, (MinecraftServer server, ServerLevel level) -> {
      server.getProfiler().push("laser_jobs");
      laser_jobs.computeIfAbsent(level.dimension().location(), LevelLaserJobs::new).tick(level);
      server.getProfiler().pop();
    });
  }

  public static final void load(final MinecraftServer server){
    for(final ServerLevel level : server.getAllLevels()){
      laser_jobs.put(level.dimension().location(), LevelLaserJobs.load(level));
    }
  }

}
