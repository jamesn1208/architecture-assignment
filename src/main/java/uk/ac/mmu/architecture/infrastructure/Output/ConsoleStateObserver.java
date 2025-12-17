package uk.ac.mmu.architecture.infrastructure.Output;

public class ConsoleStateObserver implements StateObserver {
  @Override
  public void onStateChange(String stateDescription) {
    System.out.println("Game state changed: " + stateDescription);
  }

  @Override
  public void onFinalState(String finalStateDescription) {
    System.out.println("Final game state: " + finalStateDescription);
  }
}
