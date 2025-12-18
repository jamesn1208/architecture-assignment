package uk.ac.mmu.architecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.ac.mmu.architecture.applicationcode.Boards.Board;
import uk.ac.mmu.architecture.applicationcode.Boards.BoardFactory;
import uk.ac.mmu.architecture.applicationcode.Boards.TwoPlayerBoardFactory;
import uk.ac.mmu.architecture.applicationcode.Games.ReplayGameFacade;
import uk.ac.mmu.architecture.applicationcode.Rulesets.*;

@SpringBootApplication
public class ArchitectureAssignmentApplication {
  public static void main(String[] args) {
    SpringApplication.run(ArchitectureAssignmentApplication.class, args);
    ArchitectureAssignmentApplication app = new ArchitectureAssignmentApplication();
    app.runGames();
  }

  private void runGames() {
    BoardFactory twoPlayerBoardFactory = new TwoPlayerBoardFactory();
    Board twoPlayerBoard =
        twoPlayerBoardFactory.create(
            twoPlayerBoardFactory.generatePlayers(),
            twoPlayerBoardFactory.generateTrack(),
            twoPlayerBoardFactory.generateTails());

    int[] rolls = {6, 6, 6, 6, 3, 4, 3, 4};

    ReplayGameFacade replayGame =
        new ReplayGameFacade(
            rolls, new StandardHitCondition(), new StandardWinCondition(), twoPlayerBoard);
    replayGame.start();
  }
}
