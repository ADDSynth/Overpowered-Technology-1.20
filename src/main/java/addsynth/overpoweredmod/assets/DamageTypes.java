package addsynth.overpoweredmod.assets;

import addsynth.overpoweredmod.game.reference.Names;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public final class DamageTypes {

  public static final ResourceKey<DamageType> LASER = ResourceKey.create(Registries.DAMAGE_TYPE, Names.LASER_DAMAGE);

}
