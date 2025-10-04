package Players;

import Boards.Board;
import WinConditions.WinCondition;

import java.util.ArrayList;
import java.util.Arrays;

import static Utils.Constants.*;

public class PlayerFactory {
    public static Player[] manufacture(int numOfPlayers, Board board, WinCondition winCondition) {
        if (numOfPlayers < 1 || numOfPlayers > 4) {
            throw new IllegalArgumentException("Number of players must be between 1 and 4.");
        }

        Player[] players = new Player[numOfPlayers];

        for (int index = 0; index < numOfPlayers; index++) {
            ArrayList<String> path = new ArrayList<>();
            int startPos = (index * PLAYER_TILE_GAP) + 1;
            int iters = 0;

            // Up to the end of the board
            for (int i = startPos; i <= board.getNumberOfTiles(); i++) {
                iters += 1;
                path.add(String.valueOf(i));
            }

            // Reset back to 1, continue for the remaining iterations
            for (int i = 1; i <= (board.getNumberOfTiles() - iters); i++) {
                path.add(String.valueOf(i));
            }

            // Add the tail values to the end of the ArrayList
            String[] tail = board.getTailMap().get(Integer.valueOf(path.getLast()));
            path.addAll(Arrays.asList(tail));

            // Create player, add to the 'players' Array
            players[index] = new Player(NAMES[index], winCondition, path.getFirst(), path.toArray(new String[0]));
        }

        return players;
    }
}
