package addsynth.energy.gameplay.machines.solar_panel;

import addsynth.core.game.tiles.TileBase;
import addsynth.core.gameplay.reference.ADDSynthCoreText;
import addsynth.core.util.game.MinecraftUtility;
import addsynth.core.util.game.tileentity.ITickingTileEntity;
import addsynth.core.util.network.NetworkUtil;
import addsynth.core.util.time.WorldTime;
import addsynth.energy.gameplay.NetworkHandler;
import addsynth.energy.lib.main.Energy;
import addsynth.energy.lib.main.Generator;
import addsynth.energy.lib.main.IEnergyGenerator;
import addsynth.energy.registers.Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;

public class SolarPanelControllerTile extends TileBase implements IEnergyGenerator, ITickingTileEntity {

  private SolarPanelStatus status;
  private final Generator energy = new Generator();
  private int daytime;
  private double total_energy;
  private double phase;
  private int panel_count;
  private int blocked_count;
  private double theoretical_energy;

  private boolean found;
  private BlockPos adjacent;
  private SolarPanelTile solar_panel;
  private SolarPanelNetwork network;
  private SolarPanelData data;

  public SolarPanelControllerTile(BlockPos position, BlockState blockstate){
    super(Tiles.SOLAR_PANEL_CONTROLLER.get(), position, blockstate);
  }

  @Override
  public void onLoad(){
    final Level level = this.level;
    if(level != null){
      if(!level.isClientSide){
        final DimensionType dimension = level.dimensionType();
        if(dimension.hasCeiling() || !dimension.hasSkyLight()){
          status = SolarPanelStatus.DIMENSION_HAS_NO_LIGHT;
        }
        data = new SolarPanelData(worldPosition);
      }
    }
  }

  @Override
  public void serverTick(ServerLevel level, BlockState blockstate){
    daytime = (int)(level.getDayTime() % WorldTime.minecraft_day_in_ticks);
    phase = (double)daytime / WorldTime.minecraft_day_in_ticks;
    if(status != SolarPanelStatus.DIMENSION_HAS_NO_LIGHT){
      found = false;
      total_energy = 0;
      panel_count = 0;
      blocked_count = 0;
      theoretical_energy = 0;
      for(Direction direction : Direction.values()){
        adjacent = worldPosition.relative(direction);
        solar_panel = MinecraftUtility.getTileEntity(adjacent, level, SolarPanelTile.class);
        if(solar_panel != null){
          found = true;
          network = solar_panel.getBlockNetwork();
          if(network != null){
            total_energy += network.getEnergy();
            panel_count += network.getCount();
            blocked_count += network.getBlockedCount();
            theoretical_energy += network.getTheoreticalEnergy();
          }
        }
      }
      if(!found){
        status = SolarPanelStatus.NOT_CONNECTED;
      }
      else if(phase >= 0.5){
        status = SolarPanelStatus.DARKNESS;
      }
      else if(blocked_count > 0){
        status = SolarPanelStatus.BLOCKED;
      }
      else if(level.isThundering()){
        status = SolarPanelStatus.THUNDERING;
      }
      else{
        status = SolarPanelStatus.WORKING;
      }
    }
    energy.setEnergyAndCapacity(total_energy);
    energy.setMaxExtract(total_energy);
    energy.updateEnergyIO();
    data.set(status, daytime, phase, total_energy, panel_count, blocked_count, theoretical_energy);
    NetworkUtil.send_to_TileEntity(NetworkHandler.INSTANCE, this, data);
  }

  // Client methods;
  public final void setFromServer(SolarPanelData data){
    status = data.status;
    daytime = data.daytime;
    phase = data.phase;
    total_energy = data.energy;
    panel_count = data.panel_count;
    blocked_count = data.blocked_count;
    theoretical_energy = data.theoretical_energy;
  }
  public final Component getStatusMessage(){ return status != null ? status.getMessage() : ADDSynthCoreText.null_error; }
  public final double getEnergyValue(){ return total_energy; }
  public final double getPhase(){ return phase; }
  public final int getTicks(){ return daytime; }
  public final int getSolarPanelCount(){ return panel_count; }
  public final int getBlockedCount(){ return blocked_count; }
  public final double getEfficiency(){
    return theoretical_energy > 0 ? total_energy / theoretical_energy * 100 : 0;
  }

  @Override
  public Energy getEnergy(){
    return energy;
  }

}
