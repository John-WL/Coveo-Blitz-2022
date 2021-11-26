package codes.blitz.game;

import codes.blitz.game.message.TotemAnswer;
import codes.blitz.game.solvers.BasicStackerSolver;
import codes.blitz.game.solvers.BigSquareFirstStackerSolverV2;
import codes.blitz.game.solvers.LinearSystemSolver;
import codes.blitz.game.solvers.MixedSolverV1;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import codes.blitz.game.message.Answer;
import codes.blitz.game.message.GameMessage;
import codes.blitz.game.message.Question;
import codes.blitz.game.serialization.JsonMapperSingleton;

import java.util.List;


public class BotSolver {
    private final JsonMapper jsonMapper = JsonMapperSingleton.getInstance();

    public BotSolver() {
    }

    public Answer getAnswer(final GameMessage gameMessage) throws JsonProcessingException {
        final Question question = gameMessage.payload();
        System.out.println("Received Question: " + jsonMapper.writeValueAsString(question));

        final var totemsToPlace = question.totems();
        List<TotemAnswer> placedTotems;
        placedTotems = new MixedSolverV1().solve(totemsToPlace);
        final Answer answer = new Answer(placedTotems);

        totemsToPlace.forEach(totemQuestion -> {
            System.out.println("new TotemQuestion(Totem." + totemQuestion.shape() + "),");
        });

        System.out.println("Sending Answer: " + jsonMapper.writeValueAsString(answer));
        return answer;
    }
}