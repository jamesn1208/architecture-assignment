package uk.ac.mmu.architecture.infrastructure.Output;

import uk.ac.mmu.architecture.applicationcode.Players.Player;

public class ConsoleOutputObserver implements OutputObserver {
  @Override
  public void onMove(Player player, String oldPosition, int roll) {
    System.out.printf("%s%s has rolled a %s and moved from tile %s to tile %s.%s%n", player.getColour().unicode, player.getName(), roll, oldPosition, player.getCurrentPosition(), Colour.RESET.unicode);
  }

  @Override
  public void onHit(Player movingPlayer, Player hitPlayer) {
    System.out.printf("%s stays on tile %s because %s is already on tile %s.%n", movingPlayer.getName(), movingPlayer.getCurrentPosition(), hitPlayer.getName(), hitPlayer.getCurrentPosition());
  }

  @Override
  public void onCantMove(Player player, int roll) {
    System.out.printf("%s%s can't move %s position(s) from tile %s because they would overshoot the end.%s%n", player.getColour().unicode, player.getName(), roll, player.getCurrentPosition(), Colour.RESET.unicode);
  }

  @Override
  public void onStart(Player player) {
    System.out.printf("%s%s is starting the game on tile %s.%s%n", player.getColour().unicode, player.getName(), player.getCurrentPosition(), Colour.RESET.unicode);
  }
}
