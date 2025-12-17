package uk.ac.mmu.architecture.HitConditions;

import uk.ac.mmu.architecture.Players.Player;

public class StandardHitCondition implements HitCondition {
  @Override
  public boolean movePlayer(Player[] players, Player currentPlayer, int roll) {
    return true;
  }
}
