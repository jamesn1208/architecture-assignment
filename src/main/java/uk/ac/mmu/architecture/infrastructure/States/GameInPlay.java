package uk.ac.mmu.architecture.infrastructure.States;

import uk.ac.mmu.architecture.applicationcode.Games.Game;
import uk.ac.mmu.architecture.infrastructure.Output.StateObserver;

public class GameInPlay implements GameState {
  private final StateObserver[] observers;

  public GameInPlay(StateObserver[] observers) {
    this.observers = observers;
  }

  @Override
  public void handle(Game game) {
    // Any future game logic could go here (that needs to be run whilst the game is also in play)
    System.out.println("Game is in play!");
  }

  @Override
  public GameState nextState() {
    for (StateObserver observer : this.observers) {
      observer.onStateChange("The game state has transitioned from InPlay to Finished.");
    }
    return new GameFinished(this.observers);
  }
}
