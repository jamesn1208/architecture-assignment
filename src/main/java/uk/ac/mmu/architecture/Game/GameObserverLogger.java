package uk.ac.mmu.architecture.Game;

public class GameObserverLogger implements GameObserver {
  @Override
  public void action() {
    System.out.println("GameObserverLogger: An action has occurred in the game.");
  }
}
