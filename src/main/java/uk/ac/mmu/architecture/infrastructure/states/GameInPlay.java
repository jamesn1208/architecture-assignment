package uk.ac.mmu.architecture.infrastructure.states;

import uk.ac.mmu.architecture.applicationcode.ports.GameBoundary;
import uk.ac.mmu.architecture.applicationcode.ports.StateOutputPort;
import uk.ac.mmu.architecture.applicationcode.states.GameState;

import java.util.List;

public class GameInPlay implements GameState {
  private final List<StateOutputPort> observers;

  public GameInPlay(List<StateOutputPort> observers) {
    this.observers = observers;
  }

  @Override
  public void handle(GameBoundary game) {
    // Any future game logic could go here (that needs to be run whilst the game is also in play)
    System.out.println("Game is in play!");
  }

  @Override
  public GameState nextState() {
    if (this.observers != null) {
      for (StateOutputPort observer : this.observers) {
        observer.onStateChange("The game state has transitioned from InPlay to Finished.");
      }
    }
    return new GameFinished(this.observers);
  }
}
