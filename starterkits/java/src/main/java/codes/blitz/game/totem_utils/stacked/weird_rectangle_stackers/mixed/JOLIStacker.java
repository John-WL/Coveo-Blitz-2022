package codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.mixed;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.SpecialBlockStacker;

import java.util.ArrayList;
import java.util.List;

public class JOLIStacker implements SpecialBlockStacker {

    @Override
    public Totem getType() {
        return Totem.MIXED;
    }

    @Override
    public CoordinatePair getEdgeStackOverhead() {
        return new CoordinatePair(4, 4);
    }

    @Override
    public void build4SquaresBlockAt(List<TotemAnswer> totems, int xOffset, int yOffset, int amountToBuild) {
        final List<CoordinatePair> jBlock = new ArrayList<>();
        jBlock.add(new CoordinatePair(0, 1));
        jBlock.add(new CoordinatePair(0, 2));
        jBlock.add(new CoordinatePair(0, 3));
        jBlock.add(new CoordinatePair(1, 3));
        final List<CoordinatePair> oBlock = new ArrayList<>();
        oBlock.add(new CoordinatePair(1, 1));
        oBlock.add(new CoordinatePair(2, 1));
        oBlock.add(new CoordinatePair(1, 2));
        oBlock.add(new CoordinatePair(2, 2));
        final List<CoordinatePair> lBlock = new ArrayList<>();
        lBlock.add(new CoordinatePair(3, 1));
        lBlock.add(new CoordinatePair(3, 2));
        lBlock.add(new CoordinatePair(3, 3));
        lBlock.add(new CoordinatePair(2, 3));
        final List<CoordinatePair> iBlock = new ArrayList<>();
        iBlock.add(new CoordinatePair(0, 0));
        iBlock.add(new CoordinatePair(1, 0));
        iBlock.add(new CoordinatePair(2, 0));
        iBlock.add(new CoordinatePair(3, 0));

        totems.add(new TotemAnswer(Totem.J, jBlock));
        totems.add(new TotemAnswer(Totem.O, oBlock));
        totems.add(new TotemAnswer(Totem.L, lBlock));
        totems.add(new TotemAnswer(Totem.I, iBlock));
    }
}
