package uk.ac.mmu.architecture.infrastructure.states;

import uk.ac.mmu.architecture.applicationcode.ports.GameBoundary;
import uk.ac.mmu.architecture.applicationcode.ports.StateOutputPort;
import uk.ac.mmu.architecture.applicationcode.states.GameState;

import java.util.List;

public class GameFinished implements GameState {
  private final List<StateOutputPort> observers;

  public GameFinished(List<StateOutputPort> observers) {
    this.observers = observers;
  }

  @Override
  public void handle(GameBoundary game) {
    // Any future game cleanup could go here
    System.out.println("Game has finished!");
  }

  @Override
  public GameState nextState() {
    if (this.observers != null) {
      for (StateOutputPort observer : observers) {
        observer.onFinalState("The game has reached it's final state: Finished.");
      }
    }
    return null; // There is no next state after game is finished
  }
}
