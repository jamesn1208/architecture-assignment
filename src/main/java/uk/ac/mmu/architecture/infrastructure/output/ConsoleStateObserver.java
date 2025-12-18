package uk.ac.mmu.architecture.infrastructure.output;

import uk.ac.mmu.architecture.applicationcode.ports.StateOutputPort;

public class ConsoleStateObserver implements StateOutputPort {
  @Override
  public void onStateChange(String stateDescription) {
    System.out.println("Game state changed: " + stateDescription);
  }

  @Override
  public void onFinalState(String finalStateDescription) {
    System.out.println("Final game state: " + finalStateDescription);
  }
}
