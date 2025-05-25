package addsynth.core.game.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public final class TestBlock extends Block {

  public TestBlock(){
    super(Block.Properties.of().sound(SoundType.STONE).mapColor(MapColor.SNOW).strength(0.2f, 6.0f));
  }

}
