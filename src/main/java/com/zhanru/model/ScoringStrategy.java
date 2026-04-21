package com.zhanru.model;

public interface ScoringStrategy {

    int getScore();
    void updateScore(boolean matched);
    void reset();
} 
