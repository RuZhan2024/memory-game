package com.zhanru.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private static final Logger LOGGER = LogManager.getLogger();
    private final List<Card> cards = new ArrayList<>();

    public Board(int numberOfPairs) {
        LOGGER.debug("Creating board with {} pairs", numberOfPairs);
        for (int pairId = 1; pairId <= numberOfPairs; pairId++) {
            cards.add(new Card(pairId));
            cards.add(new Card(pairId));
        }

        Collections.shuffle(cards);
        LOGGER.debug("Board created with {} cards", cards.size());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }

    public boolean allCardsMatched() {
        boolean result = cards.stream().allMatch(Card::isMatched);
        LOGGER.debug("Checking board completion; complete={}", result);
        return result;
    }
}
