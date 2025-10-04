package Game;

import HitConditions.HitCondition;
import WinConditions.WinCondition;

public class Ruleset {
    public int numberOfDice;
    public int numberOfPlayers;
    public WinCondition winCondition;
    public HitCondition hitCondition;

    public Ruleset(int numberOfDice, WinCondition winCondition, HitCondition hitCondition, int numberOfPlayers) {
        this.numberOfDice = numberOfDice;
        this.numberOfPlayers = numberOfPlayers;
        this.winCondition = winCondition;
        this.hitCondition = hitCondition;
    }

    @Override
    public String toString() {
        return "Ruleset(numberOfDice=" + numberOfDice
                + ", numberOfPlayers=" + numberOfPlayers
                + ", winCondition=" + winCondition.getClass().getSimpleName()
                + ", hitCondition=" + hitCondition.getClass().getSimpleName()
                + ")";
    }
}