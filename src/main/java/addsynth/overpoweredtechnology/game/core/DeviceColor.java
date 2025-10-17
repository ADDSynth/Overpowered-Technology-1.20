package addsynth.overpoweredtechnology.game.core;

import addsynth.overpoweredtechnology.OverpoweredTechnology;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.MapColor;

/** Many items and devices in the Overpowered Technology mod have a variety
 *  of colors associated with them. This enum is used to specify the different colors.
 *  Since these colors are based on Light, it is unlikely that any new colors will be added.
 *  @see addsynth.overpoweredtechnology.items.basic.LensItem
 *  @see Laser
 *  @see addsynth.overpoweredtechnology.machines.suspension_bridge.EnergyBridge
 */
public enum DeviceColor {

  WHITE  (0, "white",   ChatFormatting.WHITE,        MapColor.SNOW),
  RED    (1, "red",     ChatFormatting.DARK_RED,     MapColor.COLOR_RED),
  ORANGE (2, "orange",  ChatFormatting.GOLD,         MapColor.COLOR_ORANGE),
  YELLOW (3, "yellow",  ChatFormatting.YELLOW,       MapColor.GOLD),
  GREEN  (4, "green",   ChatFormatting.DARK_GREEN,   MapColor.EMERALD),
  CYAN   (5, "cyan",    ChatFormatting.AQUA,         MapColor.DIAMOND),
  BLUE   (6, "blue",    ChatFormatting.BLUE,         MapColor.LAPIS),
  MAGENTA(7, "magenta", ChatFormatting.LIGHT_PURPLE, MapColor.COLOR_MAGENTA);

  public final int index;
  // public final String name;
  public final ResourceLocation energy_bridge;
  public final ResourceLocation lens_name;
  public final ResourceLocation laser_cannon;
  public final ResourceLocation laser_beam;
  public final ResourceLocation laser_sword;
  public final ChatFormatting format_code;
  public final MapColor color;

  private DeviceColor(final int index, final String name, final ChatFormatting format_code, final MapColor material){
    this.index       = index;
    // this.name        = name;
    energy_bridge = ResourceLocation.fromNamespaceAndPath(OverpoweredTechnology.MOD_ID, name+"_energy_bridge");
        lens_name = ResourceLocation.fromNamespaceAndPath(OverpoweredTechnology.MOD_ID, index == 0 ? "focus_lens" : name+"_lens");
     laser_cannon = ResourceLocation.fromNamespaceAndPath(OverpoweredTechnology.MOD_ID, name+"_laser");
       laser_beam = ResourceLocation.fromNamespaceAndPath(OverpoweredTechnology.MOD_ID, name+"_laser_beam");
      laser_sword = ResourceLocation.fromNamespaceAndPath(OverpoweredTechnology.MOD_ID, name+"_laser_sword");
    this.format_code = format_code;
    this.color       = material;
  }

}
