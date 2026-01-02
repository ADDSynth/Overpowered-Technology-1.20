package addsynth.overpoweredtechnology.game;

import addsynth.overpoweredtechnology.OverpoweredTechnology;
import addsynth.overpoweredtechnology.machines.laser.jobs.LaserJobs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.event.server.ServerStartedEvent;

public final class OverpoweredSavedData extends SavedData {

  // TODO: World Save Data. This is great! I can use this to save the signal data so players HAVE to find it each world!

  private static OverpoweredSavedData overpowered_savedata;
  
  /** This should be called in the {@link ServerStartedEvent}. */
  public static final void load(final MinecraftServer server){
    @SuppressWarnings("resource")
    final ServerLevel overworld = server.overworld();
    if(overworld != null){
      overpowered_savedata = overworld.getDataStorage().computeIfAbsent(OverpoweredSavedData::load, OverpoweredSavedData::new, OverpoweredTechnology.MOD_ID);
    }
    LaserJobs.load(server);
  }

  /** And this should only be called on the server side! That should be obvious by now! */
  public static final void dataChanged(){
    if(overpowered_savedata != null){
      overpowered_savedata.setDirty(true);
    }
  }

  private static final OverpoweredSavedData load(CompoundTag nbt){
    final OverpoweredSavedData savedata = new OverpoweredSavedData();
    return savedata;
  }

  @Override
  public final CompoundTag save(CompoundTag nbt){
    return nbt;
  }

}
