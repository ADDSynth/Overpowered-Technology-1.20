package addsynth.core.block_network.search;

import java.util.HashSet;
import java.util.function.BiConsumer;
import addsynth.core.block_network.Node;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

@FunctionalInterface
public interface IBlockSearchAlgorithm {

  public HashSet<Node> find_blocks(BlockPos from, Level world, BiConsumer<Node, Level> consumer);

}
