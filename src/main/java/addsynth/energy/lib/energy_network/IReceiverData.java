package addsynth.energy.lib.energy_network;

public interface IReceiverData {

  public boolean hasRequestedEnergy();
  public long getTotalRequestedEnergy();
  public void receiveEnergy(long transfer_energy);

}
