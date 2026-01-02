package addsynth.overpoweredtechnology.machines.laser.machine;

import java.util.ArrayList;
import java.util.HashSet;
import javax.annotation.Nullable;
import addsynth.core.block_network.BlockNetwork;
import addsynth.core.block_network.node.Node;
import addsynth.core.util.game.data.AdvancementUtil;
import addsynth.core.util.game.redstone.RedstoneDetector;
import addsynth.core.util.math.block.BlockArea;
import addsynth.core.util.math.block.DirectionUtil;
import addsynth.core.util.network.NetworkUtil;
import addsynth.core.util.player.PlayerUtil;
import addsynth.energy.lib.main.Energy;
import addsynth.energy.lib.main.Receiver;
import addsynth.overpoweredtechnology.assets.CustomAdvancements;
import addsynth.overpoweredtechnology.assets.CustomStats;
import addsynth.overpoweredtechnology.assets.Sounds;
import addsynth.overpoweredtechnology.config.MachineValues;
import addsynth.overpoweredtechnology.game.NetworkHandler;
import addsynth.overpoweredtechnology.machines.laser.cannon.AbstractLaserCannon;
import addsynth.overpoweredtechnology.machines.laser.cannon.LaserCannon;
import addsynth.overpoweredtechnology.machines.laser.jobs.LaserJobs;
import addsynth.overpoweredtechnology.machines.laser.network_messages.LaserClientSyncMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class LaserNetwork extends BlockNetwork<TileLaserHousing> {

  public static final int max_laser_distance = 1000;

  public boolean changed;
  private final HashSet<BlockPos> lasers = new HashSet<BlockPos>(27);
  private int number_of_lasers;
  private final RedstoneDetector redstone = new RedstoneDetector();
  private int laser_distance;
  public final Receiver energy = new Receiver(0, MachineValues.laser_max_receive.get()){
    @Override
    public boolean canReceive(){
      return super.canReceive() && running;
    }
  };
  public boolean running;
  public boolean auto_shutoff;

  public LaserNetwork(final BlockPos position){
    super(TileLaserHousing.class, position);
  }

  @Override
  protected void clear_custom_data(){
    lasers.clear();
  }

  @Override
  protected final void onUpdateNetworkFinished(final ServerLevel world){
    check_if_lasers_changed(world);
  }

  @Override
  protected final void customSearch(@Nullable final Node previous, final Node node, final ServerLevel world){
    final BlockEntity tile = node.getTile();
    if(tile != null){
      if(tile.getClass() == TileLaserHousing.class){
        
        final BlockPos tile_position = tile.getBlockPos();
        BlockPos position;
        for(Direction direction : Direction.values()){
          position = tile_position.relative(direction);
          check_and_add_LaserCannon(world, position, direction);
        }
      }
    }
  }

  /** Checks position if it is a valid LaserCannon, and adds it. */
  private final void check_and_add_LaserCannon(final ServerLevel world, final BlockPos position, final Direction direction){
    final BlockState state = world.getBlockState(position);
    if(state.getBlock() instanceof LaserCannon){
      if(state.getValue(AbstractLaserCannon.FACING) == direction){
        lasers.add(position);
      }
    }
  }

  @Override
  public void neighbor_was_changed(final ServerLevel world, final BlockPos current_position, final BlockPos position_of_neighbor){
    check_and_add_LaserCannon(world, position_of_neighbor, DirectionUtil.getDirection(current_position, position_of_neighbor));
    lasers.removeIf((BlockPos pos) -> world.getBlockState(pos).getBlock() instanceof LaserCannon == false);
    // if(world.getBlockState(position_of_neighbor).getBlock() instanceof LaserCannon == false){
    //   lasers.remove(position_of_neighbor);
    // }
    check_if_lasers_changed(world);
  }

  public final void load_data(Energy energy, boolean power_switch, int laser_distance, boolean auto_shutoff, RedstoneDetector redstone_state){
    this.energy.set(energy);
    this.running = power_switch;
    this.laser_distance = laser_distance;
    this.auto_shutoff = auto_shutoff;
    this.redstone.setFrom(redstone_state);
  }

  public final int getLaserDistance(){
    return laser_distance;
  }

  public final void setLaserDistance(int laser_distance){
    this.laser_distance = laser_distance;
    update_energy_requirements();
  }

  private final void check_if_lasers_changed(final ServerLevel world){
    if(lasers.size() != number_of_lasers){
      number_of_lasers = lasers.size();
      update_energy_requirements();
      updateClient(world);
    }
  }

  /**
   *  The energy requirements of the whole LaserHousing network is calculated dynamically depending on how
   *  many lasers there are and what distance the lasers have to travel. It is recalculated whenever one of
   *  these variables change.
   */
  private final void update_energy_requirements(){
    energy.setCapacity(
      (number_of_lasers * MachineValues.required_energy_per_laser.get()) +
      (number_of_lasers * laser_distance * MachineValues.required_energy_per_laser_distance.get())
    );
  }

  /**
   * Checks if any {@link TileLaserHousing} is powered with redstone and calls the
   * {@link #fire_lasers()} function, if certain conditions are met.
   */
  @Override
  protected final void tick(final ServerLevel world){
    if(energy.getMaxReceive() != MachineValues.laser_max_receive.get()){
      energy.setMaxReceive(MachineValues.laser_max_receive.get());
    }
    changed = redstone.update(world, blocks.getBlockPositions(), changed);
    if(redstone.onRisingEdge()){
      if(lasers.size() > 0 && laser_distance > 0){
        if(energy.isFull()){
          fire_lasers(world);
        }
      }
    }
    if(energy.tick()){
      changed = true;
    }
    if(changed){
      updateLaserNetwork();
      changed = false;
    }
  }

  /** updates server (needs to be saved to world data) */
  private final void updateLaserNetwork(){
    blocks.remove_invalid();
    blocks.forAllTileEntities((TileLaserHousing laser_housing) -> 
      laser_housing.setDataDirectlyFromNetwork(energy, laser_distance, running, auto_shutoff, redstone)
    );
  }
  
  public final void updateClient(final Level world){ // (client can determine the information)
    final LaserClientSyncMessage message = new LaserClientSyncMessage(blocks.getBlockPositions(), number_of_lasers);
    NetworkUtil.send_to_clients_in_world(NetworkHandler.INSTANCE, world, message);
  }

  private final void fire_lasers(final ServerLevel world){
    blocks.remove_invalid();
    
    LaserJobs.addNew(world, lasers, laser_distance);
    playSound(world);
    awardPlayers(world);
    
    this.energy.subtract_capacity();
    if(auto_shutoff){
      running = false;
    }
    changed = true;
  }

  /**Loops through all players on the server and sends a {@link ClientboundSoundPacket} to players
   * in the same dimension. Loops through all blocks and finds the block closest to the player
   * and uses that as the SoundEvent coordinates.
   * @see ServerLevel#playSound(net.minecraft.world.entity.player.Player, double, double, double, net.minecraft.sounds.SoundEvent, SoundSource, float, float)
   * @see PlayerList#broadcast(net.minecraft.world.entity.player.Player, double, double, double, double, ResourceKey, net.minecraft.network.protocol.Packet)
   */
  @SuppressWarnings("resource")
  private final void playSound(final ServerLevel world){
    final MinecraftServer server = world.getServer();
    final ResourceKey<Level> dimension = world.dimension();
    final ArrayList<BlockPos> positions = blocks.getBlockPositions();
    BlockPos closest_position;
    double x;
    double y;
    double z;
    final long seed = world.random.nextLong();
    for(final ServerPlayer player : server.getPlayerList().getPlayers()){
      if(player.serverLevel().dimension() == dimension){
        closest_position = BlockArea.getClosest(positions, player);
        x = closest_position.getX() + 0.5;
        y = closest_position.getY() + 0.5;
        z = closest_position.getZ() + 0.5;
        // TODO: Vanilla function PlayerList.broadcast does some additional calculation to only send messages close to the player.
        //       I need to run some tests to see exactly what it does, but I don't have time for that now.
        player.connection.send(new ClientboundSoundPacket(Sounds.laser_fire_sound.getHolder().get(), SoundSource.BLOCKS, x, y, z, 2.0f, 1.0f, seed));
      }
    }
  }

  private final void awardPlayers(final Level world){
    final ArrayList<ServerPlayer> players = new ArrayList<>();
    
    blocks.forAllTileEntities((TileLaserHousing tile) -> {
      // only count each player once, increment Laser Fired stat of each player
      final String player_name = tile.getLastUsedBy();
      final ServerPlayer player = PlayerUtil.getPlayer(world, player_name);
      if(player != null){
        if(players.contains(player) == false){
          players.add(player);
        }
      }
    });
    
    for(final ServerPlayer player : players){
      AdvancementUtil.grantAdvancement(player, CustomAdvancements.FIRE_LASER);
      if(laser_distance >= LaserNetwork.max_laser_distance){
        AdvancementUtil.grantAdvancement(player, CustomAdvancements.FIRE_MAXIMUM_DISTANCE);
      }
      player.awardStat(CustomStats.LASERS_FIRED);
    }
  }

}
