package com.jitterted.yacht.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class YachtScoringTest {

  @Test
  public void fullHouse() throws Exception {
    YachtScorer yachtScorer = new YachtScorer();

    assertThat(yachtScorer.scoreAsFullHouse(DiceRoll.of(1, 2, 3, 4, 5)))
        .isZero();

    // These are the two cases for not being a full house, but one of the dice occurs twice or more
    assertThat(yachtScorer.scoreAsFullHouse(DiceRoll.of(6, 6, 5, 5, 4)))
        .isZero();
    assertThat(yachtScorer.scoreAsFullHouse(DiceRoll.of(5, 1, 1, 1, 1)))
        .isZero();

    assertThat(yachtScorer.scoreAsFullHouse(DiceRoll.of(3, 3, 3, 5, 5)))
        .isEqualTo(3 + 3 + 3 + 5 + 5);
    assertThat(yachtScorer.scoreAsFullHouse(DiceRoll.of(2, 2, 6, 6, 6)))
        .isEqualTo(2 + 2 + 6 + 6 + 6);

    assertThat(yachtScorer.scoreAsFullHouse(DiceRoll.of(5, 5, 5, 5, 5)))
        .as("A Yacht (all five dice same number) cannot be scored on Full House, i.e., is zero")
        .isZero();
  }

  @Test
  public void singleNumber() throws Exception {
    YachtScorer yachtScorer = new YachtScorer();

    assertThat(yachtScorer.scoreAsOnes(DiceRoll.of(2, 3, 4, 5, 6)))
        .isZero();
    assertThat(yachtScorer.scoreAsOnes(DiceRoll.of(1, 2, 3, 4, 5)))
        .isEqualTo(1);
    assertThat(yachtScorer.scoreAsOnes(DiceRoll.of(1, 1, 1, 1, 1)))
        .isEqualTo(5);

    assertThat(yachtScorer.scoreAsTwos(DiceRoll.of(2, 2, 3, 4, 5)))
        .isEqualTo(2 + 2);

    assertThat(yachtScorer.scoreAsThrees(DiceRoll.of(1, 3, 3, 3, 6)))
        .isEqualTo(9);

    assertThat(yachtScorer.scoreAsFives(DiceRoll.of(1, 2, 3, 4, 6)))
        .isZero();
    assertThat(yachtScorer.scoreAsFives(DiceRoll.of(1, 2, 5, 5, 6)))
        .isEqualTo(10);

    assertThat(yachtScorer.scoreAsSixes(DiceRoll.of(6, 6, 6, 4, 4)))
        .isEqualTo(6 + 6 + 6);
  }

  // @@@ strager doesn't understand this test's purpose. Perhaps this is a vestigial test.
  @Test
  public void oldScoreForFoursIsSameAsDiceRollBasedScore() throws Exception {
    YachtScorer yachtScorer = new YachtScorer();

    int diceRollScore = yachtScorer.scoreAsFours(DiceRoll.of(5, 5, 4, 4, 4));
    int score = yachtScorer.scoreAsFours(DiceRoll.of(5, 5, 4, 4, 4));

    assertThat(diceRollScore)
        .isEqualTo(score);
  }

}
