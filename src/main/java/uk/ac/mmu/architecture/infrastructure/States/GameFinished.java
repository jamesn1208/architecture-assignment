package uk.ac.mmu.architecture.infrastructure.States;

import uk.ac.mmu.architecture.applicationcode.Games.Game;
import uk.ac.mmu.architecture.infrastructure.Output.StateObserver;

public class GameFinished implements GameState {
  private final StateObserver[] observers;

  public GameFinished(StateObserver[] observers) {
    this.observers = observers;
  }

  @Override
  public void handle(Game game) {
    // Any future game cleanup could go here
    System.out.println("Game has finished!");
  }

  @Override
  public GameState nextState() {
    for (StateObserver observer : observers) {
      observer.onFinalState("The game has reached it's final state: Finished.");
    }
    return null; // There is no next state after game is finished
  }
}
