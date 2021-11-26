package codes.blitz.game.totem_utils.linear_mix_stacked;

import codes.blitz.game.totem_utils.linear_mix_stacked.blocks_4x8.ZWasterBlock;
import codes.blitz.game.totem_utils.linear_mix_stacked.default_totems.*;

public class AvailableBlocks {
    public static final SpecialBlock[] blocks = new SpecialBlock[]{
            new ZWasterBlock(),
            new LTotem(),
            new JTotem(),
            new ITotem(),
            new TTotem(),
            new STotem(),
            new ZTotem(),
            new OTotem()
    };
}
