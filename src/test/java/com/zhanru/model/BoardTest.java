package com.zhanru.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Board - Unit Tests")
public class BoardTest {

    @Test
    @DisplayName("constructor: should create two cards for each pair")
    void createsTwoCardsPerPair() {
        Board board = new Board(6);

        assertEquals(12, board.getCards().size());
    }

    @Test
    @DisplayName("allCardsMatched(): should only return true after every card is matched")
    void detectsWhenAllCardsAreMatched() {
        Board board = new Board(2);
        assertFalse(board.allCardsMatched());
        board.getCards().forEach(Card::markMatched);
        assertTrue(board.allCardsMatched());
    }
}
