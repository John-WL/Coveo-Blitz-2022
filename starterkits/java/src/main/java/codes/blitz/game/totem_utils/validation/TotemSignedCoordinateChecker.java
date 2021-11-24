package codes.blitz.game.totem_utils.validation;

import codes.blitz.game.message.TotemAnswer;

import java.util.List;

public class TotemSignedCoordinateChecker {

    public static void checkForNegativeCoordinates(List<TotemAnswer> totemAnswers) {
        totemAnswers.forEach(totemAnswer -> {
            totemAnswer.coordinates().forEach(coordinatePair -> {
                if(coordinatePair.x() < 0 || coordinatePair.y() < 0) {
                    throw new RuntimeException("Coordinate pairs cannot be negative");
                }
            });
        });
    }
}
