package addsynth.overpoweredmod.compatability.jei;

import addsynth.overpoweredmod.game.reference.GuiReference;
import addsynth.overpoweredmod.game.reference.Names;
import addsynth.overpoweredmod.game.reference.OverpoweredBlocks;
import addsynth.overpoweredmod.game.reference.TextReference;
import addsynth.overpoweredmod.machines.inverter.InverterRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;

public final class InverterCategory implements IRecipeCategory<InverterRecipe> {

  public static final RecipeType<InverterRecipe> type = new RecipeType<>(Names.INVERTER, InverterRecipe.class);
  private final IDrawable background;
  private final IDrawable icon;

  public InverterCategory(final IGuiHelper gui_helper){
    background = gui_helper.createDrawable(GuiReference.jei_recipe_background, 18, 16, 74, 18);
    icon = gui_helper.createDrawableItemStack(new ItemStack(OverpoweredBlocks.inverter.get()));
  }

  @Override
  public final RecipeType<InverterRecipe> getRecipeType(){
    return type;
  }

  @Override
  public final Component getTitle(){
    return TextReference.inverter;
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
  public void setRecipe(IRecipeLayoutBuilder builder, InverterRecipe recipe, IFocusGroup focuses){
    builder.addSlot(RecipeIngredientRole.INPUT,   1, 1).addItemStack(recipe.input);
    builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 1).addItemStack(recipe.result);
  }

  @Override
  @Nullable
  public final ResourceLocation getRegistryName(final InverterRecipe recipe){
    return recipe.getId();
  }

}
