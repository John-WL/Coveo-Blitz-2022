package codes.blitz.game;

import codes.blitz.game.client.LocalGameClient;
import codes.blitz.game.client.WebsocketGameClient;

public class Application {
  @SuppressWarnings("resource")
  public static void main(String[] args) throws Exception {
    BotSolver botSolver = new BotSolver();
    if (System.getenv("TOKEN") != null) {
      new WebsocketGameClient(botSolver).run();
    } else {
      new LocalGameClient(botSolver).run();
    }
  }
}