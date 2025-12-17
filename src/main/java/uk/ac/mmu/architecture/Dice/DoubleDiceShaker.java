package uk.ac.mmu.architecture.Dice;

public class DoubleDiceShaker implements DiceShaker{
  final DiceShaker component;

  public DoubleDiceShaker(DiceShaker component) {
    this.component = component;
  }

  @Override
  public int shake() {
    return (int) (1 + (Math.random() * 6) + component.shake());
  }
}
