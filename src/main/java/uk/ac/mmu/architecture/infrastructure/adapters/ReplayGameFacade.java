package uk.ac.mmu.architecture.infrastructure.adapters;

import uk.ac.mmu.architecture.applicationcode.dice.DiceShaker;
import uk.ac.mmu.architecture.applicationcode.dice.PresetDiceShaker;
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

public class ReplayGameFacade {
  private final BaseGame replayGame;

  public ReplayGameFacade(
      int[] rolls, BaseHitCondition hitCondition, BaseWinCondition winCondition, Board board) {
    this(
        rolls,
        hitCondition,
        winCondition,
        board,
        List.of(new ConsoleGameOutputAdapter()),
        List.of(new ConsoleStateOutputAdapter()),
        null);
  }

  public ReplayGameFacade(
      int[] rolls,
      BaseHitCondition hitCondition,
      BaseWinCondition winCondition,
      Board board,
      List<GameOutputPort> gameObservers,
      List<StateOutputPort> stateObservers) {
    this(rolls, hitCondition, winCondition, board, gameObservers, stateObservers, null);
  }

  public ReplayGameFacade(
      int[] rolls,
      BaseHitCondition hitCondition,
      BaseWinCondition winCondition,
      Board board,
      List<GameOutputPort> gameObservers,
      List<StateOutputPort> stateObservers,
      GameState initialState) {
    DiceShaker presetShaker = new PresetDiceShaker(rolls);
    Ruleset replayRuleset = new Ruleset(winCondition, hitCondition, presetShaker);

    GameState state = initialState != null ? initialState : new GameReady(stateObservers);

    this.replayGame = new Game(board, replayRuleset, gameObservers, state);
  }

  public void start() {
    replayGame.start();
  }

  public Player getWinner() {
    return this.replayGame.getWinner();
  }
}
