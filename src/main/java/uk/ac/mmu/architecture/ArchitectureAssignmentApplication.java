package uk.ac.mmu.architecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.mmu.architecture.Dice.DiceShaker;
import uk.ac.mmu.architecture.Dice.DoubleDiceShaker;
import uk.ac.mmu.architecture.Dice.SingleDiceShaker;
import uk.ac.mmu.architecture.Players.PlayerFactory;

import java.util.Arrays;

@SpringBootApplication
public class ArchitectureAssignmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(ArchitectureAssignmentApplication.class, args);
		System.out.println("Hello, World!");

		DiceShaker diceShaker = new SingleDiceShaker();
		diceShaker = new DoubleDiceShaker(diceShaker);
		System.out.println(diceShaker.shake());

		System.out.println(Arrays.toString(new PlayerFactory().manufacture(2)));
	}
}
