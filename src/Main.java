import Boards.*;
import HitConditions.*;
import WinConditions.*;
import Game.*;

import java.util.ArrayList;

import static Utils.Console.breakLine;

public class Main {
  public static void main(String[] args) {
    // ArrayList so that we can add to the simulation easily
    ArrayList<String> winners = new ArrayList<>();

    // Standard
    Ruleset standardRules =
        new Ruleset(2, 2, new StandardWinCondition(), new StandardHitCondition());
    Game standardGame = new Game(standardRules, Board.STANDARD_BOARD, 1760011319523L);
    System.out.println(standardGame.getState()); // 'READY'

    standardGame.start();
    winners.add(standardGame.getWinner().getName());

    breakLine();

    // Variation
    //Integer[] rolls = {2, 6, 9, 12};
    Ruleset variation = new Ruleset(1, 4, new VariationWinCondition(), new VariationHitCondition());
    Game variationGame = new Game(variation, Board.LARGE_BOARD);

    variationGame.start();
    winners.add(variationGame.getWinner().getName());

    breakLine();

    System.out.println(
        "Simulation Breakdown:\n"
            + "Games Played: "
            + winners.size()
            + "\n"
            + "Winners: "
            + winners);
  }
}
