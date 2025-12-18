package uk.ac.mmu.architecture.applicationcode.Rulesets;

import uk.ac.mmu.architecture.applicationcode.Boards.Board;
import uk.ac.mmu.architecture.applicationcode.Players.Player;

public class StandardHitCondition implements BaseHitCondition {
  @Override
  public Player hitPlayer(
      String targetPosition, Player[] players, Player currentPlayer, Board board) {
    return null; // Standard rules do allow hitting, so always return null
  }
}
