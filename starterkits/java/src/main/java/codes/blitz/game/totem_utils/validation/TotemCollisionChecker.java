package codes.blitz.game.totem_utils.validation;

import codes.blitz.game.message.TotemAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TotemCollisionChecker {

    public static void checkForCollisions(List<TotemAnswer> totemAnswers) {
        final AtomicReference<String> collisionsString = new AtomicReference<>("");
        for(int i = 0; i < totemAnswers.size(); i++) {
            for(int j = i+1; j < totemAnswers.size(); j++) {
                final int finalJ = j;
                totemAnswers.get(i).coordinates().forEach(coordinatePairI -> {
                    totemAnswers.get(finalJ).coordinates().forEach(coordinatePairJ -> {
                        if(coordinatePairI.x() == coordinatePairJ.x() && coordinatePairI.y() == coordinatePairJ.y()) {
                            collisionsString.set(collisionsString.get() + ", [" + coordinatePairJ.x() + "," + coordinatePairJ.y() + "]");
                        }
                    });
                });
            }
        }
        if(collisionsString.get().equals("")) {
            return;
        }
        throw new RuntimeException("totem collision at " + collisionsString.get());
    }
}
