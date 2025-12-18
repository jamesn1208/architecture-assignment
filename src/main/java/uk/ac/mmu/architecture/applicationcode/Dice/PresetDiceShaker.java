package uk.ac.mmu.architecture.applicationcode.Dice;

public class PresetDiceShaker implements DiceShaker {
  private final int[] presetRolls;
  private int currentIndex;

  public PresetDiceShaker(int[] presetRolls) {
    this.presetRolls = presetRolls;
    this.currentIndex = 0;
  }

  @Override
  public int shake() {
    if (currentIndex >= presetRolls.length) {
      throw new IllegalStateException(
          "No more preset rolls available. Games with pre-set dice rolls should cause the game to end before running out of rolls.");
    }
    return presetRolls[currentIndex++];
  }
}
