package addsynth.overpoweredtechnology.machines.advanced_gem_converter;

import addsynth.core.game.inventory.InputInventory;
import addsynth.core.game.inventory.SlotData;
import addsynth.core.game.inventory.filter.TagFilter;
import addsynth.core.util.network.NetworkUtil;
import addsynth.core.util.player.PlayerUtil;
import addsynth.energy.lib.tiles.machines.MachineStatus;
import addsynth.energy.lib.tiles.machines.TileStandardWorkMachine;
import addsynth.material.Material;
import addsynth.overpoweredtechnology.config.MachineValues;
import addsynth.overpoweredtechnology.game.NetworkHandler;
import addsynth.overpoweredtechnology.game.core.Gems;
import addsynth.overpoweredtechnology.game.reference.OverpoweredBlocks;
import addsynth.overpoweredtechnology.game.reference.OverpoweredItems;
import addsynth.overpoweredtechnology.game.tags.OverpoweredItemTags;
import addsynth.overpoweredtechnology.machines.gem_converter.TileGemConverter;
import addsynth.overpoweredtechnology.registers.Tiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileAdvancedGemConverter extends TileStandardWorkMachine implements MenuProvider {

  // 1st slot, filter -> ONLY GEMS
  // slot 2-9 gem holder slots (filter for that specific gem)
  private static final SlotData[] slot_data = {
    new SlotData(new TagFilter(OverpoweredItemTags.convertable_gems)),
    new SlotData(Items.QUARTZ),
    new SlotData(Material.RUBY.gem), // I'm restricting these slots to my gems for now, no harm I suppose
    new SlotData(Material.TOPAZ.gem),
    new SlotData(Material.CITRINE.gem),
    new SlotData(Items.EMERALD),
    new SlotData(Items.DIAMOND),
    new SlotData(Material.SAPPHIRE.gem),
    new SlotData(Items.AMETHYST_SHARD)
  };
  // Work slot (Gem Converter)

  private int slot;
  private int count;
  private int lowest_value;
  private int temp_lowest_value;
  private int next_slot;

  public TileAdvancedGemConverter(BlockPos position, BlockState blockstate){
    // By default, a Machine Inventory will have the Working Inventory slot count equal to that of the Input Inventory. This machine only uses the first slot.
    super(Tiles.ADVANCED_GEM_CONVERTER.get(), position, blockstate, slot_data, 1, MachineValues.advanced_gem_converter);
    inventory.setResponder(this);
  }

  // =====================================================================================

  @Override
  protected void derivedTick(ServerLevel level, BlockState blockstate){
    super.derivedTick(level, blockstate);
    if(temp_lowest_value != lowest_value){
      lowest_value = temp_lowest_value;
      sendClientSync();
    }
  }

  @Override
  public void onInventoryChanged(){
    countGems();
    changed = true;
  }

  /** This loops through all the gem slots, finds the lowest count and sets the
   *  {@code next_slot} value. This must be run every time the inventory changes
   *  and when we first load! {@code lowest_value} is only used on the client side,
   *  so we only check it once per tick, and send a message to the client.
   */
  private final void countGems(){
    temp_lowest_value = Integer.MAX_VALUE;
    final InputInventory inventory = this.inventory.getInputInventory();
    for(slot = 1; slot < 9; slot++){
      if(inventory.getStackInSlot(slot).isEmpty()){
        temp_lowest_value = 0;
        next_slot = slot;
        break;
      }
      count = inventory.getStackInSlot(slot).getCount();
      if(count < temp_lowest_value){
        temp_lowest_value = count;
        next_slot = slot;
      }
    }
  }

  public final void sendClientSync(){
    final GemConverterSyncClientMessage message = new GemConverterSyncClientMessage(this.worldPosition, lowest_value, next_slot);
    NetworkUtil.send_to_clients_in_world(NetworkHandler.INSTANCE, this.level, message);
  }

  @Override
  protected void begin_work(){
    final ItemStack itemstack = inventory.getInputInventory().extractItem(0, 1, false);
    inventory.getWorkingInventory().setStackInSlot(0, itemstack);
  }

  @Override
  protected boolean can_work(){
    if(!inventory.getInputInventory().getStackInSlot(0).isEmpty()){
      if(temp_lowest_value >= 64){
        status = MachineStatus.OUTPUT_FULL;
        return false;
      }
      status = MachineStatus.GOOD;
      if(quick_transfer()){
        return false;
      }
      return true;
    }
    return false;
  }

  @Override
  protected void finishWork(){
    energy.setEmpty();
    inventory.getWorkingInventory().setEmpty();
    addGem();
    TileGemConverter.increment_gems_stat(this.level, last_used_by);
  }

  public void syncClient(int lowest_value, int next_slot){
    this.lowest_value = lowest_value;
    this.next_slot = next_slot;
  }

  /** Adds a gem to the {@code next_slot}. The caller is responsible for decrementing the
   *  Input. This increments the gem if it already exists, otherwise we add our gem to the
   *  slot. A gem could already exist in the slot if the user had inserted a valid Gem,
   *  possibly from another mod.
   */
  private final void addGem(){
    final InputInventory inventory = this.inventory.getInputInventory();
    if(inventory.getStackInSlot(next_slot).isEmpty()){
      inventory.setStackInSlot(next_slot, Gems.getGem(next_slot - 1));
    }
    else{
      inventory.getStackInSlot(next_slot).grow(1);
      onInventoryChanged(); // apparently the shrink / grow functions don't call this
    }
  }

  // =====================================================================================

  private boolean quick_transfer(){
    final ItemStack input = inventory.getInputInventory().getStackInSlot(0);
    if(!input.isEmpty()){
      final int index = Gems.getGemIndex(input);
      if(index == next_slot - 1){
        inventory.getInputInventory().getStackInSlot(0).shrink(1);
        addGem();
        return true;
      }
    }
    return false;
  }

  public boolean canCraftLightBlock(){
    return lowest_value >= 3 && inventory.getOutputInventory().can_add(0, new ItemStack(OverpoweredBlocks.light_block.get()));
  }

  public boolean canCraftEnergyCrystal(){
    return lowest_value >= 1 && inventory.getOutputInventory().can_add(0, new ItemStack(OverpoweredItems.energy_crystal.get()));
  }

  public void craftLightBlock(){
    craftItem(OverpoweredBlocks.light_block.get(), 3);
  }

  public void craftEnergyCrystal(){
    craftItem(OverpoweredItems.energy_crystal.get(), 1);
  }

  private final void craftItem(final ItemLike item, final int count){
    for(slot = 1; slot < 9; slot++){
      inventory.getInputInventory().getStackInSlot(slot).shrink(count);
    }
    final ItemStack output = new ItemStack(item);
    inventory.getOutputInventory().add(output); // move this after shrinking so it calls onInventoryChanged()
    output.onCraftedBy(level, PlayerUtil.getPlayer(level, last_used_by), 1); // award craft stat to player
  }

  public final int getLowestValue(){
    return lowest_value;
  }

  // =====================================================================================

  @Override
  @NotNull
  public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side){
    return LazyOptional.empty(); // Remove Hopper auto transfer for now. Needs further testing.
  }

  @Override
  public void load(CompoundTag nbt){
    super.load(nbt);
    loadPlayerData(nbt);
    countGems();
  }

  @Override
  protected void saveAdditional(CompoundTag nbt){
    super.saveAdditional(nbt);
    savePlayerData(nbt);
  }

  // =====================================================================================

  public final ItemStack getNextGem(){
    // UNUSED, Not set up for this yet. easiest way to keep client synced is to send message every tick? or also check if next_slot changed using another temp variable?
    return lowest_value >= 0 && lowest_value < 64 ? Gems.getGem(next_slot - 1) : ItemStack.EMPTY;
  }

  @Override
  @javax.annotation.Nullable
  public AbstractContainerMenu createMenu(int containerID, Inventory playerInventory, Player player){
    setPlayerAccessed(player);
    return new AdvancedGemConverterContainer(containerID, playerInventory, this);
  }

  @Override
  public Component getDisplayName(){
    return getBlockState().getBlock().getName();
  }

}
