package com.jitterted.yacht.domain;

import java.util.List;

public class Game {

  private final DiceRoller diceRoller;
  public final Scoreboard scoreboard = new Scoreboard();

  private DiceRoll lastRoll = DiceRoll.of(0, 0, 0, 0, 0);

  private Rolls rolls;
  private boolean lastRollAssignedToCategory;

  public Game() {
    diceRoller = new DiceRoller();
  }

  public Game(DiceRoller diceRoller) {
    this.diceRoller = diceRoller;
  }

  public void rollDice() {
    lastRollAssignedToCategory = false;
    rolls = Rolls.start();
    lastRoll = diceRoller.roll();
  }

  public void reRoll(List<Integer> keptDice) {
    rolls.increment();
    lastRoll = diceRoller.reRoll(keptDice);
  }

  public DiceRoll lastRoll() {
    return lastRoll;
  }

  public int score() {
    return scoreboard.score();
  }

  public void assignRollTo(ScoreCategory scoreCategory) {
    lastRollAssignedToCategory = true;
    scoreboard.scoreAs(scoreCategory, lastRoll);
  }

  public List<ScoredCategory> scoredCategories() {
    return scoreboard.scoredCategories();
  }

  public boolean canReRoll() {
    if (lastRollAssignedToCategory()) {
      return false;
    }
    return rolls.canReRoll();
  }

  public boolean lastRollAssignedToCategory() {
    return lastRollAssignedToCategory;
  }

}
