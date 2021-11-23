const Game = require("./GameInterface");

class Solver {
  constructor() {
    // This method should be use to initialize some variables you will need throughout the challenge.
  }

  /**
   * Here is where the magic happens, for now the answer is a single 'I'. I bet you can do better ;)
   *
   * @param {Game.GameMessage} gameMessage
   * @returns {Game.Answer}
   */
  getAnswer(gameMessage) {
    const question = gameMessage.payload;
    console.log("Received Question: ", JSON.stringify(question));

    const totems = [
      {
        shape: "I",
        coordinates: [
          [0, 0],
          [1, 0],
          [2, 0],
          [3, 0],
        ],
      },
    ];

    const answer = { totems };
    console.log("Sending Answer: ", JSON.stringify(answer));
    return answer;
  }
}

module.exports = { Solver };
