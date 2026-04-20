package com.zhanru.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Card - Unit Tests")
public class CardTest {

    @Test
    @DisplayName("constructor: should initialise a card face down and unmatched")
    void startsFaceDownAndUnmatched() {
        Card card = new Card(1);

        assertFalse(card.isFaceUp());
        assertFalse(card.isMatched());
    }

    @Test
    @DisplayName("flipUp(): should show the card")
    void flipsFaceUp() {
        Card card = new Card(1);

        card.flipUp();
        assertTrue(card.isFaceUp());
    }

    @Test
    @DisplayName("markMatched(): should keep the card face up")
    void matchedCardStaysFaceUp() {
        Card card = new Card(1);

        card.markMatched();
        card.flipDown();
        assertTrue(card.isMatched());
        assertTrue(card.isFaceUp());
    }

    @Test
    @DisplayName("matches(): should compare cards by pair id")
    void comparesPairIds() {
        assertTrue(new Card(1).matches(new Card(1)));
        assertFalse(new Card(2).matches(new Card(1)));
    }
}
