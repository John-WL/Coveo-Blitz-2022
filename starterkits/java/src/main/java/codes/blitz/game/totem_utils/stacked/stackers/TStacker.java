package codes.blitz.game.totem_utils.stacked.stackers;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.totem_utils.stacked.stackers.SpecialBlockStacker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TStacker implements SpecialBlockStacker {

    private static final List<CoordinatePair> blockShape = new ArrayList<>();
    static {
        blockShape.add(new CoordinatePair(0, 0));
        blockShape.add(new CoordinatePair(1, 0));
        blockShape.add(new CoordinatePair(2, 0));
        blockShape.add(new CoordinatePair(1, 1));
    }

    public void build4SquaresBlockAt(List<TotemAnswer> totems, int xOffset, int yOffset, int amountToBuild) {
        for(int j = 0; j < amountToBuild; j++) {    // 4-cell totems
            final List<CoordinatePair> coordinates = new ArrayList<>();
            switch (j) {
                case 0 -> coordinates.addAll(blockShape.stream()
                        .map(coordinate -> new CoordinatePair(coordinate.x() + xOffset *4, coordinate.y() + yOffset *4))
                        .collect(Collectors.toList()));
                case 1 -> coordinates.addAll(blockShape.stream()
                        .map(coordinate -> new CoordinatePair(coordinate.x() + xOffset *4 + 2, 1-coordinate.y() + yOffset *4))
                        .collect(Collectors.toList()));
                case 2 -> coordinates.addAll(blockShape.stream()
                        .map(coordinate -> new CoordinatePair(coordinate.x() + xOffset *4, coordinate.y() + yOffset *4 + 2))
                        .collect(Collectors.toList()));
                case 3 -> coordinates.addAll(blockShape.stream()
                        .map(coordinate -> new CoordinatePair(coordinate.x() + xOffset *4 + 2, 1-coordinate.y() + yOffset *4 + 2))
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
