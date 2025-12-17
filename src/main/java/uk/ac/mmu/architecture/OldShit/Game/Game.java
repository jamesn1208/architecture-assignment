package uk.ac.mmu.architecture.OldShit.Game;

import uk.ac.mmu.architecture.OldShit.Players.Player;

public interface Game {
  public void start();
  public Player getWinner();
  public State getState();
}
