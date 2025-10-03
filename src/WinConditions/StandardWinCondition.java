package WinConditions;

public class StandardWinCondition implements WinCondition {
    @Override
    public boolean hasWon(int PlayerPosition, int WinningPosition, int Roll) {
        return (PlayerPosition + Roll) >= WinningPosition;
    }
}
