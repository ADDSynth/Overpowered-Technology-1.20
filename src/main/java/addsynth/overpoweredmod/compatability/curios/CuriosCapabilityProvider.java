package addsynth.overpoweredmod.compatability.curios;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

// copied from: https://github.com/TheIllusiveC4/Curios/blob/1.17.x/src/main/java/top/theillusivec4/curios/common/capability/CurioItemCapability.java
public final class CuriosCapabilityProvider implements ICapabilityProvider {

  final LazyOptional<ICurio> capability;
  
  public CuriosCapabilityProvider(ICurio curio){
    this.capability = LazyOptional.of(() -> curio);
  }
  
  @NotNull
  @Override
  public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side){
    return CuriosCapability.ITEM.orEmpty(cap, capability);
  }

}
