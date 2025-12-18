package uk.ac.mmu.architecture.applicationcode.Rulesets;

import uk.ac.mmu.architecture.applicationcode.Dice.DiceShaker;

public record Ruleset(
    BaseWinCondition winCondition, BaseHitCondition hitCondition, DiceShaker diceShaker) {
  @Override
  public String toString() {
    return "Ruleset{"
        + "winCondition="
        + winCondition.getClass().getSimpleName()
        + ", hitCondition="
        + hitCondition.getClass().getSimpleName()
        + ", diceShaker="
        + diceShaker.getClass().getSimpleName()
        + '}';
  }
}
