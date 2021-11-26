package codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.stacked.StackedTotem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ZZZLineStacker implements SpecialBlockStacker {

    private static final List<CoordinatePair> zShape = new ArrayList<>();
    static {
        zShape.add(new CoordinatePair(0, 1));
        zShape.add(new CoordinatePair(1, 1));
        zShape.add(new CoordinatePair(1, 0));
        zShape.add(new CoordinatePair(2, 0));
    }

    private static final List<CoordinatePair> lShapeStart = new ArrayList<>();
    static {
        lShapeStart.add(new CoordinatePair(0, 2));
        lShapeStart.add(new CoordinatePair(0, 1));
        lShapeStart.add(new CoordinatePair(0, 0));
        lShapeStart.add(new CoordinatePair(1, 0));
    }

    private static final List<CoordinatePair> lShapeEnd = new ArrayList<>();
    static {
        lShapeEnd.add(new CoordinatePair(1, 0));
        lShapeEnd.add(new CoordinatePair(1, 1));
        lShapeEnd.add(new CoordinatePair(1, 2));
        lShapeEnd.add(new CoordinatePair(0, 2));
    }

    private final int lengthOfStack;

    public ZZZLineStacker(int length) {
        this.lengthOfStack = length;
    }

    @Override
    public Totem getType() {
        return Totem.MIXED;
    }

    @Override
    public CoordinatePair getEdgeStackOverhead() {
        return new CoordinatePair(4*lengthOfStack, 4);
    }

    @Override
    public void build4SquaresBlockAt(List<TotemAnswer> totems, int xOffset, int yOffset, int amountToBuild) {
        if(xOffset != 0 || yOffset != 0 || amountToBuild != 1) {
            throw new RuntimeException("ZZZStacker object cannot perform this building task");
        }
        if(lengthOfStack == 0) {
            return;
        }

        totems.add(new TotemAnswer(Totem.L, lShapeStart));
        totems.add(new TotemAnswer(Totem.L, lShapeEnd.stream()
                .map(coordinatePair -> coordinatePair.plus(new CoordinatePair(lengthOfStack*4 - 2, 1)))
                .collect(Collectors.toList())));

        for(int i = 0; i < lengthOfStack*2 - 1; i++) {
            final int finalI = i;
            totems.add(new TotemAnswer(Totem.Z, zShape.stream()
                    .map(coordinatePair -> coordinatePair.plus(new CoordinatePair(finalI*2 + 1, 0)))
                    .collect(Collectors.toList())));
            totems.add(new TotemAnswer(Totem.Z, zShape.stream()
                    .map(coordinatePair -> coordinatePair.plus(new CoordinatePair(finalI*2, 2)))
                    .collect(Collectors.toList())));
        }
    }
}
