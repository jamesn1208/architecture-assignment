package uk.ac.mmu.architecture.infrastructure.Output;

public interface StateObserver {
  void onStateChange(String stateDescription);
  void onFinalState(String finalStateDescription);
}
