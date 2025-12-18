package uk.ac.mmu.architecture.infrastructure.states;

import uk.ac.mmu.architecture.applicationcode.ports.GameBoundary;
import uk.ac.mmu.architecture.applicationcode.ports.StateOutputPort;
import uk.ac.mmu.architecture.applicationcode.states.GameState;

import java.util.List;

public class GameReady implements GameState {
  private final List<StateOutputPort> observers;

  public GameReady(List<StateOutputPort> observers) {
    this.observers = observers;
  }

  @Override
  public void handle(GameBoundary game) {
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
    if (this.observers != null) {
      for (StateOutputPort observer : this.observers) {
        observer.onStateChange("Game state Ready -> InPlay.");
      }
    }
    return new GameInPlay(this.observers);
  }
}
