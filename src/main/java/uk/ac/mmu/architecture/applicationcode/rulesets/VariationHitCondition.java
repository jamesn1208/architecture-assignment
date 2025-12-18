package uk.ac.mmu.architecture.applicationcode.rulesets;

import uk.ac.mmu.architecture.applicationcode.boards.Board;
import uk.ac.mmu.architecture.applicationcode.players.Player;

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
