package uk.ac.mmu.architecture.OldShit.HitConditions;

import uk.ac.mmu.architecture.OldShit.Players.Player;

public class StandardHitCondition implements HitCondition {
  @Override
  public boolean movePlayer(Player[] players, Player currentPlayer, int roll) {
    return true;
  }
}
