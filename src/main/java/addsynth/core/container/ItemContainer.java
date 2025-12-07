package addsynth.core.container;

import java.util.function.Function;
import addsynth.core.game.inventory.ItemInventory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public abstract class ItemContainer extends AbstractContainer{

  protected final ItemInventory inventory;

  public ItemContainer(MenuType type, int id, Inventory player_inventory, ItemInventory inventory){
    super(type, id, player_inventory);
    this.inventory = inventory;
  }

  public ItemContainer(MenuType type, int id, Inventory player_inventory, FriendlyByteBuf buf, Function<ItemStack, ItemInventory> inventory_constructor){
    super(type, id, player_inventory, buf);
    inventory = inventory_constructor.apply(player_inventory.getSelected());
  }

  @Override
  public boolean stillValid(Player player){
    return true;
  }

}
