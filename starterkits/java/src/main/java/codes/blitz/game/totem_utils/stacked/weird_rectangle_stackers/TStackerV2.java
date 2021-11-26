package codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TStackerV2 implements SpecialBlockStacker {

    private static final List<CoordinatePair> blockShape1 = new ArrayList<>();
    static {
        blockShape1.add(new CoordinatePair(0, 0));
        blockShape1.add(new CoordinatePair(1, 0));
        blockShape1.add(new CoordinatePair(2, 0));
        blockShape1.add(new CoordinatePair(1, 1));
    }
    private static final List<CoordinatePair> blockShape2 = new ArrayList<>();
    static {
        blockShape2.add(new CoordinatePair(3, 0));
        blockShape2.add(new CoordinatePair(3, 1));
        blockShape2.add(new CoordinatePair(3, 2));
        blockShape2.add(new CoordinatePair(2, 1));
    }
    private static final List<CoordinatePair> blockShape3 = new ArrayList<>();
    static {
        blockShape3.add(new CoordinatePair(3, 3));
        blockShape3.add(new CoordinatePair(2, 3));
        blockShape3.add(new CoordinatePair(1, 3));
        blockShape3.add(new CoordinatePair(2, 2));
    }
    private static final List<CoordinatePair> blockShape4 = new ArrayList<>();
    static {
        blockShape4.add(new CoordinatePair(0, 3));
        blockShape4.add(new CoordinatePair(0, 2));
        blockShape4.add(new CoordinatePair(0, 1));
        blockShape4.add(new CoordinatePair(1, 2));
    }

    public void build4SquaresBlockAt(List<TotemAnswer> totems, int xOffset, int yOffset, int amountToBuild) {
        for(int j = 0; j < amountToBuild; j++) {    // 4-cell totems
            final List<CoordinatePair> coordinates = new ArrayList<>();
            switch (j) {
                case 0 -> coordinates.addAll(blockShape1.stream()
                        .map(coordinate -> new CoordinatePair(coordinate.x() + xOffset *4, coordinate.y() + yOffset *4))
                        .collect(Collectors.toList()));
                case 1 -> coordinates.addAll(blockShape2.stream()
                        .map(coordinate -> new CoordinatePair(coordinate.x() + xOffset *4, coordinate.y() + yOffset *4))
                        .collect(Collectors.toList()));
                case 2 -> coordinates.addAll(blockShape3.stream()
                        .map(coordinate -> new CoordinatePair(coordinate.x() + xOffset *4, coordinate.y() + yOffset *4))
                        .collect(Collectors.toList()));
                case 3 -> coordinates.addAll(blockShape4.stream()
                        .map(coordinate -> new CoordinatePair(coordinate.x() + xOffset *4, coordinate.y() + yOffset *4))
                        .collect(Collectors.toList()));
            }
            totems.add(new TotemAnswer(Totem.T, coordinates));
        }
    }

    @Override
    public Totem getType() {
        return Totem.T;
    }

    @Override
    public CoordinatePair getEdgeStackOverhead() {
        return new CoordinatePair(1, 0);
    }
}
