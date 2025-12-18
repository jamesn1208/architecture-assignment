package uk.ac.mmu.architecture.applicationcode.games;

import uk.ac.mmu.architecture.applicationcode.players.Player;
import uk.ac.mmu.architecture.applicationcode.states.GameState;

public interface BaseGame {
  Player getWinner();

  void start();

  GameState getState();
}
