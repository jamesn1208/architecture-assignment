package uk.ac.mmu.architecture.infrastructure.adapters;

import org.springframework.stereotype.Component;
import uk.ac.mmu.architecture.applicationcode.players.Player;
import uk.ac.mmu.architecture.applicationcode.ports.GameBoundary;

@Component
public class CliGameAdapter implements GameBoundary {
  private final RandomGameFacade gameFacade;

  public CliGameAdapter(RandomGameFacade gameFacade) {
    this.gameFacade = gameFacade;
  }

  @Override
  public void start() {
    gameFacade.start();
  }

  @Override
  public void validate() {
    Player winner = gameFacade.getWinner();
    if (winner != null) {
      System.out.println("Winner: " + winner.getName());
    } else {
      System.out.println("No winner yet.");
    }
  }
}
