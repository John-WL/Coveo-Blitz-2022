package codes.blitz.game.totem_utils.validation;

import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.message.TotemQuestion;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TotemAmountChecker {

    public static void checkForCollisions(List<TotemQuestion> totemQuestions, List<TotemAnswer> totemAnswers) {
        final AtomicReference<String> errorString = new AtomicReference<>("");
        for(int i = 0; i < totemAnswers.size(); i++) {

        }
        if(errorString.get().equals("")) {
            return;
        }
    }
}
