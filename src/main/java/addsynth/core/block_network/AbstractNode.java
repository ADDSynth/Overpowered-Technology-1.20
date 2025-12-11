package addsynth.core.block_network;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import addsynth.core.util.java.StringUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class AbstractNode<T extends BlockEntity> {

  public final BlockPos position;
  public final Block block;
  protected final T tile;

  public AbstractNode(@Nonnull final T tile){
    this.position = tile.getBlockPos();
    this.block = tile.getBlockState().getBlock();
    this.tile = tile;
  }

  public AbstractNode(final BlockPos position, final Block block, final T tile){
    this.position = position;
    this.block = block;
    this.tile = tile;
  }

  public boolean isInvalid(){
    if(block == null || position == null){
      return true;
    }
    return tile != null ? (tile.isRemoved() || !tile.getBlockPos().equals(position)) : false;
  }

  @Nullable
  public T getTile(){
    return tile;
  }

  @Override
  public String toString(){
    if(tile == null){
      return StringUtil.build("Node{Position: ", StringUtil.print(position), ", Block: ", StringUtil.getName(block), "}");
    }
    return StringUtil.build("Node{TileEntity: ", tile.getClass().getSimpleName(), ", Position: ", StringUtil.print(position), "}");
  }

  @Override
  public int hashCode(){
    return position.hashCode();
  }

  @Override
  public boolean equals(final Object obj){
    return obj instanceof Node ? position.equals(((Node)obj).position) : false;
  }

}
