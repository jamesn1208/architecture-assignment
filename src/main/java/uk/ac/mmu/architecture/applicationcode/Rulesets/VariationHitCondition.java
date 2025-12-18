package uk.ac.mmu.architecture.applicationcode.Rulesets;

import uk.ac.mmu.architecture.applicationcode.Boards.Board;
import uk.ac.mmu.architecture.applicationcode.Players.Player;

public class VariationHitCondition implements BaseHitCondition {
  @Override
  public Player hitPlayer(
      String targetPosition, Player[] players, Player currentPlayer, Board board) {
    for (Player player : players) {
      if (player == currentPlayer) {
        continue;
      }
      String playerLocation = board.getPlayerLocation(player);
      if (playerLocation != null && playerLocation.equals(targetPosition)) {
        return player;
      }
    }
    return null;
  }
}
