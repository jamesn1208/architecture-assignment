package Boards;

import java.util.LinkedHashMap;

import static Utils.Constants.*;

public abstract class Board {
    private final int numberOfTiles;
    private final int numberOfTails; // This is equal to the number of players
    private final int tailSize;
    private final LinkedHashMap<Integer, String[]> tailMap = new LinkedHashMap<>();

    public Board(int numberOfTiles, int numberOfTails, int tailSize) {
        this.numberOfTiles = numberOfTiles;
        this.numberOfTails = numberOfTails;
        this.tailSize = tailSize;
        buildTails();
    }

    @Override
    public String toString() {
        return "Board(numberOfTiles=" + numberOfTiles + ", numberOfTails=" + numberOfTails + ", tailSize=" + tailSize + ")";
    }

    private void buildTails() {
        String[] tail = new String[tailSize];
        for (int x = 0; x < tailSize; x++) {
            tail[x] = NAME_CODES[0] + (x + 1);
        }

        tailMap.put((1 + ((numberOfTiles - 1) % numberOfTiles)), tail);

        for (int i = 1; i < numberOfTails; i++) {
            int startPos = (i * PLAYER_TILE_GAP) + 1;

            tail = new String[tailSize];
            for (int x = 0; x < tailSize; x++) {
                tail[x] = NAME_CODES[i] + (x + 1);
            }

            tailMap.put((1 + ((startPos - 1 + numberOfTiles - 1) % numberOfTiles)), tail);
        }
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    public LinkedHashMap<Integer, String[]> getTailMap() {
        return tailMap;
    }
}
