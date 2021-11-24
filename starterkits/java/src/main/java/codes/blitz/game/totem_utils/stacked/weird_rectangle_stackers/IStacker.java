package codes.blitz.game.totem_utils.stacked.weird_rectangle_stackers;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IStacker implements SpecialBlockStacker {

    private static final List<CoordinatePair> blockShape = new ArrayList<>();
    static {
        blockShape.add(new CoordinatePair(0, 0));
        blockShape.add(new CoordinatePair(1, 0));
        blockShape.add(new CoordinatePair(2, 0));
        blockShape.add(new CoordinatePair(3, 0));
    }

    public void build4SquaresBlockAt(List<TotemAnswer> totems, int xOffset, int yOffset, int amountToBuild) {
        for(int j = 0; j < amountToBuild; j++) {    // 4-cell totems
            final List<CoordinatePair> coordinates = new ArrayList<>();
            final int finalJ = j;
            coordinates.addAll(blockShape.stream()
                    .map(coordinate -> new CoordinatePair(coordinate.x() + xOffset *4, coordinate.y() + yOffset *4 + finalJ))
                    .collect(Collectors.toList()));
            totems.add(new TotemAnswer(Totem.I, coordinates));
        }
    }

    @Override
    public Totem getType() {
        return Totem.I;
    }

    @Override
    public CoordinatePair getEdgeStackOverhead() {
        return new CoordinatePair(0, 0);
    }
}
