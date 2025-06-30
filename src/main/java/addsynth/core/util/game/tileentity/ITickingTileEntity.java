package addsynth.core.util.game.tileentity;

import net.minecraft.world.level.Level;

public interface ITickingTileEntity {

  /** No need to check if this is run on the server side.
   *  This is gaurenteed to run only on the server. */
  public void serverTick(Level level);

}
