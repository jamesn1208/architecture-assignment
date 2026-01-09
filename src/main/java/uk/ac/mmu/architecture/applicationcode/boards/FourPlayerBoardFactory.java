package uk.ac.mmu.architecture.applicationcode.boards;

import uk.ac.mmu.architecture.applicationcode.players.Player;

public class FourPlayerBoardFactory implements BoardFactory {
  private static final int TAIL_SIZE = 6;
  private static final int NUM_PLAYERS = 4;
  private static final int BOARD_SIZE = 36;

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
    String[] greenPath = new String[TAIL_SIZE];
    String[] yellowPath = new String[TAIL_SIZE];

    for (int i = 0; i < TAIL_SIZE; i++) {
      redPath[i] = "R" + (i + 1);
      bluePath[i] = "B" + (i + 1);
      greenPath[i] = "G" + (i + 1);
      yellowPath[i] = "Y" + (i + 1);
    }

    tails[0] = new Tail(redPath, 35, players[0]);
    tails[1] = new Tail(bluePath, 8, players[1]);
    tails[2] = new Tail(greenPath, 17, players[2]);
    tails[3] = new Tail(yellowPath, 26, players[3]);

    return tails;
  }

  private Player[] generatePlayers() {
    Player[] players = new Player[NUM_PLAYERS];
    players[0] = Player.PLAYER_1;
    players[1] = Player.PLAYER_2;
    players[2] = Player.PLAYER_3;
    players[3] = Player.PLAYER_4;

    players[0].setStartIndex(0);
    players[1].setStartIndex(9);
    players[2].setStartIndex(18);
    players[3].setStartIndex(27);

    players[0].setCurrentPosition("1");
    players[1].setCurrentPosition("10");
    players[2].setCurrentPosition("19");
    players[3].setCurrentPosition("28");
    return players;
  }
}
