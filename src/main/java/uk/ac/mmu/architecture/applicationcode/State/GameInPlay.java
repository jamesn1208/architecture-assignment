package uk.ac.mmu.architecture.applicationcode.State;

import uk.ac.mmu.architecture.applicationcode.Games.Game;

public class GameInPlay implements GameState {
  @Override
  public void handle(Game game) {
    System.out.println("Game is in play!");
  }

  @Override
  public GameState nextState() {
    return new GameFinished();
  }
}
