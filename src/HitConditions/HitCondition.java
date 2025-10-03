package HitConditions;
import java.util.ArrayList;

public interface HitCondition {
    boolean movePlayer(int targetTile, ArrayList<Integer> playerPositions);
}
