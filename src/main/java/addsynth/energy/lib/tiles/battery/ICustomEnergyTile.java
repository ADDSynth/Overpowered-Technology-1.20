package addsynth.energy.lib.tiles.battery;

import addsynth.energy.lib.energy_network.EnergyTransferStage;
import addsynth.energy.lib.main.IEnergyUser;

public interface ICustomEnergyTile extends IEnergyUser {

  public double getAvailableEnergy(EnergyTransferStage stage);
  public double getRequestedEnergy(EnergyTransferStage stage);

}
