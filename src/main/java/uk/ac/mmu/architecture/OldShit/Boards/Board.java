package uk.ac.mmu.architecture.OldShit.Boards;

import uk.ac.mmu.architecture.OldShit.Utils.Constants;

import java.util.HashMap;

public class Board {
  public static final Board STANDARD_BOARD = new Board(18, 2, 3);
  public static final Board LARGE_BOARD = new Board(36, 4, 6);

  private final int numberOfTiles;
  private final int numberOfTails; // This SHOULD be equal to the number of players
  private final int tailSize;
  private final HashMap<Integer, String[]> tailMap = new HashMap<>();

  public Board(int numberOfTiles, int numberOfTails, int tailSize) {
    this.numberOfTiles = numberOfTiles;
    this.numberOfTails = numberOfTails;
    this.tailSize = tailSize;
    buildTails();
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(numberOfTiles="
        + numberOfTiles
        + ", numberOfTails="
        + numberOfTails
        + ", tailSize="
        + tailSize
        + ")";
  }

  private void buildTails() {
    String[] tail = new String[tailSize];
    for (int x = 0; x < tailSize; x++) {
      tail[x] = Constants.NAME_CODES[0] + (x + 1);
    }

    tailMap.put((1 + ((numberOfTiles - 1) % numberOfTiles)), tail);

    for (int i = 1; i < numberOfTails; i++) {
      int startPos = (i * Constants.PLAYER_TILE_GAP) + 1;

      tail = new String[tailSize];
      for (int x = 0; x < tailSize; x++) {
        tail[x] = Constants.NAME_CODES[i] + (x + 1);
      }

      tailMap.put((1 + ((startPos - 1 + numberOfTiles - 1) % numberOfTiles)), tail);
    }
  }

  public int getNumberOfTiles() {
    return numberOfTiles;
  }

  public int getNumberOfTails() {
    return numberOfTails;
  }

  public HashMap<Integer, String[]> getTailMap() {
    return tailMap;
  }
}
