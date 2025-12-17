package uk.ac.mmu.architecture.applicationcode.Games;

import uk.ac.mmu.architecture.applicationcode.Players.Player;
import uk.ac.mmu.architecture.infrastructure.States.GameState;

public interface BaseGame {
  Player getWinner();
  void start();
  GameState getState();
}
