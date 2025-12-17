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

  public String getPlayerLocation(Player player) {
    int tailIndex = positionTailIndices.get(player);
    if (tailIndex != -1) {
      Tail tail = findOwnedTail(player);
      if (tail != null) {
        int clampedIndex = Math.max(0, Math.min(tailIndex, tail.getPositions().length - 1));
        return tail.getPositions()[clampedIndex];
      }
    }
    int trackIndex = positionTrackIndices.get(player);
    return track[trackIndex];
  }

  public String computeTargetPosition(Player player, int roll) {
    Integer currentTailIdx = positionTailIndices.get(player);
    if (currentTailIdx != null && currentTailIdx != -1) {
      Tail owned = findOwnedTail(player);
      if (owned == null) return getPlayerLocation(player);
      int newTailIdx = currentTailIdx + roll;
      int clamped = newTailIdx >= owned.getPositions().length
          ? owned.getPositions().length - 1
          : newTailIdx;
      return owned.getPositions()[clamped];
    }

    int absoluteNew = positionTrackIndices.get(player) + roll;
    TailEntry entry = computeTailEntryFromTrack(player, absoluteNew);
    if (entry != null) {
      int idx = Math.min(entry.steps(), entry.tail().getPositions().length - 1);
      return entry.tail().getPositions()[idx];
    }

    int newIndex = absoluteNew % track.length;
    return track[newIndex];
  }

  public boolean wouldOvershootTail(Player player, int roll) {
    Integer currentTailIdx = positionTailIndices.get(player);
    Tail owned;
    if (currentTailIdx != null && currentTailIdx != -1) {
      owned = findOwnedTail(player);
      if (owned == null) return false;
      int newTailIdx = currentTailIdx + roll;
      return newTailIdx > (owned.getPositions().length - 1);
    }

    int absoluteNew = positionTrackIndices.get(player) + roll;
    TailEntry entry = computeTailEntryFromTrack(player, absoluteNew);
    if (entry == null) return false;
    return entry.steps() > (entry.tail().getPositions().length - 1);
  }

  public boolean movePlayer(Player player, int roll) {
    return applyMove(player, roll);
  }

  private boolean applyMove(Player player, int roll) {
    Integer currentTailIdx = positionTailIndices.get(player);
    if (currentTailIdx != null && currentTailIdx != -1) {
      Tail owned = findOwnedTail(player);
      if (owned == null) return false;
      int newTailIdx = currentTailIdx + roll;
      if (newTailIdx >= owned.getPositions().length) {
        positionTailIndices.put(player, owned.getPositions().length - 1);
      } else {
        positionTailIndices.put(player, newTailIdx);
      }
      return hasPlayerWon(player);
    }

    int absoluteNew = positionTrackIndices.get(player) + roll;
    TailEntry entry = computeTailEntryFromTrack(player, absoluteNew);
    if (entry != null) {
      positionTailIndices.put(player, Math.min(entry.steps(), entry.tail().getPositions().length - 1));
      return hasPlayerWon(player);
    }

    int newIndex = absoluteNew % track.length;
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

  private Tail findOwnedTail(Player player) {
    for (Tail t : tails) {
      if (t.getOwner() == player) return t;
    }
    return null;
  }

  private TailEntry computeTailEntryFromTrack(Player player, int absoluteNew) {
    int currentIndex = positionTrackIndices.get(player);
    int trackLen = track.length;

    for (Tail tail : tails) {
      if (tail.getOwner() != player) continue;
      int tb = tail.getBreakIndex();

      if (currentIndex < tb && absoluteNew >= tb) {
        int stepsIntoTail = absoluteNew - tb;
        return new TailEntry(tail, stepsIntoTail);
      }

      if (absoluteNew >= trackLen) {
        int adjustedTb = tb + trackLen;
        if (currentIndex < adjustedTb && absoluteNew >= adjustedTb) {
          int stepsIntoTail = absoluteNew - adjustedTb;
          return new TailEntry(tail, stepsIntoTail);
        }
      }
    }

    return null;
  }
}
