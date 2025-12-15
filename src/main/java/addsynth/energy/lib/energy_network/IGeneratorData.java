package addsynth.energy.lib.energy_network;

public interface IGeneratorData {

  public boolean hasAvailableEnergy();
  public long getTotalAvailableEnergy();
  public void extractEnergy(long transfer_energy);

}
