import Boards.*;
import HitConditions.*;
import WinConditions.*;

public class Main {
    public static void main(String[] args) {
        // Standard
        Ruleset rules = new Ruleset(2, new StandardWinCondition(), new StandardHitCondition());
        BaseGame game = new BaseGame(rules, new StandardBoard());

        game.start();


        // Variation
        Ruleset variation = new Ruleset(1, new VariationWinCondition(), new VariationHitCondition());
        BaseGame variationGame = new BaseGame(variation, new LargeBoard());

        variationGame.start();
    }
}