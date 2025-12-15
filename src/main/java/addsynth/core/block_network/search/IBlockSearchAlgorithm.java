package addsynth.core.block_network.search;

import java.util.HashSet;
import addsynth.core.block_network.CustomSearch;
import addsynth.core.block_network.node.Node;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

@FunctionalInterface
public interface IBlockSearchAlgorithm {

  public HashSet<Node> find_blocks(BlockPos from, ServerLevel world, CustomSearch consumer);

}
