package addsynth.energy.gameplay.machines.circuit_fabricator;

import addsynth.core.util.network.TileEntityNetworkMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public final class ChangeCircuitFabricatorRecipe extends TileEntityNetworkMessage<TileCircuitFabricator> {

  private final String recipe_output;

  public ChangeCircuitFabricatorRecipe(final BlockPos position, final String recipe_output){
    super(position, TileCircuitFabricator.class);
    this.recipe_output = recipe_output;
  }

  @Override
  public final void encode(final FriendlyByteBuf buf){
    buf.writeBlockPos(position);
    buf.writeUtf(recipe_output);
  }

  public static final ChangeCircuitFabricatorRecipe decode(final FriendlyByteBuf buf){
    return new ChangeCircuitFabricatorRecipe(buf.readBlockPos(), buf.readUtf());
  }

  @Override
  protected final void handle(final ServerLevel level, final ServerPlayer player, final TileCircuitFabricator tile){
    tile.change_recipe(recipe_output);
    tile.ejectInvalidItems(player);
  }

}
