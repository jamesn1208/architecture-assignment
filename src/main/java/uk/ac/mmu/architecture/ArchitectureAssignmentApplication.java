package uk.ac.mmu.architecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import uk.ac.mmu.architecture.applicationcode.boards.Board;
import uk.ac.mmu.architecture.applicationcode.boards.BoardFactory;
import uk.ac.mmu.architecture.applicationcode.boards.FourPlayerBoardFactory;
import uk.ac.mmu.architecture.applicationcode.boards.TwoPlayerBoardFactory;
import uk.ac.mmu.architecture.applicationcode.dice.SingleDiceShaker;
import uk.ac.mmu.architecture.applicationcode.players.Player;
import uk.ac.mmu.architecture.infrastructure.adapters.RandomGameFacade;
import uk.ac.mmu.architecture.infrastructure.adapters.ReplayGameFacade;
import uk.ac.mmu.architecture.applicationcode.rulesets.*;
import uk.ac.mmu.architecture.applicationcode.ports.GameOutputPort;
import uk.ac.mmu.architecture.applicationcode.ports.StateOutputPort;

import java.util.List;

@SpringBootApplication
public class ArchitectureAssignmentApplication {
  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(ArchitectureAssignmentApplication.class, args);
    ArchitectureAssignmentApplication app = new ArchitectureAssignmentApplication();
    app.runGames(ctx);
  }

  private void announceGame(String name) {
    System.out.println("\nStarting " + name);
    System.out.println("-------------------------");
  }

  private void runGames(ApplicationContext ctx) {
    // Obtain adapter beans from the Spring context
    GameOutputPort gameObserver = ctx.getBean(GameOutputPort.class);
    StateOutputPort stateObserver = ctx.getBean(StateOutputPort.class);

    // Create board factories
    BoardFactory twoPlayerBoardFactory = new TwoPlayerBoardFactory();
    BoardFactory fourPlayerBoardFactory = new FourPlayerBoardFactory();

    // Track overall success
    boolean successful = true;

    // Random Game Example
    announceGame("Random Game Example");
    Board randomGameBoard = twoPlayerBoardFactory.create();
    RandomGameFacade randomGame =
        new RandomGameFacade(
            new SingleDiceShaker(),
            new StandardHitCondition(),
            new StandardWinCondition(),
            randomGameBoard,
            List.of(gameObserver),
            List.of(stateObserver));
    randomGame.start();

    // Basic Game Scenario 1 Example 1
    announceGame("Basic Game Scenario 1 Example 1");
    Board scenario1Example1Board = twoPlayerBoardFactory.create();
    int[] scenario1Example1Rolls = new int[] {12, 12, 7, 8};
    ReplayGameFacade scenario1Example1Game =
        new ReplayGameFacade(
            scenario1Example1Rolls,
            new StandardHitCondition(),
            new StandardWinCondition(),
            scenario1Example1Board,
            List.of(gameObserver),
            List.of(stateObserver));
    scenario1Example1Game.start();
    if (scenario1Example1Game.getWinner() != Player.PLAYER_2) {
      successful = false;
      System.out.println("Base Game Scenario 1 Example 2 failed: Expected BLUE to win.");
    }

    // Basic Game Scenario 1 Example 2
    announceGame("Basic Game Scenario 1 Example 2");
    Board scenario1Example2Board = twoPlayerBoardFactory.create();
    int[] scenario1Example2Rolls = new int[] {12, 12, 6, 6, 2};
    ReplayGameFacade scenario1Example2Game =
        new ReplayGameFacade(
            scenario1Example2Rolls,
            new StandardHitCondition(),
            new StandardWinCondition(),
            scenario1Example2Board,
            List.of(gameObserver),
            List.of(stateObserver));
    scenario1Example2Game.start();
    if (scenario1Example2Game.getWinner() != Player.PLAYER_1) {
      successful = false;
      System.out.println("Base Game Scenario 1 Example 2 failed: Expected RED to win.");
    }

    // Basic Game Scenario 2
    announceGame("Basic Game Scenario 2");
    Board scenario2Board = twoPlayerBoardFactory.create();
    int[] scenario2Rolls = new int[] {8, 2, 3, 4, 9};
    ReplayGameFacade scenario2Game =
        new ReplayGameFacade(
            scenario2Rolls,
            new StandardHitCondition(),
            new StandardWinCondition(),
            scenario2Board,
            List.of(gameObserver),
            List.of(stateObserver));
    scenario2Game.start();
    if (scenario2Game.getWinner() != Player.PLAYER_1) {
      successful = false;
      System.out.println("Base Game Scenario 2 failed: Expected RED to win.");
    }

    // Basic Game Scenario 3
    announceGame("Basic Game Scenario 3");
    Board scenario3Board = twoPlayerBoardFactory.create();
    int[] scenario3Rolls = new int[] {12, 12, 7, 11};
    ReplayGameFacade scenario3Game =
        new ReplayGameFacade(
            scenario3Rolls,
            new StandardHitCondition(),
            new StandardWinCondition(),
            scenario3Board,
            List.of(gameObserver),
            List.of(stateObserver));
    scenario3Game.start();
    if (scenario3Game.getWinner() != Player.PLAYER_2) {
      successful = false;
      System.out.println("Base Game Scenario 3 failed: Expected BLUE to win.");
    }

    // Variation Game Scenario 1
    announceGame("Variation Game Scenario 1");
    Board scenario1BoardVariation = twoPlayerBoardFactory.create();
    int[] scenario1RollsVariation = new int[] {6, 6, 6, 6, 3, 4, 3, 4};
    ReplayGameFacade scenario1GameVariation =
        new ReplayGameFacade(
            scenario1RollsVariation,
            new StandardHitCondition(),
            new StandardWinCondition(),
            scenario1BoardVariation,
            List.of(gameObserver),
            List.of(stateObserver));
    scenario1GameVariation.start();
    if (scenario1GameVariation.getWinner() != Player.PLAYER_2) {
      successful = false;
      System.out.println("Variation Game Scenario 1 failed: Expected BLUE to win.");
    }

    // Variation Game Scenario 2
    announceGame("Variation Game Scenario 2");
    Board scenario2BoardVariation = twoPlayerBoardFactory.create();
    int[] scenario2RollsVariation = new int[] {12, 12, 12, 9, 8};
    ReplayGameFacade scenario2GameVariation =
        new ReplayGameFacade(
            scenario2RollsVariation,
            new VariationHitCondition(),
            new VariationWinCondition(),
            scenario2BoardVariation,
            List.of(gameObserver),
            List.of(stateObserver));
    scenario2GameVariation.start();
    if (scenario2GameVariation.getWinner() != Player.PLAYER_1) {
      successful = false;
      System.out.println("Variation Game Scenario 2 failed: Expected RED to win.");
    }

    // Variation Game Scenario 3
    announceGame("Variation Game Scenario 3");
    Board scenario3BoardVariation = twoPlayerBoardFactory.create();
    int[] scenario3RollsVariation = new int[] {8, 2, 3, 12, 9, 6};
    ReplayGameFacade scenario3GameVariation =
        new ReplayGameFacade(
            scenario3RollsVariation,
            new VariationHitCondition(),
            new VariationWinCondition(),
            scenario3BoardVariation,
            List.of(gameObserver),
            List.of(stateObserver));
    scenario3GameVariation.start();
    if (scenario3GameVariation.getWinner() != Player.PLAYER_2) {
      successful = false;
      System.out.println("Variation Game Scenario 3 failed: Expected BLUE to win.");
    }

    // Advanced Game Scenario 1
    announceGame("Advanced Game Scenario 1");
    Board scenario1BoardAdvanced = fourPlayerBoardFactory.create();
    int[] scenario1RollsAdvanced =
        new int[] {7, 3, 8, 5, 7, 6, 8, 7, 6, 8, 2, 4, 4, 8, 5, 7, 8, 3, 9, 9, 7, 5, 7, 9};
    ReplayGameFacade scenario1GameAdvanced =
        new ReplayGameFacade(
            scenario1RollsAdvanced,
            new StandardHitCondition(),
            new StandardWinCondition(),
            scenario1BoardAdvanced,
            List.of(gameObserver),
            List.of(stateObserver));
    scenario1GameAdvanced.start();
    if (scenario1GameAdvanced.getWinner() != Player.PLAYER_4) {
      successful = false;
      System.out.println("Advanced Game Scenario 2 failed: Expected YELLOW to win.");
    }

    // Advanced Game Scenario 2
    announceGame("Advanced Game Scenario 2");
    Board scenario2BoardAdvanced = fourPlayerBoardFactory.create();
    int[] scenario2RollsAdvanced =
        new int[] {11, 11, 8, 10, 10, 7, 2, 4, 6, 8, 4, 9, 9, 10, 7, 11, 10, 8, 5, 7};
    ReplayGameFacade scenario2GameAdvanced =
        new ReplayGameFacade(
            scenario2RollsAdvanced,
            new VariationHitCondition(),
            new VariationWinCondition(),
            scenario2BoardAdvanced,
            List.of(gameObserver),
            List.of(stateObserver));
    scenario2GameAdvanced.start();
    if (scenario2GameAdvanced.getWinner() != Player.PLAYER_4) {
      successful = false;
      System.out.println("Advanced Game Scenario 2 failed: Expected YELLOW to win.");
    }

    // State Machine Game Scenario 1
    announceGame("State Machine Game Scenario 1");
    Board scenario1BoardStateMachine = twoPlayerBoardFactory.create();
    int[] scenario1RollsStateMachine = new int[] {12, 12, 7, 8, 12, 12};
    ReplayGameFacade scenario1GameStateMachine =
        new ReplayGameFacade(
            scenario1RollsStateMachine,
            new VariationHitCondition(),
            new VariationWinCondition(),
            scenario1BoardStateMachine,
            List.of(gameObserver),
            List.of(stateObserver));
    scenario1GameStateMachine.start();
    if (scenario1GameStateMachine.getWinner() != Player.PLAYER_2) {
      successful = false;
      System.out.println("State Machine Game Scenario 1 failed: Expected BLUE to win.");
    }

    // Display results
    if (successful) {
      System.out.println("\nAll game scenarios completed successfully!");
    } else {
      System.out.println("\nSome game scenarios failed. Please check the logs above.");
    }
  }
}
