const WebSocket = require("ws");
const { GameMessage } = require("../GameInterface");

class WebSocketGameClient {
  /**
   * @param {Solver} solver
   */
  constructor(solver) {
    const webSocket = new WebSocket("ws://0.0.0.0:8765");
    /**
     * @param {WebSocket.Event} event
     */
    webSocket.onopen = (event) => {
      webSocket.send(
        JSON.stringify({ type: "REGISTER", token: process.env.TOKEN })
      );
    };
    /**
     * @param {WebSocket.MessageEvent} message
     */
    webSocket.onmessage = (message) => {
      const rawGameMessage = JSON.parse(message.data.toString());

      if (rawGameMessage.type === "ERROR") {
        console.error(rawGameMessage);
        return;
      }

      const gameMessage = new GameMessage(rawGameMessage);

      webSocket.send(
        JSON.stringify({
          type: "COMMAND",
          tick: gameMessage.tick,
          actions: solver.getAnswer(gameMessage),
        })
      );
    };
  }
}

module.exports = {
  WebSocketGameClient,
};
