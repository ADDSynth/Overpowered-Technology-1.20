package addsynth.core.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.Set;
import addsynth.core.util.constants.DevStage;
import addsynth.core.util.java.StringUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Logger;

public final class CommonUtil {

  /** Using {@link ForgeRegistries#BLOCKS} directly in an advanced for statement
   *  will automatically iterate over all Blocks. */
  @Deprecated // REMOVE CommonUtil.getAllBlock() and getAllItems(). Iterate directly. In fact, you can get rid of a bunch of these functions.
  public static final Collection<Block> getAllBlocks(){
    return ForgeRegistries.BLOCKS.getValues();
  }
  
  @Deprecated
  public static final Set<ResourceLocation> getAllBlockIDs(){
    return ForgeRegistries.BLOCKS.getKeys();
  }
  
  /** Using {@link ForgeRegistries#ITEMS} directly in an advanced for statement
   *  will automatically iterate over all Items. */
  @Deprecated
  public static final Collection<Item> getAllItems(){
    return ForgeRegistries.ITEMS.getValues();
  }
  
  @Deprecated
  public static final Set<ResourceLocation> getAllItemIDs(){
    return ForgeRegistries.ITEMS.getKeys();
  }

  @Deprecated
  public static final Collection<EntityType<?>> getAllEntities(){
    return ForgeRegistries.ENTITY_TYPES.getValues();
  }
  
  @Deprecated
  public static final Set<ResourceLocation> getAllEntityIDs(){
    return ForgeRegistries.ENTITY_TYPES.getKeys();
  }

  public static final void displayModInfo(Logger log, String mod_name, String author, String version, DevStage dev_stage, String date){
    if(dev_stage.isDevelopment){
      log.info(StringUtil.build(mod_name, " by ", author, ", version ", version, dev_stage.label, ", built on ", LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)), "."));
      log.warn("This is a development build. This is not intended for public use.");
    }
    else{
      log.info(StringUtil.build(mod_name, " by ", author, ", version ", version, ", built on ", date, "."));
    }
  }

}
