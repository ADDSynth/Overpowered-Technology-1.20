package addsynth.overpoweredtechnology.machines.advanced_gem_converter;

import java.util.List;
import javax.annotation.Nullable;
import addsynth.core.util.game.MinecraftUtility;
import addsynth.energy.gameplay.reference.EnergyText;
import addsynth.energy.lib.blocks.EnergyMachineBlock;
import addsynth.overpoweredtechnology.registers.Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class AdvancedGemConverterBlock extends EnergyMachineBlock {

  public AdvancedGemConverterBlock(){
    super(MapColor.SNOW);
  }

  @Override
  public final void appendHoverText(ItemStack itemstack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag){
    tooltip.add(EnergyText.class_5_machine);
  }

  @Override
  @Nullable
  public final BlockEntity newBlockEntity(BlockPos position, BlockState blockstate){
    return new TileAdvancedGemConverter(position, blockstate);
  }

  @Override
  @Nullable
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockstate, BlockEntityType<T> blockEntityType){
    return standardTicker(level, blockEntityType, Tiles.ADVANCED_GEM_CONVERTER.get());
  }

  @Override
  @SuppressWarnings("deprecation")
  public final InteractionResult use(BlockState blockstate, Level level, BlockPos position, Player player, InteractionHand hand, BlockHitResult hit_result){
    if(!level.isClientSide){
      final TileAdvancedGemConverter tile = MinecraftUtility.getTileEntity(position, level, TileAdvancedGemConverter.class);
      if(tile != null){
        tile.sendClientSync();
        NetworkHooks.openScreen((ServerPlayer)player, tile, position);
      }
    }
    return InteractionResult.SUCCESS;
  }

}
