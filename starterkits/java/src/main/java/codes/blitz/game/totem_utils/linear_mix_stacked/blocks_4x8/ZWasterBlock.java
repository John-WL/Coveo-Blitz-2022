package codes.blitz.game.totem_utils.linear_mix_stacked.blocks_4x8;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.linear_mix_stacked.SpecialBlock;
import codes.blitz.game.totem_utils.linear_mix_stacked.default_totems.LTotem;
import codes.blitz.game.totem_utils.linear_mix_stacked.default_totems.TTotem;
import codes.blitz.game.totem_utils.linear_mix_stacked.default_totems.ZTotem;
import codes.blitz.game.totem_utils.stacked.StackedTotem;

import java.util.ArrayList;
import java.util.List;

public class ZWasterBlock implements SpecialBlock {
    @Override
    public StackedTotem pattern() {
        final List<TotemAnswer> answers = new ArrayList<>();
        answers.addAll(new ZTotem().pattern().totemList());
        answers.addAll(new ZTotem().pattern().moveBy(new CoordinatePair(0, 2)).totemList());
        answers.addAll(new ZTotem().pattern().moveBy(new CoordinatePair(2, 1)).totemList());
        answers.addAll(new ZTotem().pattern().moveBy(new CoordinatePair(2, 3)).totemList());
        answers.addAll(new ZTotem().pattern().moveBy(new CoordinatePair(2, 5)).totemList());
        answers.addAll(new LTotem().pattern()
                .awkwardRotatePattern90DegreesCCW()
                .moveBy(new CoordinatePair(1, 0))
                .totemList());
        answers.addAll(new TTotem().pattern()
                .awkwardRotatePattern90DegreesCW()
                .moveBy(new CoordinatePair(0, 4))
                .totemList());
        answers.addAll(new TTotem().pattern()
                .awkwardRotatePattern90DegreesCW()
                .awkwardRotatePattern90DegreesCW()
                .moveBy(new CoordinatePair(0, 7))
                .totemList());
        return new StackedTotem(answers, new CoordinatePair(4, 8));
    }

    @Override
    public int LCoeff() {
        return 1;
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
        return 2;
    }

    @Override
    public int SCoeff() {
        return 0;
    }

    @Override
    public int ZCoeff() {
        return 5;
    }

    @Override
    public int OCoeff() {
        return 0;
    }
}
