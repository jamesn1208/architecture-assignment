package uk.ac.mmu.architecture.applicationcode.Rulesets;

import uk.ac.mmu.architecture.applicationcode.Boards.Board;
import uk.ac.mmu.architecture.applicationcode.Players.Player;

public class VariationWinCondition implements BaseWinCondition {
  @Override
  public boolean canMove(Board board, Player currentPlayer, int roll) {
    return !board.wouldOvershootTail(currentPlayer, roll);
  }
}
