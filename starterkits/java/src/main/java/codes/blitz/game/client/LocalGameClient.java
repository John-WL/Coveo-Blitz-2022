package codes.blitz.game.client;

import java.util.List;

import codes.blitz.game.totem_utils.validation.TotemCollisionChecker;
import codes.blitz.game.totem_utils.validation.TotemSignedCoordinateChecker;
import com.fasterxml.jackson.core.JsonProcessingException;

import codes.blitz.game.BotSolver;
import codes.blitz.game.message.GameMessage;
import codes.blitz.game.message.Question;
import codes.blitz.game.message.Totem;
import codes.blitz.game.message.TotemQuestion;

public class LocalGameClient {
  private final BotSolver botSolver;

  public LocalGameClient(BotSolver botSolver) {
    this.botSolver = botSolver;
  }

  public void run() throws JsonProcessingException {
    System.out.println("[Running in local mode]");
    var question = new GameMessage(1, new Question(List.of(
            /*new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.I),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.S),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.Z),
            new TotemQuestion(Totem.J),
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.O),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.L),
            new TotemQuestion(Totem.T)*/
            new TotemQuestion(Totem.T),
            new TotemQuestion(Totem.I)

    )));
    var answer = botSolver.getAnswer(question);
    TotemCollisionChecker.checkForCollisions(answer.totems());
    TotemSignedCoordinateChecker.checkForNegativeCoordinates(answer.totems());
  }
}
