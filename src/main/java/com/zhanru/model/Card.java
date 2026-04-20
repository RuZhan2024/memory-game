package com.zhanru.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Card {
    private static final Logger LOGGER = LogManager.getLogger();

    private final int pairId;
    private boolean faceUp;
    private boolean matched;

    public Card(int pairId) {
        this.pairId = pairId;
    }

    public int getPairId() {
        return this.pairId;
    }

    public boolean isFaceUp() {
        return this.faceUp;
    }

    public boolean isMatched() {
        return this.matched;
    }

    public void flipUp() {
        this.faceUp = true;
    }

    public void flipDown() {
        if (!matched) {
            this.faceUp = false;
        }

    }

    public void markMatched() {
        matched = true;
        faceUp = true;
    }

    public boolean matches(Card other) {
        boolean result = other != null && this.pairId == other.pairId;
        return result;
    }
}
