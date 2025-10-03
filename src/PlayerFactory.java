import Boards.Board;

public class PlayerFactory {
    private static final String[] names = {"Red", "Blue", "Green", "Yellow"};
    private static final float[] multipliers = {0.25f, 0.5f, 0.75f};

    public static Player[] manufacture(int numOfPlayers, Board board) {
        if (numOfPlayers < 1 || numOfPlayers > 4) {
            throw new IllegalArgumentException("Number of players must be between 1 and 4.");
        }

        Player[] players = new Player[numOfPlayers];
        players[0] = new Player(names[0], 1);
        for (int i = 1; i < numOfPlayers; i++) {
            players[i] = new Player(names[i], (i * 9) + 1);

            //players[i] = new Player(names[i], (int) (board.numberOfTiles * multipliers[i - 1]) + 1);
        }

        return players;
    }
}
