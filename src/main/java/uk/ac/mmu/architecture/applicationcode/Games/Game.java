package uk.ac.mmu.architecture.applicationcode.Games;

import uk.ac.mmu.architecture.applicationcode.Boards.Board;
import uk.ac.mmu.architecture.applicationcode.Players.Player;
import uk.ac.mmu.architecture.applicationcode.Rulesets.Ruleset;
import uk.ac.mmu.architecture.infrastructure.Output.GameObserver;
import uk.ac.mmu.architecture.infrastructure.Output.StateObserver;
import uk.ac.mmu.architecture.infrastructure.States.*;

import java.util.ArrayList;
import java.util.List;

public class Game implements BaseGame {
  private final Board board;
  private final Ruleset rules;
  private Player winner;
  private final Player[] players;
  private final GameObserver[] observers;
  private GameState state;
  private final List<Integer> rolls = new ArrayList<>();

  public Game(
      Board board, Ruleset rules, GameObserver[] gameObservers, StateObserver[] stateObservers) {
    this.state = new GameReady(stateObservers);
    this.board = board;
    this.rules = rules;
    this.players = this.board.getPlayers();
    this.observers = gameObservers;
    this.state.handle(this);
  }

  public void validate() {
    for (Player player : this.players) {
      if (!player.validate()) {
        throw new IllegalArgumentException(
            "Player " + player.getName() + " must have a starting position on the board.");
      }
    }
    if (this.observers.length == 0) {
      throw new IllegalArgumentException("At least one OutputObserver must be provided.");
    }
    if (this.players.length < 2) {
      throw new IllegalArgumentException("At least two players are required to start the game.");
    }
    if (this.players != this.board.getPlayers()) {
      throw new IllegalArgumentException("Players provided do not match players on the board.");
    }
  }

  @Override
  public GameState getState() {
    return state;
  }

  @Override
  public Player getWinner() {
    return this.winner;
  }

  @Override
  public void start() {
    this.state = this.state.nextState();
    this.state.handle(this);

    System.out.println("Starting game with ruleset: " + this.rules.toString());
    for (Player player : this.players) {
      for (GameObserver observer : observers) {
        observer.onStart(player);
      }
    }

    while (this.state instanceof GameInPlay) {
      for (Player player : this.players) {
        int roll = this.rules.diceShaker().shake();
        this.rolls.add(roll);

        if (!this.rules.winCondition().canMove(this.board, player, roll)) {
          for (GameObserver observer : observers) {
            observer.onCantMove(player, roll);
          }
          continue; // Skip to the next player if they cannot move (overshot end position for
                    // variation)
        }

        String targetPosition = this.board.computeTargetPosition(player, roll);
        Player hitPlayer =
            this.rules.hitCondition().hitPlayer(targetPosition, this.players, player, this.board);
        if (hitPlayer != null) {
          for (GameObserver observer : observers) {
            observer.onHit(player, hitPlayer);
          }
          continue; // Skip to the next player if they hit someone (do not move - variation)
        }

        // Update the player's position on the board
        String oldPosition = this.board.getPlayerLocation(player);
        boolean hasWon = this.board.movePlayer(player, roll);
        player.setCurrentPosition(this.board.getPlayerLocation(player));

        for (GameObserver observer : observers) {
          observer.onMove(player, oldPosition, roll);
        }

        // Handle the game state if the player has won
        if (hasWon) {
          this.winner = player;

          for (GameObserver observer : observers) {
            observer.onEnd(this.rolls.stream().mapToInt(i -> i).toArray(), this.winner);
          }

          this.state = this.state.nextState();
          this.state.handle(this);
          break;
        }
      }
    }
  }
}
