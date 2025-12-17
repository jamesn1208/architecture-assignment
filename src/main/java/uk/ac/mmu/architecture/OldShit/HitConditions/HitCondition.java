package uk.ac.mmu.architecture.OldShit.HitConditions;

import uk.ac.mmu.architecture.OldShit.Players.Player;

public interface HitCondition {
  boolean movePlayer(Player[] players, Player currentPlayer, int roll);
}
