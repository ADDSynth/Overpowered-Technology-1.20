package addsynth.core.game.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class BlockItemHolder {

  private final RegistryObject<Block> block;
  private final RegistryObject<Item> item;
  private final Item.Properties item_properties;

  public BlockItemHolder(final ResourceLocation name){
    this(RegistryObject.create(name, ForgeRegistries.BLOCKS), new Item.Properties());
  }
  
  public BlockItemHolder(final ResourceLocation name, final Item.Properties item_properties){
    this(RegistryObject.create(name, ForgeRegistries.BLOCKS), item_properties);
  }
  
  public BlockItemHolder(final RegistryObject<Block> block){
    this(block, new Item.Properties());
  }
  
  public BlockItemHolder(final RegistryObject<Block> block, final Item.Properties item_properties){
    this.block = block;
    item = RegistryObject.create(block.getId(), ForgeRegistries.ITEMS);
    this.item_properties = item_properties;
  }

  public static final void register(final IForgeRegistry<Item> registry, final RegistryObject<Block> block){
    registry.register(block.getId(), new BlockItem(block.get(), new Item.Properties()));
  }

  public final BlockItem get(){
    return (BlockItem)item.get();
  }

  public final void register(final IForgeRegistry<Item> registry){
    registry.register(block.getId(), new BlockItem(block.get(), item_properties));
  }

}
