package codes.blitz.game.totem_utils.stacked;

import codes.blitz.game.message.CoordinatePair;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemAnswer;

import java.util.List;
import java.util.stream.Collectors;

public class StackedTotem {

    private List<TotemAnswer> totemList;
    private Totem type;
    private CoordinatePair size;
    private int amountOfSpecial4Squares;

    public StackedTotem(List<TotemAnswer> totemList, Totem type, CoordinatePair size, int amountOfSpecial4Squares) {
        this.totemList = totemList;
        this.type = type;
        this.size = size;
        this.amountOfSpecial4Squares = amountOfSpecial4Squares;
    }

    public StackedTotem(List<TotemAnswer> totemList, CoordinatePair size) {
        this.totemList = totemList;
        this.type = Totem.MIXED;
        this.size = size;
        this.amountOfSpecial4Squares = (int)(Math.ceil(size.x()*size.y()/16.0)+0.5);
    }

    public StackedTotem moveBy(CoordinatePair offset) {
        totemList.forEach(totemAnswer ->
                totemList.set(totemList.indexOf(totemAnswer),
                        new TotemAnswer(totemAnswer.shape(),
                                totemAnswer.coordinates().stream()
                                        .map(position -> position.plus(offset))
                                        .collect(Collectors.toList()))));
        return this;
    }

    public void inversePattern() {
        if(type == Totem.Z || type == Totem.S || type == Totem.L || type == Totem.J) {
            throw new RuntimeException("flipping non-invertible totems is prohibited");
        }
        size = size.inverse();
        totemList.forEach(totemAnswer ->
                totemList.set(totemList.indexOf(totemAnswer),
                        new TotemAnswer(totemAnswer.shape(),
                                totemAnswer.coordinates().stream()
                                        .map(position -> new CoordinatePair(position.y(), position.x()))
                                        .collect(Collectors.toList()))));
    }

    public StackedTotem awkwardRotatePattern90DegreesCW() {
        totemList.forEach(totemAnswer ->
                totemList.set(totemList.indexOf(totemAnswer),
                        new TotemAnswer(totemAnswer.shape(),
                                totemAnswer.coordinates().stream()
                                        .map(position -> new CoordinatePair(position.y(), size.y()-1-position.x()))
                                        .collect(Collectors.toList()))));
        size = size.inverse();
        return this;
    }

    public StackedTotem awkwardRotatePattern90DegreesCCW() {
        totemList.forEach(totemAnswer ->
                totemList.set(totemList.indexOf(totemAnswer),
                        new TotemAnswer(totemAnswer.shape(),
                                totemAnswer.coordinates().stream()
                                        .map(position -> new CoordinatePair(size.x()-1-position.y(), position.x()))
                                        .collect(Collectors.toList()))));
        size = size.inverse();
        return this;
    }

    public List<TotemAnswer> totemList() {
        return totemList;
    }

    public Totem type() {
        return type;
    }

    public CoordinatePair size() {
        return size;
    }

    public int amountOfSpecial4Squares() {
        return amountOfSpecial4Squares;
    }
}
