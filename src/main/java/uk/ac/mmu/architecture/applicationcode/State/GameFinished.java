package uk.ac.mmu.architecture.applicationcode.State;

import uk.ac.mmu.architecture.applicationcode.Games.Game;

public class GameFinished implements GameState {
  @Override
  public void handle(Game game) {
    System.out.println("Game has finished!");
  }

  @Override
  public GameState nextState() {
    return null; // There is no next state after game is finished
  }
}
