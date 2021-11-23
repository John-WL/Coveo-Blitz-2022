const { GameMessage } = require("../GameInterface");

class LocalGameClient {
  /**
   * @param {Solver} solver
   */
  constructor(solver) {
    console.log("[Running in local mode]");
    const sample = new GameMessage({
      tick: 1,
      payload: {
        totems: [{ shape: "I" }],
      },
    });
    solver.getAnswer(sample);
  }
}

module.exports = { LocalGameClient };
