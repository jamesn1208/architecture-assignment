package uk.ac.mmu.architecture.applicationcode.ports;

public interface StateOutputPort {
  void onStateChange(String stateDescription);

  void onFinalState(String finalStateDescription);
}

