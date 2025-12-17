package uk.ac.mmu.architecture.applicationcode.Games;

import uk.ac.mmu.architecture.applicationcode.Boards.Board;
import uk.ac.mmu.architecture.applicationcode.Players.Player;
import uk.ac.mmu.architecture.applicationcode.Rulesets.Ruleset;
import uk.ac.mmu.architecture.infrastructure.Output.OutputObserver;

public class RandomGame implements BaseGame {
  private final Board board;
  private final Ruleset rules;
  private Player winner;
  private final Player[] players;
  private final OutputObserver[] observers;

  public RandomGame(Board board, Ruleset rules, OutputObserver[] observers) {
    this.board = board;
    this.rules = rules;
    this.players = this.board.getPlayers();
    this.observers = observers;

    for (Player player : this.players) {
      if (!player.validate()) {
        throw new IllegalArgumentException(
            "Player " + player.getName() + " must have a starting position on the board.");
      }
    }
  }

  @Override
  public Player getWinner() {
    return this.winner;
  }

  @Override
  public void start() {
    System.out.println("Starting Random Game with ruleset: " + this.rules.toString());
    for (Player player : this.players) {
      for (OutputObserver observer : observers) {
        observer.onStart(player);
      }
    }
    while (true) {
      for (Player player : this.players) {
        int roll = this.rules.diceShaker().shake();

        if (!this.rules.winCondition().canMove(this.board, player, roll)) {
          for (OutputObserver observer : observers) {
            observer.onCantMove(player, roll);
          }
          continue; // Skip to the next player if they cannot move
        }

        Player hitPlayer = this.rules.hitCondition().hitPlayer(this.players, player, roll);
        if (hitPlayer != null) {
          for (OutputObserver observer : observers) {
            observer.onHit(player, hitPlayer);
          }
          continue; // Skip to the next player if they cannot move
        }

        String oldPosition = this.board.getPlayerLocation(player);
        boolean hasWon = this.board.movePlayer(player, roll);
        player.setCurrentPosition(this.board.getPlayerLocation(player));

        for (OutputObserver observer : observers) {
          observer.onMove(player, oldPosition, roll);
        }

        if (hasWon) {
          this.winner = player;
          return; // End the game if there's a winner
        }
      }
    }
  }
}
