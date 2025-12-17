package uk.ac.mmu.architecture.Game;

import uk.ac.mmu.architecture.Boards.Board;
import uk.ac.mmu.architecture.Players.Player;
import uk.ac.mmu.architecture.Players.PlayerFactory;

public class RandomGameFacade {
  private final int num_of_players;
  private final Board board;
  private final Ruleset rules;
  private final Player[] players;

  public RandomGameFacade(int numOfPlayers, Board board, Ruleset rules) {
    this.players = new PlayerFactory().manufacture(numOfPlayers);
    this.num_of_players = numOfPlayers;
    this.board = board;
    this.rules = rules;
  }

  public void start() {
    Game game = new RandomGame(this.num_of_players, this.board, this.rules, this.players);
    game.start();
  }
}
