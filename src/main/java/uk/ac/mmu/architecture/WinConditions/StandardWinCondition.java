package uk.ac.mmu.architecture.WinConditions;

import uk.ac.mmu.architecture.Players.Player;

public class StandardWinCondition implements WinCondition {
  @Override
  public boolean canMove(Player player, int roll) {
    return true;
  }
}
