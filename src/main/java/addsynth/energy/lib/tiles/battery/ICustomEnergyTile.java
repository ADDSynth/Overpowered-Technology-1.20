package addsynth.energy.lib.tiles.battery;

import addsynth.energy.lib.energy_network.EnergyTransferStage;
import addsynth.energy.lib.main.IBattery;

public interface ICustomEnergyTile extends IBattery {

  public void extractEnergy(double energy, EnergyTransferStage stage);
  public void receiveEnergy(double energy, EnergyTransferStage stage);

}
