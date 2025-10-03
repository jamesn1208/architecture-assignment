import Boards.Board;

public class BaseGame {
    private final Ruleset rules;
    private final Board board;

    public BaseGame(Ruleset rules, Board board) {
        this.rules = rules;
        this.board = board;
    }

    public void start() {
        System.out.println("Game started with " + rules.numberOfDice + " die.");
        System.out.println("Board has " + board.numberOfTiles + " tiles.");

        System.out.println(this.rules.winCondition.hasWon(10, 12, 3));
    }
}
