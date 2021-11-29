package codes.blitz.game.client;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import codes.blitz.game.totem_utils.totem_question.builder.TotemQuestionBuilder;
import org.glassfish.tyrus.client.ClientManager;

import codes.blitz.game.BotSolver;
import codes.blitz.game.message.GameMessage;
import codes.blitz.game.serialization.MessageDecoder;
import codes.blitz.game.serialization.MessageEncoder;
import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;

@ClientEndpoint(decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class WebsocketGameClient {
  private final BotSolver botSolver;
  private final CountDownLatch latch;

  public WebsocketGameClient(BotSolver botSolver) {
    this.botSolver = botSolver;
    this.latch = new CountDownLatch(1);
  }

  public void run() {
    ClientManager client = ClientManager.createClient();

    try {
      client.connectToServer(this,
          URI.create("ws://127.0.0.1:8765"));
      latch.await();
    } catch (DeploymentException | InterruptedException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @OnOpen
  public void onOpen(Session session) throws IOException, EncodeException {
    //var registration = Map.of("type", "REGISTER", "token", System.getenv("TOKEN"));
    var registration = Map.of("type", "REGISTER");
    session.getBasicRemote().sendObject(registration);
  }

  @OnMessage
  public void processMessageFromServer(GameMessage receivedMessage,
                                       Session session) throws IOException, EncodeException {
    var nextMove = Map.of("type", "GAMESTATE",
        "tick", receivedMessage.tick(),
    //    "actions", botSolver.getAnswer(new GameMessage(1, new Question(Stream.of(Totem.S, Totem.S).map(TotemQuestion::new).collect(Collectors.toList())))));
        "actions", botSolver.getAnswer(new GameMessage(0, new TotemQuestionBuilder().build(10))));

    session.getBasicRemote().sendObject(nextMove);
    onClose(null, null);
  }

  @SuppressWarnings("unused")
  @OnClose
  public void onClose(Session session, CloseReason closeReason) {
    latch.countDown();
  }
}