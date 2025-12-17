package uk.ac.mmu.architecture.applicationcode.Boards;

import uk.ac.mmu.architecture.applicationcode.Players.Player;

public class FourPlayerBoardFactory implements BoardFactory {
  private static final int tailSize = 6;
  private static final int numPlayers = 4;
  private static final int boardSize = 36;

  @Override
  public Board create(Player[] players, String[] track, Tail[] tails) {
    return new Board(players, track, tails);
  }

  @Override
  public String[] generateTrack() {
    String[] track = new String[boardSize];
    for (int i = 0; i < track.length; i++) {
      track[i] = String.valueOf(i + 1);
    }
    return track;
  }

  @Override
  public Tail[] generateTails() {
    Player[] players = generatePlayers();
    Tail[] tails = new Tail[numPlayers];

    String[] redPath = new String[tailSize];
    String[] bluePath = new String[tailSize];
    String[] greenPath = new String[tailSize];
    String[] yellowPath = new String[tailSize];

    for (int i = 0; i < tailSize; i++) {
      redPath[i] = "R" + (i + 1);
      bluePath[i] = "B" + (i + 1);
      greenPath[i] = "G" + (i + 1);
      yellowPath[i] = "Y" + (i + 1);
    }

    tails[0] = new Tail(redPath, 17, players[0]);
    tails[1] = new Tail(bluePath, 8, players[1]);
    tails[2] = new Tail(greenPath, 26, players[2]);
    tails[3] = new Tail(yellowPath, 35, players[3]);

    return tails;
  }

  @Override
  public Player[] generatePlayers() {
    Player[] players = new Player[numPlayers];
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
