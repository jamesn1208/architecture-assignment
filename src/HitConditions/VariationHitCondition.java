package HitConditions;

import java.util.ArrayList;

public class VariationHitCondition implements HitCondition {
    @Override
    public boolean movePlayer(int targetTile, ArrayList<Integer> playerPositions) {
        for (int position : playerPositions) {
            if (position == targetTile) {
                return false; // Cannot move to a tile already occupied by another player
            }
        }
        return true;
    }
}
