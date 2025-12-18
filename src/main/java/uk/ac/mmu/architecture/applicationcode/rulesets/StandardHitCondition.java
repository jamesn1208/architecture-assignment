package uk.ac.mmu.architecture.applicationcode.rulesets;

import uk.ac.mmu.architecture.applicationcode.boards.Board;
import uk.ac.mmu.architecture.applicationcode.players.Player;

public class StandardHitCondition implements BaseHitCondition {
  @Override
  public Player hitPlayer(
      String targetPosition, Player[] players, Player currentPlayer, Board board) {
    return null; // Standard rules do allow hitting, so always return null
  }
}
