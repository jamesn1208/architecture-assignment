package uk.ac.mmu.architecture.applicationcode.boards;

import uk.ac.mmu.architecture.applicationcode.players.Player;

import java.util.Arrays;

public record Tail(String[] positions, int breakIndex, Player owner) {
  @Override
  public String toString() {
    return "Tail{"
        + "positions="
        + Arrays.toString(positions)
        + ", breakIndex="
        + breakIndex
        + ", owner="
        + owner
        + '}';
  }
}
