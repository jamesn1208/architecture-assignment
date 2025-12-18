package uk.ac.mmu.architecture.applicationcode.Boards;

import uk.ac.mmu.architecture.applicationcode.Players.Player;

import java.util.Arrays;

public class Tail {
  private final String[] positions;
  private final int breakIndex;
  private final Player owner;

  public Tail(String[] positions, int breakIndex, Player owner) {
    this.positions = positions;
    this.breakIndex = breakIndex;
    this.owner = owner;
  }

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

  public String[] getPositions() {
    return positions;
  }

  public int getBreakIndex() {
    return breakIndex;
  }

  public Player getOwner() {
    return owner;
  }
}
