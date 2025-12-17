package uk.ac.mmu.architecture.applicationcode.Boards;

import uk.ac.mmu.architecture.applicationcode.Players.Player;

public interface BoardFactory {
  Board create(Player[] players, String[] track, Tail[] tails);
  String[] generateTrack();
  Tail[] generateTails();
  Player[] generatePlayers();
}
