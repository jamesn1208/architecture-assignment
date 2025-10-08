package HitConditions;

import Players.Player;

public interface HitCondition {
  boolean movePlayer(Player[] players, Player currentPlayer, int roll);
}
