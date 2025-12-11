package Game;

public class GameObserverLogger implements GameObserver {
  @Override
  public void action() {
    System.out.println("GameObserverLogger: An action has occurred in the game.");
  }
}
