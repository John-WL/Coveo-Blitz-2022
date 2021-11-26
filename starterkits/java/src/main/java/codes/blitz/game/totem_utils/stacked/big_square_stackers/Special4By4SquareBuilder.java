package codes.blitz.game.totem_utils.stacked.big_square_stackers;

import codes.blitz.game.message.Totem;
import codes.blitz.game.totem_utils.stacked.StackedTotem;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.*;

import java.util.List;

public record Special4By4SquareBuilder(Totem totem) {

    public List<StackedTotem> build(final int amount) {
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
                throw new RuntimeException("the totem type is not handled for special squares");
        }
    }

    private List<StackedTotem> buildL(int amount) {
        return SpecialBlockStacker.generateSpecial4By4BlockList(amount, new LStacker());
    }

    private List<StackedTotem> buildJ(int amount) {
        return SpecialBlockStacker.generateSpecial4By4BlockList(amount, new JStacker());
    }

    private List<StackedTotem> buildI(int amount) {
        return SpecialBlockStacker.generateSpecial4By4BlockList(amount, new IStacker());
    }

    private List<StackedTotem> buildT(int amount) {
        return SpecialBlockStacker.generateSpecial4By4BlockList(amount, new TStackerV2());
    }

    private List<StackedTotem> buildS(int amount) {
        return SpecialBlockStacker.generateSpecial4By4BlockList(amount, new SStackerV2());
    }

    private List<StackedTotem> buildZ(int amount) {
        return SpecialBlockStacker.generateSpecial4By4BlockList(amount, new ZStacker());
    }

    private List<StackedTotem> buildO(int amount) {
        return SpecialBlockStacker.generateSpecial4By4BlockList(amount, new OStacker());
    }
}
