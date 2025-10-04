package Players;

import WinConditions.WinCondition;
import Utils.Console;

import java.util.Arrays;

public class Player {
    private final String name;
    private final WinCondition winCondition;

    private String currentPosition;

    private final String[] path;
    private int pathIndex = 0;

    public Player(String name, WinCondition winCondition, String startingPosition, String[] path) {
        this.name = name;
        this.winCondition = winCondition;
        this.currentPosition = startingPosition;
        this.path = path;
    }

    public int getPathIndex() {
        return pathIndex;
    }

    public String[] getPath() {
        return path;
    }

    public String getPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }

    public void move(int roll) {
        String oldPosition = currentPosition;

        if (winCondition.canMove(this, roll)) {
            try {
                pathIndex += roll;
                currentPosition = path[pathIndex];
            } catch (ArrayIndexOutOfBoundsException e) {
                Console.log(name + " overshoots!", name);
                pathIndex = path.length - 1;
                currentPosition = path[pathIndex];
            }
            Console.log(name + " moved from " + oldPosition + " to " + currentPosition, name);
        } else {
            Console.log(name + " overshoots and forfeits their turn, remaining at " + currentPosition, name);
        }
    }

    @Override
    public String toString() {
        return "Players.Player(name=" + name + ", currentPosition=" + currentPosition + ", winCondition=" + winCondition.getClass().getSimpleName() + ", path=" + Arrays.toString(path) +  ", pathIndex=" + pathIndex + ")";
    }
}
