package uk.ac.mmu.architecture.infrastructure.adapters;

import org.springframework.stereotype.Component;
import uk.ac.mmu.architecture.applicationcode.ports.StateOutputPort;
import uk.ac.mmu.architecture.infrastructure.output.ConsoleStateObserver;

@Component
public class ConsoleStateOutputAdapter implements StateOutputPort {
  private final ConsoleStateObserver delegate;

  public ConsoleStateOutputAdapter() {
    this.delegate = new ConsoleStateObserver();
  }

  public ConsoleStateOutputAdapter(ConsoleStateObserver delegate) {
    this.delegate = delegate;
  }

  @Override
  public void onStateChange(String stateDescription) {
    delegate.onStateChange(stateDescription);
  }

  @Override
  public void onFinalState(String finalStateDescription) {
    delegate.onFinalState(finalStateDescription);
  }
}
