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
        LOGGER.debug("Creating card for pair {}", pairId);
        return this.pairId;
    }

    public boolean isFaceUp() {
        return this.faceUp;
    }

    public boolean isMatched() {
        return this.matched;
    }

    public void flipUp() {
        LOGGER.debug("Flipping pair {} face up", pairId);
        this.faceUp = true;
    }

    public void flipDown() {
        if (!matched) {
            LOGGER.debug("Flipping pair {} face down", pairId);
            this.faceUp = false;
        }

    }

    public void markMatched() {
        LOGGER.debug("Marking pair {} card as matched", pairId);
        matched = true;
        faceUp = true;
    }

    public boolean matches(Card other) {
        boolean result = other != null && this.pairId == other.pairId;
        LOGGER.debug("Comparing pair {} with {}; matched={}",
                pairId,
                other == null ? "null" : other.pairId,
                result);
        return result;
    }
}
