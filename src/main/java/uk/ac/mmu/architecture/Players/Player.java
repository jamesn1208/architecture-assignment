package uk.ac.mmu.architecture.Players;

public class Player {
  private final String name;
  private String currentPosition;

  public Player(String name) {
    this.name = name;
  }

  public String getPosition() {
    return currentPosition;
  }

  public String getName() {
    return name;
  }

  public void setPosition(String position) {
    this.currentPosition = position;
  }

  @Override
  public String toString() {
    return "Player{name="
        + name
        + ", currentPosition="
        + currentPosition
        + "}";
  }
}
