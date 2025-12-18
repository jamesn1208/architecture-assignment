package uk.ac.mmu.architecture.applicationcode.states;

import uk.ac.mmu.architecture.applicationcode.ports.GameBoundary;

public interface GameState {
  void handle(GameBoundary game);

  GameState nextState();
}

