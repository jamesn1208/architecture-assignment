package uk.ac.mmu.architecture.infrastructure.States;

import uk.ac.mmu.architecture.applicationcode.Games.Game;

public interface GameState {
  void handle(Game game);
  GameState nextState();
}
