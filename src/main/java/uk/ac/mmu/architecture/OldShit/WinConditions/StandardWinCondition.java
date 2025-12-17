package uk.ac.mmu.architecture.OldShit.WinConditions;

import uk.ac.mmu.architecture.OldShit.Players.Player;

public class StandardWinCondition implements WinCondition {
  @Override
  public boolean canMove(Player player, int roll) {
    return true;
  }
}
