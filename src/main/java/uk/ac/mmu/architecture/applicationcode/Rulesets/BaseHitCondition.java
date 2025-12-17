package uk.ac.mmu.architecture.applicationcode.Rulesets;

import uk.ac.mmu.architecture.applicationcode.Players.Player;

public interface BaseHitCondition {
    Player hitPlayer(Player[] players, Player currentPlayer, int roll);
}
