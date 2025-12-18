package uk.ac.mmu.architecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import uk.ac.mmu.architecture.applicationcode.boards.Board;
import uk.ac.mmu.architecture.applicationcode.boards.BoardFactory;
import uk.ac.mmu.architecture.applicationcode.boards.TwoPlayerBoardFactory;
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

  private void runGames(ApplicationContext ctx) {
    BoardFactory twoPlayerBoardFactory = new TwoPlayerBoardFactory();
    Board twoPlayerBoard = twoPlayerBoardFactory.create();

    int[] rolls = {6, 6, 6, 6, 3, 4, 3, 4};

    // Obtain adapter beans from the Spring context
    GameOutputPort gameObserver = ctx.getBean(GameOutputPort.class);
    StateOutputPort stateObserver = ctx.getBean(StateOutputPort.class);

    ReplayGameFacade replayGame =
        new ReplayGameFacade(
            rolls,
            new StandardHitCondition(),
            new StandardWinCondition(),
            twoPlayerBoard,
            List.of(gameObserver),
            List.of(stateObserver));
    replayGame.start();
  }
}
