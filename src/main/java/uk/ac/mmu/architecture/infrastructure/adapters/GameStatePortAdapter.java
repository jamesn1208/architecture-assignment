package uk.ac.mmu.architecture.infrastructure.adapters;

import uk.ac.mmu.architecture.applicationcode.ports.GameStatePort;
import uk.ac.mmu.architecture.infrastructure.states.GameState;
import uk.ac.mmu.architecture.infrastructure.states.GameFinished;

public class GameStatePortAdapter implements GameStatePort {
  private final GameState delegate;

  public GameStatePortAdapter(GameState delegate) {
    this.delegate = delegate;
  }

  @Override
  public String getStateDescription() {
    // Provide a simple description based on the delegate class name
    return delegate.getClass().getSimpleName();
  }

  @Override
  public boolean isFinished() {
    return delegate instanceof GameFinished;
  }
}
