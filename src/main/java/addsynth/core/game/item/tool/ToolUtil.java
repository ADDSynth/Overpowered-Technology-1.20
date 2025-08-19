package addsynth.core.game.item.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public final class ToolUtil {

  /** @see net.minecraft.world.item.DiggerItem#mineBlock */
  public static boolean InstaMine(final Level level, final BlockState blockstate, final BlockPos position){
    return !level.isClientSide && blockstate.getDestroySpeed(level, position) != 0.0f;
  }

}
