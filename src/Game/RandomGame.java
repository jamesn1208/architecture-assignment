package Game;

import Players.Player;
import Dice.DiceShaker;

import java.util.Random;

public class RandomGame implements Game {
  private final Ruleset rules;
  private final DiceShaker diceShaker;
  private final Random random;
  private Player winner;
  private State state;

  public RandomGame(Ruleset rules, DiceShaker diceShaker) {
    this.rules  = rules;
    this.diceShaker = diceShaker;
    this.random = new Random();
    this.state  = State.READY;
  }

  @Override
  public void start() {
    this.state = State.IN_PLAY;
    // Implementation of game logic goes here
  }

  @Override
  public Player getWinner() {
    return this.winner;
  }

  @Override
  public State getState() {
    return this.state;
  }

  @Override
  public String toString() {
    return "RandomGame{" +
        "rules=" + rules +
        ", random=" + random +
        ", winner=" + winner +
        ", state=" + state +
        '}';
  }
}
