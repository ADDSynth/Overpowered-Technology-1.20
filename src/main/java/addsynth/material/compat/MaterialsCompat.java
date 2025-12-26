package addsynth.material.compat;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

public final class MaterialsCompat {

  // have to do it this way to remove dependencies
  public static final class ModID {
    private final String mod_id;
    private ModID(String mod_id){
      this.mod_id = mod_id;
    }
    public final boolean isLoaded(){
      return ModList.get().isLoaded(mod_id);
    }
  }

  public static final ModID addsynthcore          = new ModID("addsynthcore");
  public static final ModID addsynth_energy       = new ModID("addsynth_energy");
  public static final ModID immersive_engineering = new ModID("immersiveengineering");
  public static final ModID mekanism              = new ModID("mekanism");

  public static final boolean SteelModAbsent(){
    return !(
      immersive_engineering.isLoaded() ||
      mekanism.isLoaded()
    );
  }

  public static final boolean BronzeModAbsent(){
    return !(
      immersive_engineering.isLoaded() || // In Alloy Smelter
      mekanism.isLoaded()                 // by combining dusts
    );
  }

  public static final void sendIMCMessages(final InterModEnqueueEvent event){
    // if(Compatibility.PROJECT_E.isLoaded()){
    //   ProjectE.register_emc_values();
    // }
  }

}
