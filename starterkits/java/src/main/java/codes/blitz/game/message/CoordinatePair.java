package codes.blitz.game.message;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public record CoordinatePair(int x, int y) {

    public CoordinatePair inverse() {
        return new CoordinatePair(y, x);
    }

    public CoordinatePair plus(CoordinatePair offset) {
        return new CoordinatePair(x()+offset.x(), y()+offset.y());
    }
}
