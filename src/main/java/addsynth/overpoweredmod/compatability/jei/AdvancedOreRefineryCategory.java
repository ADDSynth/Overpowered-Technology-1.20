package addsynth.overpoweredmod.compatability.jei;

import addsynth.overpoweredmod.game.reference.GuiReference;
import addsynth.overpoweredmod.game.reference.Names;
import addsynth.overpoweredmod.game.reference.OverpoweredBlocks;
import addsynth.overpoweredmod.game.reference.TextReference;
import addsynth.overpoweredmod.machines.advanced_ore_refinery.OreRefineryRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public final class AdvancedOreRefineryCategory  implements IRecipeCategory<OreRefineryRecipe> {

  public static final RecipeType<OreRefineryRecipe> type = new RecipeType<>(Names.ADVANCED_ORE_REFINERY, OreRefineryRecipe.class);
  private final IDrawable background;
  private final IDrawable icon;

  public AdvancedOreRefineryCategory(final IGuiHelper gui_helper){
    background = gui_helper.createDrawable(GuiReference.jei_recipe_background, 18, 16, 74, 18);
    icon = gui_helper.createDrawableItemStack(new ItemStack(OverpoweredBlocks.advanced_ore_refinery.get()));
  }

  @Override
  public final RecipeType<OreRefineryRecipe> getRecipeType(){
    return type;
  }

  @Override
  public final Component getTitle(){
    return TextReference.advanced_ore_refinery;
  }

  @Override
  public IDrawable getBackground(){
    return background;
  }

  @Override
  public IDrawable getIcon(){
    return icon;
  }

  @Override
  public void setRecipe(IRecipeLayoutBuilder builder, OreRefineryRecipe recipe, IFocusGroup focuses){
    builder.addSlot(RecipeIngredientRole.INPUT,   1, 1).addItemStack(recipe.itemStack);
    builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 1).addItemStack(recipe.output);
  }

  @Override
  @Nullable
  public final ResourceLocation getRegistryName(final OreRefineryRecipe recipe){
    return recipe.getId();
  }

}
