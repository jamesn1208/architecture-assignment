package uk.ac.mmu.architecture.applicationcode.rulesets;

import uk.ac.mmu.architecture.applicationcode.boards.Board;
import uk.ac.mmu.architecture.applicationcode.players.Player;

public interface BaseHitCondition {
  Player hitPlayer(String targetPosition, Player[] players, Player currentPlayer, Board board);
}
