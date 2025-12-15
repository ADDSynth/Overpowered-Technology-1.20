package addsynth.energy.lib.tiles.machines;

import addsynth.energy.lib.main.IEnergyConsumer;
import net.minecraft.world.level.block.entity.BlockEntity;

/** With the way Energy Networks are set up now, nearly all machines are now part
 *  of the Energy Network, EXCEPT when the machine we're checking is already part
 *  of its own BlockNetwork. BlockNetwork tiles should implement this, so when
 *  the Energy Network updates, we can check for this, and you can return the first
 *  TileEntity of the BlockNetwork, and the TileEntity can still satisfy the
 *  conditions and add itself to the Energy Network.
 *  WARNING: On second thought, this is a bad idea. What happens when the energy
 *  network finds a TileEntity, but it points to another TileEntity, and then
 *  that TileEntity breaks away from its BlockNetwork? The TileEntity still exists,
 *  so the node will never get deleted, but the Energy Network thinks its connected
 *  to a closer TileEntity.
 */
public interface IVirtualMachine extends IEnergyConsumer {

  public <E extends BlockEntity & IEnergyConsumer> E getTile();

}
