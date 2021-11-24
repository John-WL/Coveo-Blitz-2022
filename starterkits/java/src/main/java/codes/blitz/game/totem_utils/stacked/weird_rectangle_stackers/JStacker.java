package codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JStacker implements SpecialBlockStacker {

    private static final List<CoordinatePair> blockShape = new ArrayList<>();
    static {
        blockShape.add(new CoordinatePair(0, 1));
        blockShape.add(new CoordinatePair(0, 2));
        blockShape.add(new CoordinatePair(0, 3));
        blockShape.add(new CoordinatePair(1, 3));
    }

    public void build4SquaresBlockAt(List<TotemAnswer> totems, int xOffset, int yOffset, int amountToBuild) {
        for(int j = 0; j < amountToBuild; j++) {    // 4-cell totems
            final List<CoordinatePair> coordinates = new ArrayList<>();
            switch (j) {
                case 0 -> coordinates.addAll(blockShape.stream()
                        .map(coordinate -> new CoordinatePair(coordinate.x() + xOffset *4, coordinate.y() + yOffset *4))
                        .collect(Collectors.toList()));
                case 1 -> coordinates.addAll(blockShape.stream()
                        .map(coordinate -> new CoordinatePair(1-coordinate.x() + xOffset *4, 3-coordinate.y() + yOffset *4))
                        .collect(Collectors.toList()));
                case 2 -> coordinates.addAll(blockShape.stream()
                        .map(coordinate -> new CoordinatePair(coordinate.x() + xOffset *4 + 2, coordinate.y() + yOffset *4))
                        .collect(Collectors.toList()));
                case 3 -> coordinates.addAll(blockShape.stream()
                        .map(coordinate -> new CoordinatePair(1-coordinate.x() + xOffset *4 + 2, 3-coordinate.y() + yOffset *4))
                        .collect(Collectors.toList()));
            }
            totems.add(new TotemAnswer(Totem.J, coordinates));
        }
    }

    @Override
    public Totem getType() {
        return Totem.J;
    }

    @Override
    public CoordinatePair getEdgeStackOverhead() {
        return new CoordinatePair(0, 0);
    }
}
