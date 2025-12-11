//package Game;
//
//import Boards.Board;
//import Players.Player;
//import Players.PlayerFactory;
//import Utils.Console;
//
//import java.util.Arrays;
//import java.util.Random;
//
//public abstract class oldgame {
//  private final Ruleset rules;
//  private final Board board;
//  private final Random random;
//  private final long seed;
//  private final Integer[] rolls;
//  private int rollIndex = 0;
//
//  private Player[] players;
//  private Player winner;
//  private State state = State.READY;
//
//  public oldgame(Ruleset rules, Board board) {
//    this(rules, board, System.currentTimeMillis(), new Integer[0]);
//  }
//
//  public oldgame(Ruleset rules, Board board, long seed) {
//    this(rules, board, seed, new Integer[0]);
//  }
//
//  public oldgame(Ruleset rules, Board board, Integer[] rolls) {
//    this(rules, board, System.currentTimeMillis(), rolls);
//  }
//
//  private oldgame(Ruleset rules, Board board, long seed, Integer[] rolls) {
//    if (board.getNumberOfTails() != rules.numberOfPlayers) {
//      throw new IllegalArgumentException(
//          "Each player must have one tail each on the board. Number of tails on the board: "
//              + board.getNumberOfTails()
//              + ", number of players: "
//              + rules.numberOfPlayers);
//    }
//
//    this.players = new Player[rules.numberOfPlayers];
//    this.players = PlayerFactory.manufacture(rules.numberOfPlayers, board);
//    this.seed = seed;
//    this.random = new Random(this.seed);
//    this.rolls = rolls;
//
//    this.rules = rules;
//    this.board = board;
//  }
//
//  @Override
//  public String toString() {
//    return "Game(rules="
//        + rules
//        + ", board="
//        + board.getClass()
//        + ", players="
//        + Arrays.toString(players)
//        + ", seed="
//        + seed
//        + ")";
//  }
//
//  public Player getWinner() {
//    return winner;
//  }
//
//  public State getState() {
//    return state;
//  }
//
//  public void start() {
//    state = State.IN_PLAY;
//
//    System.out.println(
//        "++ Starting a new game ++"
//            + "\nRules: "
//            + rules
//            + "\nBoard: "
//            + board
//            + "\nDice Seed: "
//            + seed
//            + "\n+++++++++++++++++++++++++"
//            + "\n");
//
//    while (true) {
//      for (Player player : players) {
//        if (takeTurn(player)) {
//          state = State.COMPLETE;
//          return;
//        }
//      }
//    }
//  }
//
//  private boolean takeTurn(Player player) {
//    int roll;
//
//    if (this.rolls.length > 0 ) {
//      try {
//        roll = this.rolls[rollIndex];
//        this.rollIndex += 1;
//      } catch (ArrayIndexOutOfBoundsException e) {
//        throw new RuntimeException("Not enough rolls provided, unable to continue simulation");
//      }
//    } else {
//      roll = rollDie();
//    }
//
//    Console.log(player.getName() + " rolled a " + roll, player.getName());
//
//    if (!rules.hitCondition.movePlayer(players, player, roll)) {
//      return false;
//    }
//
//    if (rules.winCondition.canMove(player, roll)) {
//      player.move(roll);
//    } else {
//      Console.log(
//          player.getName()
//              + " overshoots and forfeits their turn, remaining at "
//              + player.getPosition(),
//          player.getName());
//      return false;
//    }
//
//    if (rules.winCondition.hasWon(player)) {
//      Console.log(player.getName() + " has won the game!", player.getName());
//      winner = player;
//      return true;
//    }
//
//    return false;
//  }
//
//  private int rollDie() {
//    int roll = 0;
//
//    for (int i = 0; i < rules.numberOfDice; i++) {
//      roll += random.nextInt(6) + 1;
//    }
//
//    return roll;
//  }
//}
