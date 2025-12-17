package uk.ac.mmu.architecture.applicationcode.Games;

import uk.ac.mmu.architecture.applicationcode.Players.Player;

public interface BaseGame {
  Player getWinner();
  void start();
}
