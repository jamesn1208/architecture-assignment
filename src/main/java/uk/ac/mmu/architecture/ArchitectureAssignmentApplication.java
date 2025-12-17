package uk.ac.mmu.architecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.mmu.architecture.OldShit.Dice.DoubleDiceShaker;
import uk.ac.mmu.architecture.OldShit.Dice.SingleDiceShaker;
import uk.ac.mmu.architecture.applicationcode.Boards.Board;
import uk.ac.mmu.architecture.applicationcode.Boards.BoardFactory;
import uk.ac.mmu.architecture.applicationcode.Boards.FourPlayerBoardFactory;
import uk.ac.mmu.architecture.applicationcode.Games.BaseGame;
import uk.ac.mmu.architecture.applicationcode.Games.RandomGame;
import uk.ac.mmu.architecture.applicationcode.Rulesets.Ruleset;
import uk.ac.mmu.architecture.applicationcode.Rulesets.StandardHitCondition;
import uk.ac.mmu.architecture.applicationcode.Rulesets.StandardWinCondition;
import uk.ac.mmu.architecture.infrastructure.Output.ConsoleOutputObserver;
import uk.ac.mmu.architecture.infrastructure.Output.OutputObserver;

@SpringBootApplication
public class ArchitectureAssignmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(ArchitectureAssignmentApplication.class, args);

		for (int i = 0; i < 1000; i++) {
			BoardFactory factory = new FourPlayerBoardFactory();
			Board board = factory.create(
					factory.generatePlayers(),
					factory.generateTrack(),
					factory.generateTails()
			);

			OutputObserver[] observers = {new ConsoleOutputObserver()};
			Ruleset rules = new Ruleset(new StandardWinCondition(), new StandardHitCondition(), new DoubleDiceShaker(new SingleDiceShaker()));

			BaseGame game = new RandomGame(board, rules, observers);
			game.start();
			System.out.println("Winner: " + game.getWinner().getName());
		}
	}
}
