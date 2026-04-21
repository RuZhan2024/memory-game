package com.zhanru.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoveBasedScoring implements ScoringStrategy {
    
    private static final Logger LOGGER = LogManager.getLogger();

    private final int matchReward;
    private final int mismatchPenalty;
    private int score;

    public MoveBasedScoring(int matchReward, int mismatchPenalty) {
        if (matchReward <= 0 || mismatchPenalty < 0) {
            throw new IllegalArgumentException("Invalid scoring values");
        }

        this.matchReward = matchReward;
        this.mismatchPenalty = mismatchPenalty;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void updateScore(boolean matched) {
        this.score = matched ? score + this.matchReward : Math.max(0, score - this.mismatchPenalty);
    }

    @Override
    public void reset() {
        score = 0;
    }
}
