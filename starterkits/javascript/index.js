const { LocalGameClient } = require("./client/LocalGameClient");
const { WebSocketGameClient } = require("./client/WebSocketGameClient");
const { Solver } = require("./Solver");

const solver = new Solver();
process.env.TOKEN
  ? new WebSocketGameClient(solver)
  : new LocalGameClient(solver);
