package uk.ac.mmu.architecture.applicationcode.boards;

import uk.ac.mmu.architecture.applicationcode.players.Player;

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
    return "Board{"
        + "players="
        + Arrays.toString(players)
        + ", positionTrackIndices="
        + positionTrackIndices
        + ", positionTailIndices="
        + positionTailIndices
        + ", track="
        + Arrays.toString(track)
        + ", tails="
        + Arrays.toString(tails)
        + '}';
  }

  public String getPlayerLocation(Player player) {
    int tailIndex = positionTailIndices.get(player);
    if (tailIndex != -1) {
      Tail tail = findOwnedTail(player);
      if (tail != null) {
        int clampedIndex = Math.max(0, Math.min(tailIndex, tail.positions().length - 1));
        return tail.positions()[clampedIndex];
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
      int clamped =
          newTailIdx >= owned.positions().length ? owned.positions().length - 1 : newTailIdx;
      return owned.positions()[clamped];
    }

    int absoluteNew = positionTrackIndices.get(player) + roll;
    TailEntry entry = computeTailEntryFromTrack(player, absoluteNew);
    if (entry != null) {
      int adjustedStep =
          Math.max(0, Math.min(entry.steps() - 1, entry.tail().positions().length - 1));
      return entry.tail().positions()[adjustedStep];
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
      return newTailIdx > (owned.positions().length - 1);
    }

    int absoluteNew = positionTrackIndices.get(player) + roll;
    TailEntry entry = computeTailEntryFromTrack(player, absoluteNew);
    if (entry == null) return false;
    int adjustedSteps = Math.max(0, entry.steps() - 1);
    return adjustedSteps > (entry.tail().positions().length - 1);
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
      if (newTailIdx >= owned.positions().length) {
        positionTailIndices.put(player, owned.positions().length - 1);
      } else {
        positionTailIndices.put(player, newTailIdx);
      }
      return hasPlayerWon(player);
    }

    int absoluteNew = positionTrackIndices.get(player) + roll;
    TailEntry entry = computeTailEntryFromTrack(player, absoluteNew);
    if (entry != null) {
      int stepsIntoTail = Math.max(0, entry.steps() - 1);
      positionTailIndices.put(player, Math.min(stepsIntoTail, entry.tail().positions().length - 1));
      return hasPlayerWon(player);
    }

    int newIndex = absoluteNew % track.length;
    positionTrackIndices.put(player, newIndex);
    return false;
  }

  private boolean hasPlayerWon(Player player) {
    for (Tail t : tails) {
      if (t.owner() == player) {
        Integer idx = positionTailIndices.get(player);
        return idx != null && idx == (t.positions().length - 1);
      }
    }
    return false;
  }

  public Player[] getPlayers() {
    return players;
  }

  private Tail findOwnedTail(Player player) {
    for (Tail t : tails) {
      if (t.owner() == player) return t;
    }
    return null;
  }

  private TailEntry computeTailEntryFromTrack(Player player, int absoluteNew) {
    int currentIndex = positionTrackIndices.get(player);
    int trackLen = track.length;

    for (Tail tail : tails) {
      if (tail.owner() != player) continue;
      int tb = tail.breakIndex();

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
