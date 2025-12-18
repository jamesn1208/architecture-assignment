package uk.ac.mmu.architecture.infrastructure.adapters;

import org.springframework.stereotype.Component;
import uk.ac.mmu.architecture.applicationcode.ports.GameOutputPort;
import uk.ac.mmu.architecture.infrastructure.output.ConsoleGameObserver;
import uk.ac.mmu.architecture.applicationcode.players.Player;

@Component
public class ConsoleGameOutputAdapter implements GameOutputPort {
  private final ConsoleGameObserver delegate;

  public ConsoleGameOutputAdapter() {
    this.delegate = new ConsoleGameObserver();
  }

  public ConsoleGameOutputAdapter(ConsoleGameObserver delegate) {
    this.delegate = delegate;
  }

  @Override
  public void onMove(Player player, String oldPosition, int roll) {
    delegate.onMove(player, oldPosition, roll);
  }

  @Override
  public void onHit(Player movingPlayer, Player hitPlayer) {
    delegate.onHit(movingPlayer, hitPlayer);
  }

  @Override
  public void onCantMove(Player player, int roll) {
    delegate.onCantMove(player, roll);
  }

  @Override
  public void onStart(Player player) {
    delegate.onStart(player);
  }

  @Override
  public void onEnd(int[] rolls, Player winner) {
    delegate.onEnd(rolls, winner);
  }
}
