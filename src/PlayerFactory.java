import Boards.Board;

public class PlayerFactory {
    private static final String[] names = {"Red", "Blue", "Green", "Yellow"};
    private static final int gap = 9; // There is a gap of 9 tiles between each player

    public static Player[] manufacture(int numOfPlayers, Board board) {
        if (numOfPlayers < 1 || numOfPlayers > 4) {
            throw new IllegalArgumentException("Number of players must be between 1 and 4.");
        }

        // Initialise variables to be consumed below only after validation to avoid wasting memory
        Player[] players = new Player[numOfPlayers];
        int pos;

        // First player always starts at position 1
        players[0] = new Player(names[0], 1, getWinPosition(1, board), getTailIndex(1, board));

        // Determine starting positions for remaining players
        for (int i = 1; i < numOfPlayers; i++) {
            pos = (i * gap) + 1;

            if (board.getNumberOfTiles() >= pos) {
                players[i] = new Player(names[i], pos, getWinPosition(pos, board), getTailIndex(pos, board));
            } else {
                throw new IllegalArgumentException("Board is too small for " + numOfPlayers + " players.");
            }
        }

        return players;
    }

    private static String getWinPosition(int startingPosition, Board board) {
        return board.getTailMap().get(getTailIndex(startingPosition, board))[board.getTailSize() - 1];
    }

    private static int getTailIndex(int startingPosition, Board board) {
        return 1 + (startingPosition - 1 + board.getNumberOfTiles() - 1) % board.getNumberOfTiles();
    }
}
