package uk.ac.mmu.architecture.HitConditions;

import uk.ac.mmu.architecture.Players.Player;

public interface HitCondition {
  boolean movePlayer(Player[] players, Player currentPlayer, int roll);
}
