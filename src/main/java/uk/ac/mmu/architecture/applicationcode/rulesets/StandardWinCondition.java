package uk.ac.mmu.architecture.applicationcode.rulesets;

import uk.ac.mmu.architecture.applicationcode.boards.Board;
import uk.ac.mmu.architecture.applicationcode.players.Player;

public class StandardWinCondition implements BaseWinCondition {
  @Override
  public boolean canMove(Board board, Player currentPlayer, int roll) {
    return true;
  }
}
