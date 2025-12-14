package addsynth.core.block_network.search;

import java.util.HashSet;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import addsynth.core.ADDSynthCore;
import addsynth.core.block_network.BlockNetwork;
import addsynth.core.block_network.IBlockNetworkUser;
import addsynth.core.block_network.node.Node;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

public final class StandardBlockSearch implements IBlockSearchAlgorithm {

  public final Predicate<Node> isValid;
  private final HashSet<BlockPos> searched = new HashSet<BlockPos>(1000);

  public StandardBlockSearch(Predicate<Node> is_valid){
    this.isValid = is_valid;
  }

  /** Creates a StandardBlockSearch object for use in BlockNetworks. */
  public static final <B extends BlockNetwork<T>, T extends BlockEntity & IBlockNetworkUser<B>> StandardBlockSearch create(final Class<T> tile_class){
    return new StandardBlockSearch(
      (Node node) -> {
        final BlockEntity tile = node.getTile();
        if(tile != null){
          if(!tile.isRemoved()){
            return tile_class.isInstance(tile);
          }
        }
        return false;
      }
    );
  }

  /** Standard search algorithm. Checks current block and adjacent blocks against the predicate you specify.
   *  If the predicate returns true, the {@link Node} is added to a list and then the list is returned.
   * @param from Starting Position. Predicate must return true otherwise an empty list is returned.
   * @param world
   */
  public final HashSet<Node> find_blocks(final BlockPos from, final ServerLevel world){
    return find_blocks(from, world, null);
  }

  /** Standard search algorithm. Checks current block and adjacent blocks against the predicate you specify.
   *  If the predicate returns true, the {@link Node} is added to a list and then the list is returned. This
   *  version has an additional Consumer argument which allows you to run additional code on all blocks searched.
   * @param from Starting Position. Predicate must return true otherwise an empty list is returned.
   * @param world
   * @param consumer Supply a function that takes a Node as an argument. Allows you to run additional code on all Nodes searched.
   */
  @Override
  public final HashSet<Node> find_blocks(final BlockPos from, final ServerLevel world, final BiConsumer<Node, ServerLevel> consumer){
    final HashSet<Node> list = new HashSet<>(100);
    try{
      searched.clear();
      searched.add(from);
      if(check(from, list, world, consumer)){
        search(from, list, world, consumer);
      }
    }
    catch(StackOverflowError e){
      ADDSynthCore.log.fatal("Search algorithm in "+StandardBlockSearch.class.getName()+" looped forever! We're sorry about that! "+
        "(someone's code is doing something they're not supposed to.)", e);
    }
    catch(Exception e){
      ADDSynthCore.log.fatal("Error in "+StandardBlockSearch.class.getSimpleName()+".search() algorithm.", e);
    }
    return list;
  }

  private final void search(BlockPos from, HashSet<Node> list, ServerLevel world, BiConsumer<Node, ServerLevel> consumer){
    BlockPos position;
    for(final Direction side : Direction.values()){
      position = from.relative(side);
      if(searched.contains(position) == false){
        searched.add(position);
        if(check(position, list, world, consumer)){
          search(position, list, world, consumer);
        }
      }
    }
  }

  private final boolean check(BlockPos position, HashSet<Node> list, ServerLevel world, BiConsumer<Node, ServerLevel> consumer){
    final Node node = new Node(position, world);
    if(consumer != null){
      consumer.accept(node, world);
    }
    if(isValid.test(node)){
      list.add(node);
      return true;
    }
    return false;
  }

}
