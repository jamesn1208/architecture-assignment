package uk.ac.mmu.architecture.applicationcode.Games;

import uk.ac.mmu.architecture.applicationcode.Dice.DiceShaker;
import uk.ac.mmu.architecture.applicationcode.Dice.PresetDiceShaker;
import uk.ac.mmu.architecture.applicationcode.Boards.Board;
import uk.ac.mmu.architecture.applicationcode.Players.Player;
import uk.ac.mmu.architecture.applicationcode.Rulesets.BaseHitCondition;
import uk.ac.mmu.architecture.applicationcode.Rulesets.BaseWinCondition;
import uk.ac.mmu.architecture.applicationcode.Rulesets.Ruleset;
import uk.ac.mmu.architecture.infrastructure.Output.ConsoleGameObserver;
import uk.ac.mmu.architecture.infrastructure.Output.ConsoleStateObserver;
import uk.ac.mmu.architecture.infrastructure.Output.GameObserver;
import uk.ac.mmu.architecture.infrastructure.Output.StateObserver;

public class ReplayGameFacade {
  private final BaseGame replayGame;

  public ReplayGameFacade(
      int[] rolls, BaseHitCondition hitCondition, BaseWinCondition winCondition, Board board) {
    DiceShaker presetShaker = new PresetDiceShaker(rolls);
    Ruleset replayRuleset = new Ruleset(winCondition, hitCondition, presetShaker);
    GameObserver[] gameObservers = {new ConsoleGameObserver()};
    StateObserver[] stateObservers = {new ConsoleStateObserver()};
    this.replayGame = new Game(board, replayRuleset, gameObservers, stateObservers);
  }

  public void start() {
    replayGame.start();
  }

  public Player getWinner() {
    return this.replayGame.getWinner();
  }
}
