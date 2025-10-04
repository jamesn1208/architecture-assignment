package Players;

import Boards.Board;
import WinConditions.WinCondition;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerFactory {
    private static final String[] names = {"Red", "Blue", "Green", "Yellow"};
    private static final int gap = 9; // There is a gap of 9 tiles between each player

    public static Player[] manufacture(int numOfPlayers, Board board, WinCondition winCondition) {
        if (numOfPlayers < 1 || numOfPlayers > 4) {
            throw new IllegalArgumentException("Number of players must be between 1 and 4.");
        }

        Player[] players = new Player[numOfPlayers];

        for (int index = 0; index < numOfPlayers; index++) {
            ArrayList<String> path = new ArrayList<>();
            int startPos;

            if (index == 0) {
                startPos = 1;
            } else {
                startPos = (index * gap) + 1;
            }

            int iters = 0;

            for (int i = startPos; i <= board.getNumberOfTiles(); i++) {
                iters += 1;
                path.add(String.valueOf(i));
            }

            for (int i = 1; i <= (board.getNumberOfTiles() - iters); i++) {
                path.add(String.valueOf(i));
            }

            String[] tail = board.getTailMap().get(Integer.valueOf(path.getLast()));
            path.addAll(Arrays.asList(tail));

            players[index] = new Player(names[index], winCondition, path.getFirst(), path.toArray(new String[0]));
        }

        return players;
    }
}
