package uk.ac.mmu.architecture.applicationcode.State;

import uk.ac.mmu.architecture.applicationcode.Games.Game;

public class GameReady implements GameState {
  @Override
  public void handle(Game game) {
    try {
      System.out.println("Validating game setup...");
      game.validate();
    } catch (Exception e) {
      System.out.println("Game validation failed: " + e.getMessage());
      return;
    }
    System.out.println("Game is ready to play!");
  }

  @Override
  public GameState nextState() {
    return new GameInPlay();
  }
}
