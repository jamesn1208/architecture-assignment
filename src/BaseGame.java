import Boards.Board;

public class BaseGame {
    private final Ruleset rules;
    private final Board board;
    public Player[] players;

    public BaseGame(Ruleset rules, Board board) {
        this.players = new Player[rules.numberOfPlayers];
        this.players = PlayerFactory.manufacture(rules.numberOfPlayers, board);
        this.rules = rules;
        this.board = board;
    }

    @Override
    public String toString() {
        return "BaseGame(rules=" + rules + ", board=" + board.getClass() + ")";
    }

    public void start() {
        while (true) {
            for (Player player : players) {
                boolean turn = takeTurn(player);
                if (turn) {
                    return;
                }
            }
        }
    }

    private boolean takeTurn(Player player) {
        int roll = rollDie();
        System.out.println(player.getName() + " rolled a " + roll);

    }

    private Player movePlayer(Player player, int roll) {
        int currentPosition = player.getPosition();
        int newPosition = currentPosition + roll;

        if (newPosition > board.getNumberOfTiles()) {
            newPosition = newPosition % board.getNumberOfTiles();
        }

        if (currentPosition < newPosition && newPosition >= player.getTailIndex()) {
            newPosition = board.getTailMap().get(newPosition % player.getTailIndex());
        }

        player.setPosition(newPosition);
        return player;
    }

    private int rollDie() {
        int roll = 0;

        for (int i = 0; i < rules.numberOfDice; i++) {
            roll += (int) (Math.random() * 6) + 1;
        }

        return roll;
    }
}
