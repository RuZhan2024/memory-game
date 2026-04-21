package com.zhanru.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Move Based Scoring - Unit Tests")
public class MoveBasedScoringTest {

    @Test
    @DisplayName("updateScore(): should add points for a match")
    void addPointForMatch() {
        MoveBasedScoring scoring = new MoveBasedScoring(2, 1);
        scoring.updateScore(true);
        assertEquals(2, scoring.getScore());
        scoring.updateScore(false);
        assertEquals(1, scoring.getScore());
    }

    @Test
    @DisplayName("updateScore(): should not let score go below zero")
    void keepScoreAtZeroOrAbove() {
        MoveBasedScoring scoring = new MoveBasedScoring(2, 1);
        scoring.updateScore(false);
        assertEquals(0, scoring.getScore());
    }

    @Test
    @DisplayName("reset(): should clear the score")
    void resetScore() {
        MoveBasedScoring scoring = new MoveBasedScoring(2, 1);
        scoring.updateScore(true);
        scoring.reset();

        assertEquals(0, scoring.getScore());
    }
}
