package uk.ac.mmu.architecture.infrastructure.States;

import uk.ac.mmu.architecture.applicationcode.Games.Game;
import uk.ac.mmu.architecture.infrastructure.Output.StateObserver;

public class GameReady implements GameState {
  private final StateObserver[] observers;

  public GameReady(StateObserver[] observers) {
    this.observers = observers;
  }

  @Override
  public void handle(Game game) {
    try {
      game.validate();
    } catch (Exception e) {
      System.out.println("Game validation failed: " + e.getMessage());
      return;
    }
    System.out.println("Game is ready to play!");
  }

  @Override
  public GameState nextState() {
    for (StateObserver observer : this.observers) {
      observer.onStateChange("The game state has transitioned from Ready to InPlay.");
    }
    return new GameInPlay(this.observers);
  }
}
