package uk.ac.mmu.architecture.infrastructure.Output;

import uk.ac.mmu.architecture.applicationcode.Players.Player;

public interface OutputObserver {
  void onMove(Player player, String newPosition, int roll);
  void onHit(Player movingPlayer, Player hitPlayer);
  void onCantMove(Player player, int roll);
  void onStart(Player player);
}
