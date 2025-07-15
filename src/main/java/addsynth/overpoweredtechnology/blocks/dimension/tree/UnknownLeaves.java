package addsynth.overpoweredtechnology.blocks.dimension.tree;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

public final class UnknownLeaves extends Block {

  public UnknownLeaves(){
    super(Block.Properties.of().strength(0.2f, 0.0f).sound(SoundType.GRASS).noOcclusion());
  }

}
