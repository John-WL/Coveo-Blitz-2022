package codes.blitz.game.message;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public record CoordinatePair(int x, int y) {
    public CoordinatePair plus(CoordinatePair offset) {
        return new CoordinatePair(x()+offset.x(), y()+offset.y());
    }
}
