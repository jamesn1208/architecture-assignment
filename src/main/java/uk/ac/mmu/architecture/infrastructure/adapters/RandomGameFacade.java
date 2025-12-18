package uk.ac.mmu.architecture.infrastructure.adapters;

import uk.ac.mmu.architecture.applicationcode.dice.DiceShaker;
import uk.ac.mmu.architecture.applicationcode.boards.Board;
import uk.ac.mmu.architecture.applicationcode.players.Player;
import uk.ac.mmu.architecture.applicationcode.rulesets.BaseHitCondition;
import uk.ac.mmu.architecture.applicationcode.rulesets.BaseWinCondition;
import uk.ac.mmu.architecture.applicationcode.rulesets.Ruleset;
import uk.ac.mmu.architecture.applicationcode.ports.GameOutputPort;
import uk.ac.mmu.architecture.applicationcode.ports.StateOutputPort;
import uk.ac.mmu.architecture.applicationcode.states.GameState;
import uk.ac.mmu.architecture.applicationcode.games.BaseGame;
import uk.ac.mmu.architecture.applicationcode.games.Game;

import uk.ac.mmu.architecture.infrastructure.states.GameReady;

import java.util.List;

public class RandomGameFacade {
  private final BaseGame randomGame;

  public RandomGameFacade(
      DiceShaker diceShaker, BaseHitCondition hitCondition, BaseWinCondition winCondition, Board board) {
    this(
        diceShaker,
        hitCondition,
        winCondition,
        board,
        List.of(new ConsoleGameOutputAdapter()),
        List.of(new ConsoleStateOutputAdapter()),
        null);
  }

  public RandomGameFacade(
      DiceShaker diceShaker,
      BaseHitCondition hitCondition,
      BaseWinCondition winCondition,
      Board board,
      List<GameOutputPort> gameObservers,
      List<StateOutputPort> stateObservers) {
    this(diceShaker, hitCondition, winCondition, board, gameObservers, stateObservers, null);
  }

  public RandomGameFacade(
      DiceShaker diceShaker,
      BaseHitCondition hitCondition,
      BaseWinCondition winCondition,
      Board board,
      List<GameOutputPort> gameObservers,
      List<StateOutputPort> stateObservers,
      GameState initialState) {
    Ruleset replayRuleset = new Ruleset(winCondition, hitCondition, diceShaker);

    GameState state =
        initialState != null
            ? initialState
            : new GameReady(stateObservers);

    this.randomGame = new Game(board, replayRuleset, gameObservers, state);
  }

  public void start() {
    randomGame.start();
  }

  public Player getWinner() {
    return this.randomGame.getWinner();
  }
}
