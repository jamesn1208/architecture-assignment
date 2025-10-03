import HitConditions.HitCondition;
import WinConditions.WinCondition;

public class Ruleset {
    public int numberOfDice;
    public WinCondition winCondition;
    public HitCondition hitCondition;

    public Ruleset(int numberOfDice, WinCondition winCondition, HitCondition hitCondition) {
        this.numberOfDice = numberOfDice;
        this.winCondition = winCondition;
        this.hitCondition = hitCondition;
    }
}