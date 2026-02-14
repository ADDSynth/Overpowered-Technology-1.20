package addsynth.overpoweredtechnology.game.core;

import addsynth.material.reference.Material;
import addsynth.material.util.MaterialTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

public final class Gems {

  public static final ItemStack getGem(final DeviceColor device_color){
    return getGem(device_color.index);
  }

  public static final ItemStack getGem(final int gem_id){
    return switch (gem_id) {
      case 0 -> new ItemStack(Items.QUARTZ);
      case 1 -> new ItemStack(Material.RUBY.gem.get());
      case 2 -> new ItemStack(Material.TOPAZ.gem.get());
      case 3 -> new ItemStack(Material.CITRINE.gem.get());
      case 4 -> new ItemStack(Items.EMERALD);
      case 5 -> new ItemStack(Items.DIAMOND);
      case 6 -> new ItemStack(Material.SAPPHIRE.gem.get());
      case 7 -> new ItemStack(Items.AMETHYST_SHARD);
      default -> ItemStack.EMPTY;
    };
  }

  public static final int getGemIndex(final ItemStack gem){
    return getGemIndex(gem.getItem());
  }

  public static final int getGemIndex(final Item gem){
    final ITagManager<Item> tag_manager = ForgeRegistries.ITEMS.tags();
    if(tag_manager != null){
      if(tag_manager.getTag(Tags.Items.GEMS_QUARTZ).contains(gem)   ){return 0;}
      if(tag_manager.getTag(MaterialTag.RUBY.GEMS).contains(gem)    ){return 1;}
      if(tag_manager.getTag(MaterialTag.TOPAZ.GEMS).contains(gem)   ){return 2;}
      if(tag_manager.getTag(MaterialTag.CITRINE.GEMS).contains(gem) ){return 3;}
      if(tag_manager.getTag(Tags.Items.GEMS_EMERALD).contains(gem)  ){return 4;}
      if(tag_manager.getTag(Tags.Items.GEMS_DIAMOND).contains(gem)  ){return 5;}
      if(tag_manager.getTag(MaterialTag.SAPPHIRE.GEMS).contains(gem)){return 6;}
      if(tag_manager.getTag(Tags.Items.GEMS_AMETHYST).contains(gem) ){return 7;}
    }
    return -1;
  }

}
