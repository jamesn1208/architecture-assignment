package Players;

import Boards.Board;

import java.util.Arrays;

import static Utils.Constants.*;

public class PlayerFactory {
  public static Player[] manufacture(int numOfPlayers, Board board) {
    if (numOfPlayers < 1) {
      throw new IllegalArgumentException("At least 1 player required for the game to run.");
    }

    Player[] players = new Player[numOfPlayers];

    for (int index = 0; index < numOfPlayers; index++) {
      String[] normalTiles = new String[board.getNumberOfTiles()];
      int normalTilesIndex = 0;

      int startPos = (index * PLAYER_TILE_GAP) + 1;
      int iters = 0;

      // Up to the end of the board
      for (int i = startPos; i <= board.getNumberOfTiles(); i++) {
        normalTiles[normalTilesIndex] = String.valueOf(i);
        iters += 1;
        normalTilesIndex += 1;
      }

      // Reset back to 1, continue for the remaining iterations
      for (int i = 1; i <= (board.getNumberOfTiles() - iters); i++) {
        normalTiles[normalTilesIndex] = String.valueOf(i);
        normalTilesIndex += 1;
      }

      // Create a new Array for the tiles in the tail for this player
      String[] tailTiles =
          board.getTailMap().get(Integer.valueOf(normalTiles[normalTiles.length - 1]));

      // Combine the two Arrays
      String[] path = Arrays.copyOf(normalTiles, normalTiles.length + tailTiles.length);
      System.arraycopy(tailTiles, 0, path, normalTiles.length, tailTiles.length);

      // Create player, add to the 'players' Array
      players[index] = new Player(NAMES[index], path[0], path);
    }

    return players;
  }
}
