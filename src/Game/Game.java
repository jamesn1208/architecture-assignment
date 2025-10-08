package Game;

import Boards.Board;
import Players.Player;
import Players.PlayerFactory;
import Utils.Console;

import java.util.Arrays;

public class Game {
  private final Ruleset rules;
  private final Board board;
  public Player[] players;
  private Player winner;

  public Game(Ruleset rules, Board board) {
    if (board.getNumberOfTails() != rules.numberOfPlayers) {
      throw new IllegalArgumentException(
          "Each player must have one tail each on the board. Number of tails on the board: "
              + board.getNumberOfTails()
              + ", number of players: "
              + rules.numberOfPlayers);
    }

    this.players = new Player[rules.numberOfPlayers];
    this.players = PlayerFactory.manufacture(rules.numberOfPlayers, board);
    this.rules = rules;
    this.board = board;
  }

  @Override
  public String toString() {
    return "Game(rules="
        + rules
        + ", board="
        + board.getClass()
        + ", players="
        + Arrays.toString(players)
        + ")";
  }

  public Player getWinner() {
    return winner;
  }

  public void start() {
    System.out.println(
        "++ Starting a new game ++"
            + "\nRules: "
            + rules
            + "\nBoard: "
            + board
            + "\n+++++++++++++++++++++++++"
            + "\n");

    while (true) {
      for (Player player : players) {
        boolean turn = takeTurn(player);
        if (turn) {
          return;
        }
      }
    }
  }

  private boolean takeTurn(Player player) {
    int roll = rollDie();
    Console.log(player.getName() + " rolled a " + roll, player.getName());

    if (!rules.hitCondition.movePlayer(players, player, roll)) {
      return false;
    }

    if (rules.winCondition.canMove(player, roll)) {
      player.move(roll);
    } else {
      Console.log(
          player.getName()
              + " overshoots and forfeits their turn, remaining at "
              + player.getPosition(),
          player.getName());
      return false;
    }

    if (rules.winCondition.hasWon(player)) {
      Console.log(player.getName() + " has won the game!", player.getName());
      winner = player;
      return true;
    }

    return false;
  }

  private int rollDie() {
    int roll = 0;

    for (int i = 0; i < rules.numberOfDice; i++) {
      roll += (int) (Math.random() * 6) + 1;
    }

    return roll;
  }
}
