package codes.blitz.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import codes.blitz.game.message.Answer;
import codes.blitz.game.message.GameMessage;
import codes.blitz.game.message.Question;
import codes.blitz.game.serialization.JsonMapperSingleton;


public class Solver {
    private final JsonMapper jsonMapper = JsonMapperSingleton.getInstance();

    public Solver() {
    }

    public Answer getAnswer(final GameMessage gameMessage) throws JsonProcessingException {
        final Question question = gameMessage.payload();
        System.out.println("Received Question: " + jsonMapper.writeValueAsString(question));

        final var totemsToPlace = question.totems();
        final var placedTotems = BasicStackerSolver.solve(totemsToPlace);
        final Answer answer = new Answer(placedTotems);

        System.out.println("Sending Answer: " + jsonMapper.writeValueAsString(answer));
        return answer;
    }
}