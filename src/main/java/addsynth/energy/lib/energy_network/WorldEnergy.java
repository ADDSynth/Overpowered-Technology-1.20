package addsynth.energy.lib.energy_network;

/* I can't believe ChatGPT (or specifically Copilot using GPT-5) actually solved my issue.
 * To fix, an energy network MUST consist of all machines connected, but FAIL if trying to go from machine to machine.
 * DELETE WorldEnergy once I have implemented this change.
import java.util.HashMap;
import java.util.HashSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;

public class WorldEnergy {

  private static final HashMap<ResourceLocation, HashSet<EnergyNetwork>> world_energy = new HashMap<>(3);

  public static final void addEnergyNetwork(final ServerLevel level, final EnergyNetwork network){
    final ResourceLocation dimension = level.dimension().location();
    world_energy.computeIfAbsent(dimension, (ResourceLocation) -> new HashSet<EnergyNetwork>());
    world_energy.get(dimension).add(network);
  }

  public static final void tickLevel(final TickEvent.LevelTickEvent event){
    if(event.side == LogicalSide.SERVER){
      if(event.phase == TickEvent.Phase.START){
        tick((ServerLevel)event.level);
      }
    }
  }

  private static final void tick(final ServerLevel level){
    final ResourceLocation dimension = level.dimension().location();
    @SuppressWarnings("resource")
    final MinecraftServer server = level.getServer();
    server.getProfiler().push("ADDSynth Energy Network Tick: "+dimension);
    
    final HashSet<EnergyNetwork> set = world_energy.get(dimension);
    if(set != null){
      
      // Step 1: Remove invalid networks
      set.removeIf((EnergyNetwork network) -> network.getCount() == 0);

      // Step 2: Compute network's Requested Energy.      
      for(EnergyNetwork network : set){
        
      }
    }
    server.getProfiler().pop();
  }

}
*/
