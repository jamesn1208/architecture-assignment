package uk.ac.mmu.architecture.applicationcode.Boards;

import uk.ac.mmu.architecture.applicationcode.Players.Player;

public class TwoPlayerBoardFactory implements BoardFactory {
  private static final int tailSize = 3;
  private static final int numPlayers = 2;
  private static final int boardSize = 18;

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

    for (int i = 0; i < tailSize; i++) {
      redPath[i] = "R" + (i + 1);
      bluePath[i] = "B" + (i + 1);
    }

    tails[0] = new Tail(redPath, 17, players[0]);
    tails[1] = new Tail(bluePath, 8, players[1]);

    return tails;
  }

  @Override
  public Player[] generatePlayers() {
    Player[] players = new Player[numPlayers];
    players[0] = Player.PLAYER_1;
    players[1] = Player.PLAYER_2;

    players[0].setStartIndex(0);
    players[1].setStartIndex(9);

    players[0].setCurrentPosition("1");
    players[1].setCurrentPosition("10");
    return players;
  }
}
