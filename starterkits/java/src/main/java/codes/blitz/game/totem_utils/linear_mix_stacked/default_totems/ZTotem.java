package codes.blitz.game.totem_utils.linear_mix_stacked.default_totems;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.linear_mix_stacked.SpecialBlock;
import codes.blitz.game.totem_utils.stacked.StackedTotem;

import java.util.ArrayList;
import java.util.List;

public class ZTotem implements SpecialBlock {
    @Override
    public StackedTotem pattern() {
        final List<TotemAnswer> totemAnswers = new ArrayList<>();
        final List<CoordinatePair> coordinatePairs = new ArrayList<>();
        coordinatePairs.add(new CoordinatePair(0, 0));
        coordinatePairs.add(new CoordinatePair(0, 1));
        coordinatePairs.add(new CoordinatePair(1, 1));
        coordinatePairs.add(new CoordinatePair(1, 2));
        totemAnswers.add(new TotemAnswer(Totem.Z, coordinatePairs));
        return new StackedTotem(totemAnswers, new CoordinatePair(2, 3));
    }

    @Override
    public int LCoeff() {
        return 0;
    }

    @Override
    public int JCoeff() {
        return 0;
    }

    @Override
    public int ICoeff() {
        return 0;
    }

    @Override
    public int TCoeff() {
        return 0;
    }

    @Override
    public int SCoeff() {
        return 0;
    }

    @Override
    public int ZCoeff() {
        return 1;
    }

    @Override
    public int OCoeff() {
        return 0;
    }
}
