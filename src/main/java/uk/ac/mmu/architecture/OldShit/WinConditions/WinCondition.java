package uk.ac.mmu.architecture.OldShit.WinConditions;

import uk.ac.mmu.architecture.OldShit.Players.Player;

public interface WinCondition {
  boolean canMove(Player player, int roll);
//
//  default boolean hasWon(Player player) {
//    // The player is at the final position in their path (win)
//    return player.getPathIndex() == (player.getPath().length - 1);
//  }
}
