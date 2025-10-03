package HitConditions;

import java.util.ArrayList;

public class StandardHitCondition implements HitCondition {
    @Override
    public boolean movePlayer(int targetTile, ArrayList<Integer> playerPositions) {
        return true;
    }
}
