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
    Game standardGame = new Game(standardRules, new StandardBoard());

    standardGame.start();
    winners.add(standardGame.getWinner().getName());

    breakLine();

    // Variation
    Ruleset variation = new Ruleset(1, 4, new VariationWinCondition(), new VariationHitCondition());
    Game variationGame = new Game(variation, new LargeBoard());

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
