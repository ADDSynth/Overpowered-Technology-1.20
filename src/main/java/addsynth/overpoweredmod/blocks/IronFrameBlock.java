package addsynth.overpoweredmod.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public final class IronFrameBlock extends Block {

  public IronFrameBlock(){
    super(Block.Properties.of().sound(SoundType.METAL).mapColor(MapColor.WOOL).strength(3.5f, 300.0f));
  }

}
