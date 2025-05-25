package addsynth.overpoweredmod.game.core;

import addsynth.overpoweredmod.game.reference.Names;
import addsynth.overpoweredmod.machines.magic_infuser.recipes.MagicInfuserRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class RecipeTypes {

  public static final RegistryObject<RecipeType<MagicInfuserRecipe>> MAGIC_INFUSER = RegistryObject.create(Names.MAGIC_INFUSER, ForgeRegistries.RECIPE_TYPES);

}
