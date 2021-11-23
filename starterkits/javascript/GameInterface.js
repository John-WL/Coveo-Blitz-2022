/**
 * @typedef TickEvent
 * @type {object}
 * @property {number} tick
 * @property {Question} payload
 */

/**
 * @typedef Question
 * @type {object}
 * @property {TotemQuestion[]} totems
 */

/**
 * @typedef TotemQuestion
 * @type {object}
 * @property {Totem} shape
 */

/**
 * @typedef { 'I' | 'O' | 'J' | 'L' | 'S' | 'Z' | 'T'} Totem
 */

/**
 * @typedef Answer
 * @type {object}
 * @property {TotemAnswer[]} totems
 */

/**
 * @typedef TotemAnswer
 * @type {object}
 * @property {Totem} shape
 * @property {CoordinatePair[]} coordinates
 */

/**
 * @typedef {[number, number]} CoordinatePair
 */

/**
 * @implements {TickEvent}
 */
class GameMessage {
  /**
   * @type {number}
   * @readonly
   */
  tick;
  /**
   * @type {Question}
   * @readonly
   */
  payload;

  /**
   *
   * @param {TickEvent} rawTick
   */
  constructor(rawTick) {
    Object.assign(this, rawTick);
  }
}

module.exports = {
  GameMessage,
};
