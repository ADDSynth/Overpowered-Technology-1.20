package addsynth.overpoweredtechnology.machines.portal.frame;

import javax.annotation.Nullable;
import addsynth.core.game.inventory.SlotData;
import addsynth.core.game.inventory.filter.TagFilter;
import addsynth.core.game.tiles.TileStorageMachine;
import addsynth.material.util.MaterialTag;
import addsynth.overpoweredtechnology.game.tags.OverpoweredItemTags;
import addsynth.overpoweredtechnology.registers.Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

public final class TilePortalFrame extends TileStorageMachine implements MenuProvider {

  private static final TagFilter filter = new TagFilter(OverpoweredItemTags.portal_fuel);
  private static final SlotData[] slot_data = {new SlotData(filter, 1)};

  public TilePortalFrame(BlockPos position, BlockState blockstate){
    super(Tiles.PORTAL_FRAME.get(), position, blockstate, slot_data);
  }

  public final void check_item(final boolean[] items){
    final ItemStack stack = input_inventory.getStackInSlot(0);
    if(!stack.isEmpty()){
      final Item item = stack.getItem();
      final ITagManager<Item> tag_manager = ForgeRegistries.ITEMS.tags();
      if(tag_manager != null){
             if(tag_manager.getTag(MaterialTag.RUBY.BLOCKS           ).contains(item)){ items[0] = true; }
        else if(tag_manager.getTag(MaterialTag.TOPAZ.BLOCKS          ).contains(item)){ items[1] = true; }
        else if(tag_manager.getTag(MaterialTag.CITRINE.BLOCKS        ).contains(item)){ items[2] = true; }
        else if(tag_manager.getTag(Tags.Items.STORAGE_BLOCKS_EMERALD ).contains(item)){ items[3] = true; }
        else if(tag_manager.getTag(Tags.Items.STORAGE_BLOCKS_DIAMOND ).contains(item)){ items[4] = true; }
        else if(tag_manager.getTag(MaterialTag.SAPPHIRE.BLOCKS       ).contains(item)){ items[5] = true; }
        else if(tag_manager.getTag(Tags.Items.STORAGE_BLOCKS_AMETHYST).contains(item)){ items[6] = true; }
        else if(tag_manager.getTag(Tags.Items.STORAGE_BLOCKS_QUARTZ  ).contains(item)){ items[7] = true; }
      }
    }
  }

  @Override
  @Nullable
  public AbstractContainerMenu createMenu(int id, Inventory player_inventory, Player player){
    return new ContainerPortalFrame(id, player_inventory, this);
  }

  @Override
  public Component getDisplayName(){
    return getBlockState().getBlock().getName();
  }

}
