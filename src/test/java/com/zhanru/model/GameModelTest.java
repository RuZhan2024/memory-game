package com.zhanru.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Game Model - Unit Tests")
public class GameModelTest {

    @Test
    @DisplayName("selectCard(): should flip the first selected card face up")
    void flipsFirstSelection() {
        Board board = new Board(6);
        GameModel model = new GameModel(board);
        Card card = model.getCards().getFirst();

        model.selectCard(card);
        assertTrue(card.isFaceUp());
    }

    @Test
    @DisplayName("selectCard(): should keep mismatched cards visible until they are hidden")
    void keepsMismatchedVisible() {
        Card firstCard = new Card(1);
        Card secondCard = new Card(2);

        GameModel model = new GameModel(new Board(6));
        model.selectCard(firstCard);
        model.selectCard(secondCard);

        assertTrue(model.hasPendingMismatch());
        assertTrue(firstCard.isFaceUp());
        assertTrue(secondCard.isFaceUp());

        model.hidePendingMismatch();

        assertFalse(firstCard.isFaceUp());
        assertFalse(secondCard.isFaceUp());
    }

    @Test
    @DisplayName("selectCard(): should finish a one-pair game after a match")
    void finishesOnePairGame() {
        Board board = new Board(1);
        GameModel model = new GameModel(board);
        Card firstCard = board.getCards().get(0);
        Card secondCard = board.getCards().get(1);

        model.selectCard(firstCard);
        model.selectCard(secondCard);

        assertTrue(firstCard.isMatched());
        assertTrue(secondCard.isMatched());

        assertTrue(model.isGameOver());
    }
}
