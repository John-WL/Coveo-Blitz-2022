package codes.blitz.game.totem_utils.stacked;

import codes.blitz.game.message.Totem;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.*;

public record StackedTotemBuilder(Totem totem) {

    public StackedTotem build(final int amount) {
        switch (totem) {
            case L:
                return buildL(amount);
            case J:
                return buildJ(amount);
            case I:
                return buildI(amount);
            case T:
                return buildT(amount);
            case S:
                return buildS(amount);
            case Z:
                return buildZ(amount);
            case O:
                return buildO(amount);
            default:
                throw new RuntimeException("the totem type is not handled");
        }
    }

    private StackedTotem buildL(int amount) {
        return SpecialBlockStacker.stackBlocks(amount, new LStacker());
    }

    private StackedTotem buildJ(int amount) {
        return SpecialBlockStacker.stackBlocks(amount, new JStacker());
    }

    private StackedTotem buildI(int amount) {
        return SpecialBlockStacker.stackBlocks(amount, new IStacker());
    }

    private StackedTotem buildT(int amount) {
        return SpecialBlockStacker.stackBlocks(amount, new TStacker());
    }

    private StackedTotem buildS(int amount) {
        return SpecialBlockStacker.stackBlocks(amount, new SStacker());
    }

    private StackedTotem buildZ(int amount) {
        return SpecialBlockStacker.stackBlocks(amount, new ZStacker());
    }

    private StackedTotem buildO(int amount) {
        return SpecialBlockStacker.stackBlocks(amount, new OStacker());
    }
}
