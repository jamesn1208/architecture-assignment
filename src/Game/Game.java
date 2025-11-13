package Game;

import Players.Player;

public interface Game {
  public void start();
  public Player getWinner();
  public State getState();
}
