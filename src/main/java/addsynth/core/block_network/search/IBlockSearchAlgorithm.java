package addsynth.core.block_network.search;

import java.util.HashSet;
import java.util.function.BiConsumer;
import addsynth.core.block_network.Node;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

@FunctionalInterface
public interface IBlockSearchAlgorithm {

  public HashSet<Node> find_blocks(BlockPos from, ServerLevel world, BiConsumer<Node, ServerLevel> consumer);

}
