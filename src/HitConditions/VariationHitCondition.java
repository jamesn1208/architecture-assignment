package HitConditions;

import Players.Player;

public class VariationHitCondition implements HitCondition {
    @Override
    public boolean movePlayer(Player[] players, Player currentPlayer, int roll) {
        String targetTile;

        try {
            targetTile = currentPlayer.getPath()[currentPlayer.getPathIndex() + roll];
        } catch (ArrayIndexOutOfBoundsException e) {
            return true; // If this is past the end of the array then other plays cannot be in the current user's tail, so they can move
        }

        for (Player player : players) {
            if (player.getPosition().equals(targetTile)) {
                System.out.println(player.getName() + " is on tile " + targetTile + " so " + currentPlayer.getName() + " cannot move there, their turn is forfeit.");
                return false; // Cannot move to a tile already occupied by another player
            }
        }

        return true;
    }
}
