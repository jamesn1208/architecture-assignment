package uk.ac.mmu.architecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.mmu.architecture.applicationcode.Boards.Board;
import uk.ac.mmu.architecture.applicationcode.Boards.BoardFactory;
import uk.ac.mmu.architecture.applicationcode.Boards.FourPlayerBoardFactory;
import uk.ac.mmu.architecture.applicationcode.Boards.TwoPlayerBoardFactory;
import uk.ac.mmu.architecture.applicationcode.Dice.PresetDiceShaker;
import uk.ac.mmu.architecture.applicationcode.Dice.DoubleDiceShaker;
import uk.ac.mmu.architecture.applicationcode.Dice.SingleDiceShaker;
import uk.ac.mmu.architecture.applicationcode.Dice.DiceShaker;
import uk.ac.mmu.architecture.applicationcode.Games.BaseGame;
import uk.ac.mmu.architecture.applicationcode.Games.Game;
import uk.ac.mmu.architecture.applicationcode.Rulesets.*;
import uk.ac.mmu.architecture.infrastructure.Output.Colour;
import uk.ac.mmu.architecture.infrastructure.Output.ConsoleOutputObserver;
import uk.ac.mmu.architecture.infrastructure.Output.OutputObserver;

@SpringBootApplication
public class ArchitectureAssignmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(ArchitectureAssignmentApplication.class, args);

		BoardFactory factory = new TwoPlayerBoardFactory();
		Board board = factory.create(
				factory.generatePlayers(),
				factory.generateTrack(),
				factory.generateTails()
		);

		OutputObserver[] observers = {new ConsoleOutputObserver()};
		int[] rolls = {6, 6, 6, 6, 3, 4, 3, 4};
		DiceShaker presetShaker = new PresetDiceShaker(rolls);
		DiceShaker shaker = new SingleDiceShaker();
		Ruleset rules = new Ruleset(new VariationWinCondition(), new VariationHitCondition(), shaker);

		BaseGame game = new Game(board, rules, observers);
		game.start();
		System.out.format("Winner: %s%s%s%n", game.getWinner().getColour().unicode, game.getWinner().getName(), Colour.RESET.unicode);

	}
}
