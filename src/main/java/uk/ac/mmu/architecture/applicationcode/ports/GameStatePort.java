package uk.ac.mmu.architecture.applicationcode.ports;

public interface GameStatePort {
  String getStateDescription();

  boolean isFinished();
}
