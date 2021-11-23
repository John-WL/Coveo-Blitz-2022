package codes.blitz.game.client;

import java.util.List;

import codes.blitz.game.totem_utils.collisions.TotemCollisionChecker;
import com.fasterxml.jackson.core.JsonProcessingException;

import codes.blitz.game.Solver;
import codes.blitz.game.message.GameMessage;
import codes.blitz.game.message.Question;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemQuestion;

public class LocalGameClient {
  private final Solver solver;

  public LocalGameClient(Solver solver) {
    this.solver = solver;
  }

  public void run() throws JsonProcessingException {
    System.out.println("[Running in local mode]");
    var question = new GameMessage(1, new Question(List.of(
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.O)
    )));
    var answer = solver.getAnswer(question);
    TotemCollisionChecker.checkForCollisions(answer.totems());
  }
}
