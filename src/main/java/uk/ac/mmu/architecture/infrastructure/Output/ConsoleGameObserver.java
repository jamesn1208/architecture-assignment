package uk.ac.mmu.architecture.infrastructure.Output;

import uk.ac.mmu.architecture.applicationcode.Players.Player;
import uk.ac.mmu.architecture.infrastructure.Utils.Colour;

import java.util.Arrays;

public class ConsoleGameObserver implements GameObserver {
  @Override
  public void onMove(Player player, String oldPosition, int roll) {
    System.out.printf("%s%s has rolled a %s and moved from tile %s to tile %s.%s%n", player.getColour().unicode, player.getName(), roll, oldPosition, player.getCurrentPosition(), Colour.RESET.unicode);
  }

  @Override
  public void onHit(Player movingPlayer, Player hitPlayer) {
    System.out.printf("%s%s%s stays on tile %s because %s%s%s is already on tile %s.%n", movingPlayer.getColour().unicode, movingPlayer.getName(), Colour.RESET.unicode, movingPlayer.getCurrentPosition(), hitPlayer.getColour().unicode ,hitPlayer.getName(), Colour.RESET.unicode, hitPlayer.getCurrentPosition());
  }

  @Override
  public void onCantMove(Player player, int roll) {
    System.out.printf("%s%s%s can't move %s position%s from tile %s because they would overshoot the end.%s%n", player.getColour().unicode, player.getName(), Colour.RESET.unicode, roll, (roll > 1 ? "s" : ""), player.getCurrentPosition(), Colour.RESET.unicode);
  }

  @Override
  public void onStart(Player player) {
    System.out.printf("%s%s is starting the game on tile %s.%s%n", player.getColour().unicode, player.getName(), player.getCurrentPosition(), Colour.RESET.unicode);
  }

  @Override
  public void onEnd(int[] rolls, Player winner) {
    System.out.printf("%s%s has won the game.%s%n", winner.getColour().unicode, winner.getName(), Colour.RESET.unicode);
    System.out.printf("Dice throws: %s%n", Arrays.toString(rolls));
  }
}
