package addsynth.overpoweredtechnology.blocks.dimension.tree;

import addsynth.core.block_network.node.Node;
import addsynth.core.util.block.BlockSearch;
import addsynth.core.util.world.WorldUtil;
import addsynth.overpoweredtechnology.game.reference.OverpoweredBlocks;
import addsynth.overpoweredtechnology.game.reference.OverpoweredItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public final class UnknownWood extends Block {

  public UnknownWood(){
    super(Block.Properties.of().sound(SoundType.WOOD).strength(2.0f));
  }

  // TEST: I think I prefer this one because it works if the player is in Creative Mode as well.
  @Override
  public void playerWillDestroy(Level world, BlockPos position, BlockState state, Player player){
    super.playerWillDestroy(world, position, state, player);
    if(world.isClientSide == false){
      BlockSearch.forEachAdjacent(position, world, (Node node) -> {
        if(node.block == OverpoweredBlocks.unknown_wood.get() ||
           node.block == OverpoweredBlocks.unknown_leaves.get()){
          world.removeBlock(node.position, false);
          return true;
        }
        return false;
      });
      WorldUtil.spawnItemStack(world, position, new ItemStack(OverpoweredItems.void_crystal.get(), 1));
    }
  }

}
