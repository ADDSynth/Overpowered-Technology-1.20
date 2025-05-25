package addsynth.overpoweredmod.game.core;

import addsynth.overpoweredmod.machines.laser.beam.LaserBeam;
import addsynth.overpoweredmod.machines.laser.cannon.LaserCannon;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public enum Laser {

  WHITE  (DeviceColor.WHITE),
  RED    (DeviceColor.RED),
  ORANGE (DeviceColor.ORANGE),
  YELLOW (DeviceColor.YELLOW),
  GREEN  (DeviceColor.GREEN),
  CYAN   (DeviceColor.CYAN),
  BLUE   (DeviceColor.BLUE),
  MAGENTA(DeviceColor.MAGENTA);

  private final DeviceColor color;
  public final RegistryObject<Block> cannon;
  public final RegistryObject<Block> beam;

  public static final Laser[] index = Laser.values();
  
  private Laser(final DeviceColor color){
    this.color = color;
    this.cannon = RegistryObject.create(color.laser_cannon, ForgeRegistries.BLOCKS);
    this.beam   = RegistryObject.create(color.laser_beam,   ForgeRegistries.BLOCKS);
  }

  public final void registerBlocks(final IForgeRegistry<Block> registry){
    registry.register(color.laser_cannon, new LaserCannon(color.index));
    registry.register(color.laser_beam,   new LaserBeam());
  }
  
}
