package WinConditions;

import Boards.Board;

public interface WinCondition {
    boolean hasWon(int PlayerPosition, int WinningPosition, int Roll);
}
