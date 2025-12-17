package uk.ac.mmu.architecture.Dice;

public class SingleDiceShaker implements DiceShaker{
  @Override
  public int shake() {
    return (int) (1 + (Math.random() * 6));
  }
}
