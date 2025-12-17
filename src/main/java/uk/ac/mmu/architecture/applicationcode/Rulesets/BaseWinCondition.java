package uk.ac.mmu.architecture.applicationcode.Rulesets;

import uk.ac.mmu.architecture.applicationcode.Boards.Board;
import uk.ac.mmu.architecture.applicationcode.Players.Player;

public interface BaseWinCondition {
  boolean canMove(Board board, Player currentPlayer, int roll);
}
