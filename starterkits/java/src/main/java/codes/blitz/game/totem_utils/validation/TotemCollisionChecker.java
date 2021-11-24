package codes.blitz.game.totem_utils.validation;

import codes.blitz.game.message.TotemAnswer;

import java.util.List;

public class TotemCollisionChecker {

    public static void checkForCollisions(List<TotemAnswer> totemAnswers) {
        for(int i = 0; i < totemAnswers.size(); i++) {
            for(int j = i+1; j < totemAnswers.size(); j++) {
                final int finalJ = j;
                totemAnswers.get(i).coordinates().forEach(coordinatePairI -> {
                    totemAnswers.get(finalJ).coordinates().forEach(coordinatePairJ -> {
                        if(coordinatePairI.x() == coordinatePairJ.x() && coordinatePairI.y() == coordinatePairJ.y()) {
                            System.out.println(coordinatePairJ.x());
                            System.out.println(coordinatePairJ.y());
                            throw new RuntimeException("totem collision");
                        }
                    });
                });
            }
        }
    }
}
