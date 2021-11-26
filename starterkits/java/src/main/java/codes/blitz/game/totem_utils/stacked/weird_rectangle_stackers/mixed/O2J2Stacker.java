package codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.mixed;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.SpecialBlockStacker;

import java.util.ArrayList;
import java.util.List;

public class O2J2Stacker implements SpecialBlockStacker {

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
        final List<CoordinatePair> o1Block = new ArrayList<>();
        o1Block.add(new CoordinatePair(0, 2));
        o1Block.add(new CoordinatePair(0, 3));
        o1Block.add(new CoordinatePair(1, 2));
        o1Block.add(new CoordinatePair(1, 3));
        final List<CoordinatePair> o2Block = new ArrayList<>();
        o2Block.add(new CoordinatePair(2, 2));
        o2Block.add(new CoordinatePair(2, 3));
        o2Block.add(new CoordinatePair(3, 2));
        o2Block.add(new CoordinatePair(3, 3));
        final List<CoordinatePair> j1Block = new ArrayList<>();
        j1Block.add(new CoordinatePair(0, 1));
        j1Block.add(new CoordinatePair(0, 0));
        j1Block.add(new CoordinatePair(1, 0));
        j1Block.add(new CoordinatePair(2, 0));
        final List<CoordinatePair> j2Block = new ArrayList<>();
        j2Block.add(new CoordinatePair(3, 0));
        j2Block.add(new CoordinatePair(3, 1));
        j2Block.add(new CoordinatePair(2, 1));
        j2Block.add(new CoordinatePair(1, 1));

        totems.add(new TotemAnswer(Totem.O, o1Block));
        totems.add(new TotemAnswer(Totem.O, o2Block));
        totems.add(new TotemAnswer(Totem.J, j1Block));
        totems.add(new TotemAnswer(Totem.J, j2Block));
    }
}
