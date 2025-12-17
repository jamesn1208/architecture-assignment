package uk.ac.mmu.architecture.applicationcode.Boards;

import uk.ac.mmu.architecture.applicationcode.Players.Player;

import java.util.Arrays;
import java.util.HashMap;

public class Board {
  private final Player[] players;
  private final HashMap<Player, Integer> positionTrackIndices = new HashMap<>();
  private final HashMap<Player, Integer> positionTailIndices = new HashMap<>();
  private final String[] track;
  private final Tail[] tails;

  public Board(Player[] players, String[] track, Tail[] tails) {
    this.players = players;
    this.track = track;
    this.tails = tails;

    for (Player player : players) {
      positionTrackIndices.put(player, player.getStartIndex());
      positionTailIndices.put(player, -1);
    }
  }

  @Override
  public String toString() {
    return "Board{" +
        "players=" + Arrays.toString(players) +
        ", positionTrackIndices=" + positionTrackIndices +
        ", positionTailIndices=" + positionTailIndices +
        ", track=" + Arrays.toString(track) +
        ", tails=" + Arrays.toString(tails) +
        '}';
  }

  public HashMap<Player, Integer> getTrackPositionIndices() {
    return positionTrackIndices;
  }

  public HashMap<Player, Integer> getTailPositionIndices() {
    return positionTailIndices;
  }

  public String getPlayerLocation(Player player) {
    int tailIndex = positionTailIndices.get(player);
    if (tailIndex != -1) {
      Tail tail = null;
      for (Tail t : this.tails) {
        if (t.getOwner() == player) {
          tail = t;
          break;
        }
      }
      if (tail != null) {
        int clampedIndex = Math.max(0, Math.min(tailIndex, tail.getPositions().length - 1));
        return tail.getPositions()[clampedIndex];
      }
    }
    int trackIndex = positionTrackIndices.get(player);
    return track[trackIndex];
  }

  public boolean movePlayer(Player player, int roll) {
    // If player is already in their tail, advance inside the tail
    Integer currentTailIdx = positionTailIndices.get(player);
    if (currentTailIdx != null && currentTailIdx != -1) {
      Tail owned = null;
      for (Tail t : tails) {
        if (t.getOwner() == player) {
          owned = t;
          break;
        }
      }
      if (owned == null) return false;
      int newTailIdx = currentTailIdx + roll;
      if (newTailIdx >= owned.getPositions().length) {
        positionTailIndices.put(player, owned.getPositions().length - 1);
      } else {
        positionTailIndices.put(player, newTailIdx);
      }
      return hasPlayerWon(player);
    }

    int currentIndex = positionTrackIndices.get(player);
    int trackLen = track.length;
    int absoluteNew = currentIndex + roll; // may be >= trackLen (wrap)

    // Check for entering owner's tail taking wrapping into account
    for (Tail tail : tails) {
      if (tail.getOwner() != player) continue;

      int tb = tail.getBreakIndex();

      // Case 1: no wrap (absoluteNew within same loop)
      if (currentIndex < absoluteNew && currentIndex < tb && absoluteNew >= tb) {
        int stepsIntoTail = absoluteNew - tb;
        positionTailIndices.put(player, Math.min(stepsIntoTail, tail.getPositions().length - 1));
        return hasPlayerWon(player);
      }

      // Case 2: wrapped around the end of track
      if (absoluteNew >= trackLen) {
        int adjustedTb = tb + trackLen;
        if (currentIndex < adjustedTb && absoluteNew >= adjustedTb) {
          int stepsIntoTail = absoluteNew - adjustedTb;
          positionTailIndices.put(player, Math.min(stepsIntoTail, tail.getPositions().length - 1));
          return hasPlayerWon(player);
        }
      }
    }

    // Normal track move (with wrap)
    int newIndex = absoluteNew % trackLen;
    positionTrackIndices.put(player, newIndex);
    return false;
  }

  private boolean hasPlayerWon(Player player) {
    for (Tail t : tails) {
      if (t.getOwner() == player) {
        Integer idx = positionTailIndices.get(player);
        return idx != null && idx == (t.getPositions().length - 1);
      }
    }
    return false;
  }

  public Player[] getPlayers() {
    return players;
  }
}
