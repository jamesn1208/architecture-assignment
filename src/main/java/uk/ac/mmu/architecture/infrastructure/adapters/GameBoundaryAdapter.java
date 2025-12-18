package uk.ac.mmu.architecture.infrastructure.adapters;

import uk.ac.mmu.architecture.applicationcode.ports.GameBoundary;

public class GameBoundaryAdapter implements GameBoundary {
  private final GameBoundary delegate;

  public GameBoundaryAdapter(GameBoundary delegate) {
    this.delegate = delegate;
  }

  @Override
  public void start() {
    delegate.start();
  }

  @Override
  public void validate() {
    delegate.validate();
  }
}
