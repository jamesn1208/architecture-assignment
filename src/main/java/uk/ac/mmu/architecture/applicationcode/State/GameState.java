package uk.ac.mmu.architecture.applicationcode.State;

public interface GameState {
  void startGame();
  GameState nextState();
}
