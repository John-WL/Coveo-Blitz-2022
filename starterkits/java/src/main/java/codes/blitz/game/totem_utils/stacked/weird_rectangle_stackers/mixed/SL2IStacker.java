package codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.mixed;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers.SpecialBlockStacker;

import java.util.ArrayList;
import java.util.List;

public class SL2IStacker implements SpecialBlockStacker {

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
        final List<CoordinatePair> iBlock = new ArrayList<>();
        iBlock.add(new CoordinatePair(3, 0));
        iBlock.add(new CoordinatePair(3, 1));
        iBlock.add(new CoordinatePair(3, 2));
        iBlock.add(new CoordinatePair(3, 3));
        final List<CoordinatePair> l1Block = new ArrayList<>();
        l1Block.add(new CoordinatePair(2, 1));
        l1Block.add(new CoordinatePair(2, 0));
        l1Block.add(new CoordinatePair(1, 0));
        l1Block.add(new CoordinatePair(0, 0));
        final List<CoordinatePair> l2Block = new ArrayList<>();
        l2Block.add(new CoordinatePair(2, 3));
        l2Block.add(new CoordinatePair(1, 3));
        l2Block.add(new CoordinatePair(0, 3));
        l2Block.add(new CoordinatePair(0, 2));
        final List<CoordinatePair> sBlock = new ArrayList<>();
        sBlock.add(new CoordinatePair(2, 2));
        sBlock.add(new CoordinatePair(1, 2));
        sBlock.add(new CoordinatePair(1, 1));
        sBlock.add(new CoordinatePair(0, 1));

        totems.add(new TotemAnswer(Totem.I, iBlock));
        totems.add(new TotemAnswer(Totem.L, l1Block));
        totems.add(new TotemAnswer(Totem.L, l2Block));
        totems.add(new TotemAnswer(Totem.S, sBlock));
    }
}
