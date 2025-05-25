package addsynth.core.game.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.IForgeRegistry;

public final class RegistryUtil {

  public static final <T extends Recipe<?>> void registerRecipeType(final IForgeRegistry<RecipeType<?>> registry, final ResourceLocation name){
    registry.register(name,
      new RecipeType<T>() {
        @Override
        public final String toString(){
          return name.getPath();
        }
      }
    );
  }

}
