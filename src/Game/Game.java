package Game;

import Boards.Board;
import Players.Player;
import Players.PlayerFactory;
import Utils.Console;

import java.util.Arrays;

public class Game {
    private final Ruleset rules;
    private final Board board;
    public Player[] players;

    public Game(Ruleset rules, Board board) {
        this.players = new Player[rules.numberOfPlayers];
        this.players = PlayerFactory.manufacture(rules.numberOfPlayers, board, rules.winCondition);
        this.rules = rules;
        this.board = board;
    }

    @Override
    public String toString() {
        return "Main.BaseGame(rules=" + rules + ", board=" + board.getClass() + ", players=" + Arrays.toString(players) + ")";
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
        Console.log(player.getName() + " rolled a " + roll, player.getName());

        if (! rules.hitCondition.movePlayer(players, player, roll)) {
            return false;
        }

        player.move(roll);

        if (rules.winCondition.hasWon(player)) {
            Console.log(player.getName() + " has won the game!", player.getName());
            return true;
        }

        return false;
    }

    private int rollDie() {
        int roll = 0;

        for (int i = 0; i < rules.numberOfDice; i++) {
            roll += (int) (Math.random() * 6) + 1;
        }

        return roll;
    }
}
