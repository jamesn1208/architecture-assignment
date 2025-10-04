import Boards.*;
import HitConditions.*;
import WinConditions.*;
import Game.*;

public class Main {
    public static void main(String[] args) {
        // Standard
        Ruleset standardRules = new Ruleset(2, new StandardWinCondition(), new StandardHitCondition(), 2);
        Game standardGame = new Game(standardRules, new StandardBoard());

        standardGame.start();

        System.out.println(" ");
        System.out.println("---");
        System.out.println(" ");

        // Variation
        Ruleset variation = new Ruleset(1, new VariationWinCondition(), new VariationHitCondition(), 4);
        Game variationGame = new Game(variation, new LargeBoard());

        variationGame.start();
    }
}