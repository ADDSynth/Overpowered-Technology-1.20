package addsynth.core.game.inventory;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import addsynth.core.container.ItemContainer;
import addsynth.core.container.ItemContainerSupplier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class ItemInventory implements IStorageInventory, IInventoryResponder, IInputInventory, MenuProvider {

  private final InputInventory inventory;
  private final ItemStack      itemstack;
  private final ItemContainerSupplier menu_supplier;
  private static final String name = "Inventory"; // temporary

  private ItemInventory(ItemStack itemstack, int slots, Predicate<ItemStack> filter, ItemContainerSupplier container_constructor){
    this.itemstack = itemstack;
    inventory = InputInventory.create(this, slots, filter);
    menu_supplier = container_constructor;
    load();
  }

  public static final ItemInventory of(final ItemStack itemstack, int slots, ItemContainerSupplier container_constructor){
    return new ItemInventory(itemstack, slots, (ItemStack stack) -> true, container_constructor);
  }

  public static final ItemInventory of(final ItemStack itemstack, int slots, Predicate<ItemStack> filter, ItemContainerSupplier container_constructor){
    return new ItemInventory(itemstack, slots, filter, container_constructor);
  }

  @Override
  public final void onInventoryChanged(){
    save();
  }

  private final void load(){
    inventory.load(itemstack.getOrCreateTag(), name);
  }

  private final void save(){
    final CompoundTag tag = itemstack.getOrCreateTag();
    inventory.save(tag, name);
  }

  @Override
  public final CommonInventory getInventory(){
    return inventory;
  }

  @Override
  public final InputInventory getInputInventory(){
    return inventory;
  }

  /**
   * This returns the items saved in the Item's inventory. As the items are still
   * saved with the ItemStack, you are free to manipulate these Items as you see fit.
   */
  public static final ItemStack[] getStoredItems(final ItemStack itemstack){
    final CommonInventory inventory = CommonInventory.create(0); // create temporary Inventory, filter does not matter here as we're not inserting items.
    inventory.load(itemstack.getOrCreateTag()); // contains size tag, so it will resize the inventory
    // return inventory;
    return inventory.getItemStacks();
  }

  /**
   * If you want your Item's inventory to drop its contents, then you MUST call this function
   * in your Item's {@link Item#onDestroyed(ItemEntity, DamageSource) onDestroyed()} function.
   * @param entity
   * @see net.minecraft.world.item.ItemUtils#onContainerDestroyed
   */
  public static final void dropContents(ItemEntity entity){
    dropContents(entity, false);
  }

  /**
   * If you want your Item's inventory to drop its contents, then you MUST call this function
   * in your Item's {@link Item#onDestroyed(ItemEntity, DamageSource) onDestroyed()} function.
   * @param entity
   * @see net.minecraft.world.item.ItemUtils#onContainerDestroyed
   * @see net.minecraft.world.level.block.Block#popResource
   */
  public static final void dropContents(ItemEntity entity, boolean spread){
    final ItemStack itemstack = entity.getItem();
    @SuppressWarnings("resource")
    final Level     level     = entity.level();
    if(!level.isClientSide){
      double xDelta = 0;
      double yDelta = 0;
      double zDelta = 0;
      ItemEntity spawnedItem;
      for(final ItemStack item : getStoredItems(itemstack)){
        if(!item.isEmpty()){
          if(spread){
            xDelta = Mth.nextDouble(level.random, -0.1, 0.1);
            yDelta = Mth.nextDouble(level.random,  0.0, 0.1);
            zDelta = Mth.nextDouble(level.random, -0.1, 0.1);
          }
          spawnedItem = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), item, xDelta, yDelta, zDelta);
          spawnedItem.setDefaultPickUpDelay();
          level.addFreshEntity(spawnedItem);
        }
      }
    }
  }

  @Override
  public void drop_inventory(){} // also temporary

  public final void openInventory(final ServerPlayer player){
    NetworkHooks.openScreen(player, this);
  }

  @Override
  @Nullable
  public ItemContainer createMenu(int containerID, Inventory player_inventory, Player player){
    return menu_supplier.get(containerID, player_inventory, this);
  }

  @Override
  public Component getDisplayName(){
    return itemstack.getHoverName();
  }

}
