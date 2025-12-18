package uk.ac.mmu.architecture.applicationcode.Rulesets;

import uk.ac.mmu.architecture.applicationcode.Boards.Board;
import uk.ac.mmu.architecture.applicationcode.Players.Player;

public interface BaseHitCondition {
  Player hitPlayer(String targetPosition, Player[] players, Player currentPlayer, Board board);
}
