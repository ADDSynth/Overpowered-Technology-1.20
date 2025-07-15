package addsynth.overpoweredtechnology.blocks.dimension;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@Deprecated
public final class BlockAirNoDestroy extends AirBlock {

  public BlockAirNoDestroy(){
    super(BlockBehaviour.Properties.of().replaceable().noCollission().noLootTable().air());
    // MAYBE: setRegistryName(new ResourceLocation(OverpoweredTechnology.MOD_ID, "air"));
  }

  @Override
  public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player){
    if(world.isClientSide() == false){
      // world.setBlockState(pos, Init.custom_air_block.getDefaultState(), 2);
    }
  }

}
