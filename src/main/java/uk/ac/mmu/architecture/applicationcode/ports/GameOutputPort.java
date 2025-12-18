package uk.ac.mmu.architecture.applicationcode.ports;

import uk.ac.mmu.architecture.applicationcode.players.Player;

public interface GameOutputPort {
  void onMove(Player player, String oldPosition, int roll);

  void onHit(Player movingPlayer, Player hitPlayer);

  void onCantMove(Player player, int roll);

  void onStart(Player player);

  void onEnd(int[] rolls, Player winner);
}

