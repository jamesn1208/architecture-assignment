package uk.ac.mmu.architecture.infrastructure.adapters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uk.ac.mmu.architecture.applicationcode.boards.Board;
import uk.ac.mmu.architecture.applicationcode.boards.TwoPlayerBoardFactory;
import uk.ac.mmu.architecture.applicationcode.dice.DiceShaker;
import uk.ac.mmu.architecture.applicationcode.dice.SingleDiceShaker;
import uk.ac.mmu.architecture.applicationcode.dice.DoubleDiceShaker;
import uk.ac.mmu.architecture.applicationcode.rulesets.BaseHitCondition;
import uk.ac.mmu.architecture.applicationcode.rulesets.BaseWinCondition;
import uk.ac.mmu.architecture.applicationcode.rulesets.StandardHitCondition;
import uk.ac.mmu.architecture.applicationcode.rulesets.VariationWinCondition;

import java.util.List;

@Configuration
public class GameConfig {

  @Bean
  public DiceShaker diceShaker() {
    // Two dice by wrapping a single-dice shaker (adjust as desired)
    return new DoubleDiceShaker(new SingleDiceShaker());
  }

  @Bean
  public BaseHitCondition baseHitCondition() {
    return new StandardHitCondition();
  }

  @Bean
  public BaseWinCondition baseWinCondition() {
    return new VariationWinCondition();
  }

  @Bean
  public Board board() {
    // Provide a default board (use TwoPlayerBoardFactory or FourPlayerBoardFactory as needed)
    return new TwoPlayerBoardFactory().create();
  }

  @Bean
  public RandomGameFacade randomGameFacade(
      DiceShaker diceShaker,
      BaseHitCondition hitCondition,
      BaseWinCondition winCondition,
      Board board,
      ConsoleGameOutputAdapter consoleGameOutputAdapter,
      ConsoleStateOutputAdapter consoleStateOutputAdapter) {
    return new RandomGameFacade(
        diceShaker,
        hitCondition,
        winCondition,
        board,
        List.of(consoleGameOutputAdapter),
        List.of(consoleStateOutputAdapter));
  }
}
