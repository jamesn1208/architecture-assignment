import Boards.Board;

public class BaseGame {
    private final Ruleset rules;
    private final Board board;
    private Player[] players;

    public BaseGame(Ruleset rules, Board board) {
        this.players = new Player[rules.numberOfPlayers];
        this.rules = rules;
        this.board = board;
    }

    @Override
    public String toString() {
        return "BaseGame(rules=" + rules + ", board=" + board.getClass() + ")";
    }

    public void start() {
//        while (true) {
//            for (int player = 1; player <= rules.numberOfPlayers; player++) {
//                takeTurn(player);
//                if (rules.winCondition.hasWon())
//                // Check win condition here
//            }
//        }
        int roll = 0;

        for (int i = 0; i < rules.numberOfDice; i++) {
            roll += rollDie();
        }
    }

    private int rollDie() {
        return (int) (Math.random() * 6) + 1;
    }
}
