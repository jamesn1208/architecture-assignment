import Boards.*;
import HitConditions.*;
import Players.PlayerFactory;
import WinConditions.*;
import Game.*;
import Dice.*;

import java.util.ArrayList;
import java.util.Arrays;

import static Utils.Console.breakLine;

public class Main {
  public static void main(String[] args) {
    DiceShaker diceShaker = new SingleDiceShaker();
    diceShaker = new DoubleDiceShaker(diceShaker);
    System.out.println(diceShaker.shake());

    System.out.println(Arrays.toString(new PlayerFactory().manufacture(2)));
  }
}
