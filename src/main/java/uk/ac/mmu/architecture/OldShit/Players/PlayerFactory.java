package uk.ac.mmu.architecture.OldShit.Players;

import static uk.ac.mmu.architecture.OldShit.Utils.Constants.*;

public class PlayerFactory implements BasePlayerFactory {
  @Override
  public Player[] manufacture(int numOfPlayers) {
    if (numOfPlayers < 1) {
      throw new IllegalArgumentException("At least 1 player required for the game to run.");
    }

    Player[] players = new Player[numOfPlayers];

    for (int i = 0; i < numOfPlayers; i++) {
      players[i] = new Player(NAMES[i]);
    }

    return players;
  }
}
