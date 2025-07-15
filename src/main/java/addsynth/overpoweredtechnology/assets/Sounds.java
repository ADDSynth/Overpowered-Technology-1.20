package addsynth.overpoweredtechnology.assets;

import addsynth.overpoweredtechnology.OverpoweredTechnology;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class Sounds {

  public static final class Names {
    public static final ResourceLocation laser_fire = OverpoweredTechnology.getLocation("block.laser.fire");
  }

  public static final RegistryObject<SoundEvent> laser_fire_sound =
    RegistryObject.create(Names.laser_fire, ForgeRegistries.SOUND_EVENTS);

}
