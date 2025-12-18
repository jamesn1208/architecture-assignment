package uk.ac.mmu.architecture.applicationcode.Boards;

import uk.ac.mmu.architecture.applicationcode.Players.Player;

public class TwoPlayerBoardFactory implements BoardFactory {
  private static final int TAIL_SIZE = 3;
  private static final int NUM_PLAYERS = 2;
  private static final int BOARD_SIZE = 18;

  @Override
  public Board create() {
    Player[] players = generatePlayers();
    String[] track = generateTrack();
    Tail[] tails = generateTails(players);
    return new Board(players, track, tails);
  }

  private String[] generateTrack() {
    String[] track = new String[BOARD_SIZE];
    for (int i = 0; i < track.length; i++) {
      track[i] = String.valueOf(i + 1);
    }
    return track;
  }

  private Tail[] generateTails(Player[] players) {
    Tail[] tails = new Tail[NUM_PLAYERS];

    String[] redPath = new String[TAIL_SIZE];
    String[] bluePath = new String[TAIL_SIZE];

    for (int i = 0; i < TAIL_SIZE; i++) {
      redPath[i] = "R" + (i + 1);
      bluePath[i] = "B" + (i + 1);
    }

    tails[0] = new Tail(redPath, 17, players[0]);
    tails[1] = new Tail(bluePath, 8, players[1]);

    return tails;
  }

  private Player[] generatePlayers() {
    Player[] players = new Player[NUM_PLAYERS];
    players[0] = Player.PLAYER_1;
    players[1] = Player.PLAYER_2;

    players[0].setStartIndex(0);
    players[1].setStartIndex(9);

    players[0].setCurrentPosition("1");
    players[1].setCurrentPosition("10");
    return players;
  }
}
