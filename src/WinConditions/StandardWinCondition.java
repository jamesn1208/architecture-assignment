package WinConditions;

import Players.Player;

public class StandardWinCondition implements WinCondition {
    @Override
    public boolean canMove(Player player, int roll) {
        return true;
    }
}
