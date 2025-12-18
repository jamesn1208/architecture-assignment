package uk.ac.mmu.architecture.infrastructure.output;

import uk.ac.mmu.architecture.applicationcode.players.Player;

public interface GameObserver {
  void onMove(Player player, String newPosition, int roll);

  void onHit(Player movingPlayer, Player hitPlayer);

  void onCantMove(Player player, int roll);

  void onStart(Player player);

  void onEnd(int[] rolls, Player winner);
}
