package addsynth.core.gameplay.registers;

import addsynth.core.game.registry.BlockEntityHolder;
import addsynth.core.gameplay.Core;
import addsynth.core.gameplay.music_box.TileMusicBox;
import addsynth.core.gameplay.reference.Names;

public final class Tiles {

  public static final BlockEntityHolder<TileMusicBox> MUSIC_BOX =
    new BlockEntityHolder<>(Names.MUSIC_BOX, TileMusicBox::new, Core.music_box);

}
