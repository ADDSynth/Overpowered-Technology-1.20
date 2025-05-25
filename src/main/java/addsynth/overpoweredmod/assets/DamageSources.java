package addsynth.overpoweredmod.assets;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public final class DamageSources {

  // public static final DamageSource black_hole = new DamageSource("black_hole").bypassArmor().bypassInvul().bypassMagic();
  // public static final DamageSource corrupted  = new DamageSource("corrupted" ).bypassArmor().bypassInvul().bypassMagic();
  // public static final DamageSource corrupted_by_player = new DamageSource("corrupted_by_player").setDamageBypassesArmor();
  public static final DamageSource get(final Level world, final ResourceKey<DamageType> damage_type){
    // Thanks: MC 1.19.4: https://wiki.fabricmc.net/tutorial:damagetypes
    //                    https://docs.fabricmc.net/develop/entities/damage-types
    return new DamageSource(world.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(damage_type));
  }

}
