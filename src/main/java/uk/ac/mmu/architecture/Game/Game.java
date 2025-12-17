package uk.ac.mmu.architecture.Game;

import uk.ac.mmu.architecture.Players.Player;

public interface Game {
  public void start();
  public Player getWinner();
  public State getState();
}
