package WinConditions;

import Players.Player;

public class VariationWinCondition implements WinCondition {
    @Override
    public boolean canMove(Player player, int roll) {
        return (player.getPathIndex() + roll) <= (player.getPath().length - 1);
    }
}
