package codes.blitz.game.totem_utils.totem_question.builder;

import codes.blitz.game.message.Question;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TotemQuestionBuilder {

    private static final List<Totem> ALL_AVAILABLE_TOTEMS = List.of(Totem.L, Totem.J, Totem.I, Totem.I, Totem.T, Totem.S, Totem.Z, Totem.O);

    public TotemQuestionBuilder() {
    }

    public Question build(final int questionNumber) {
        int amountOfTotemsToGenerate = (int) (Math.pow(2, questionNumber-1)+0.5);
        final List<TotemQuestion> totemQuestions = new ArrayList<>();

        for(int i = 0; i < amountOfTotemsToGenerate; i++) {
            totemQuestions.add(new TotemQuestion(getRandomTotem()));
        }

        return new Question(totemQuestions);
    }

    private Totem getRandomTotem() {
        final int randomIndex = (int) (Math.random()*ALL_AVAILABLE_TOTEMS.size());
        return ALL_AVAILABLE_TOTEMS.get(randomIndex);
    }
}
