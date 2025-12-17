package uk.ac.mmu.architecture.applicationcode.Rulesets;

import uk.ac.mmu.architecture.OldShit.Dice.DiceShaker;

public record Ruleset(BaseWinCondition winCondition, BaseHitCondition hitCondition, DiceShaker diceShaker) {
}
