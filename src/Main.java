import Boards.*;
import HitConditions.*;
import WinConditions.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(PlayerFactory.manufacture(4, new StandardBoard())));

        // Standard
        Ruleset rules = new Ruleset(2, new StandardWinCondition(), new StandardHitCondition(), 3);
        BaseGame game = new BaseGame(rules, new StandardBoard());

        game.start();


        // Variation
        Ruleset variation = new Ruleset(1, new VariationWinCondition(), new VariationHitCondition(), 3);
        BaseGame variationGame = new BaseGame(variation, new LargeBoard());

        variationGame.start();
    }
}