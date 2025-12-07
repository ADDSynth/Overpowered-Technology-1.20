package addsynth.core.game.inventory.filter;

import java.util.function.Predicate;
import net.minecraft.world.item.ItemStack;

/** <p>An Inverted Filter does as the name implies. Pass in another filter,
 *  and this will ONLY return true, if the supplied filter fails.
 *  So you can think of this as a Blacklist, where normally filters are
 *  treated as a Whitelist.
 *  <p>Although, now that I think about it, I suppose you could've just
 *  custom-constructed a Predicate that allows all items EXCEPT the ones
 *  you specify.
 */
public class InvertedFilter {
  public static final Predicate<ItemStack> of(Predicate<ItemStack> predicate){
    return (ItemStack itemstack) -> !predicate.test(itemstack);
  }
}
/*
public class InvertedFilter implements Predicate<ItemStack> {

  private final Predicate<ItemStack> internal;

  private InvertedFilter(Predicate<ItemStack> predicate){
    internal = predicate;
  }

  public static final InvertedFilter of(Predicate<ItemStack> test){
    return new InvertedFilter(test);
  }

  @Override
  public boolean test(ItemStack itemstack){
    return !internal.test(itemstack);
  }

}
*/
