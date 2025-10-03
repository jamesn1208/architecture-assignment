import Boards.*;
import HitConditions.*;
import WinConditions.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Standard
        Ruleset rules = new Ruleset(2, new StandardWinCondition(), new StandardHitCondition(), 2);
        Board board = new StandardBoard();
        BaseGame game = new BaseGame(rules, board);
        System.out.println(Arrays.toString(board.getTailMap().get(9)));
        System.out.println(Arrays.toString(game.players));

//        game.start();
//
//        System.out.println(" ");
//        System.out.println("---");
//        System.out.println(" ");
//
//        // Variation
//        Ruleset variation = new Ruleset(1, new VariationWinCondition(), new VariationHitCondition(), 4);
//        BaseGame variationGame = new BaseGame(variation, new LargeBoard());
//
//        variationGame.start();
    }
}