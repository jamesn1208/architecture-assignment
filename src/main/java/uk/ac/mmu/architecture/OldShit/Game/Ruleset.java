package uk.ac.mmu.architecture.OldShit.Game;

import uk.ac.mmu.architecture.OldShit.HitConditions.HitCondition;
import uk.ac.mmu.architecture.OldShit.WinConditions.WinCondition;

public class Ruleset {
  public int numberOfDice;
  public int numberOfPlayers;
  public WinCondition winCondition;
  public HitCondition hitCondition;

  public Ruleset(
      int numberOfDice, int numberOfPlayers, WinCondition winCondition, HitCondition hitCondition) {
    // Input validation
    if (numberOfDice < 1) {
      throw new IllegalArgumentException("There must be at least one die.");
    }
    if (numberOfPlayers < 1 || numberOfPlayers > 4) {
      throw new IllegalArgumentException("Number of players must be between 1 and 4.");
    }

    // Assignment
    this.numberOfDice = numberOfDice;
    this.numberOfPlayers = numberOfPlayers;
    this.winCondition = winCondition;
    this.hitCondition = hitCondition;
  }

  @Override
  public String toString() {
    return "Ruleset(numberOfDice="
        + numberOfDice
        + ", numberOfPlayers="
        + numberOfPlayers
        + ", winCondition="
        + winCondition.getClass().getSimpleName()
        + ", hitCondition="
        + hitCondition.getClass().getSimpleName()
        + ")";
  }
}
