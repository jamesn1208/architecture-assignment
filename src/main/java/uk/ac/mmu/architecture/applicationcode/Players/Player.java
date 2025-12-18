package uk.ac.mmu.architecture.applicationcode.Players;

import java.util.Objects;

import uk.ac.mmu.architecture.infrastructure.Utils.Colour;

public class Player {
  public static final Player PLAYER_1 = new Player("Red", "R", Colour.RED);
  public static final Player PLAYER_2 = new Player("Blue", "B", Colour.BLUE);
  public static final Player PLAYER_3 = new Player("Green", "G", Colour.GREEN);
  public static final Player PLAYER_4 = new Player("Yellow", "Y", Colour.YELLOW);
  private final String name;
  private final String shortName;
  private final Colour colour;
  private String currentPosition;
  private int startIndex;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return Objects.equals(name, player.name)
        && Objects.equals(shortName, player.shortName)
        && colour == player.colour;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, shortName, colour);
  }

  @Override
  public String toString() {
    return "Player{"
        + "name='"
        + name
        + '\''
        + ", shortName='"
        + shortName
        + '\''
        + ", currentPosition='"
        + currentPosition
        + '\''
        + ", startIndex="
        + startIndex
        + '}';
  }

  private Player(String name, String shortName, Colour colour) {
    this.name = name;
    this.shortName = shortName;
    this.currentPosition = null; // This is defined by the setter after creation
    this.startIndex = -1; // This is defined by the setter after creation
    this.colour = colour;
  }

  public boolean validate() {
    return currentPosition != null && !currentPosition.isEmpty() && startIndex >= 0;
  }

  public String getCurrentPosition() {
    return currentPosition;
  }

  public Colour getColour() {
    return colour;
  }

  public void setCurrentPosition(String currentPosition) {
    this.currentPosition = currentPosition;
  }

  public String getName() {
    return name;
  }

  public String getShortName() {
    return shortName;
  }

  public int getStartIndex() {
    return startIndex;
  }

  public void setStartIndex(int startIndex) {
    this.startIndex = startIndex;
  }
}
