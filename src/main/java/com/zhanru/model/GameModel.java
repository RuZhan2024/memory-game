package com.zhanru.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GameModel {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Board board;
    private Card firstSelection;
    private Card pendingMismatchA;
    private Card pendingMismatchB;
    private boolean gameOver;

    public GameModel(Board board) {
        this.board = board;

    }

    public void selectCard(Card card) {

        if (gameOver || hasPendingMismatch() || card.isMatched() || card.isFaceUp()) {
            return;
        }
        card.flipUp();

        if (firstSelection == null) {
            firstSelection = card;
            return;
        }

        if (firstSelection.matches(card)) {
            firstSelection.markMatched();
            card.markMatched();
        } else {
            pendingMismatchA = firstSelection;
            pendingMismatchB = card;
        }

        firstSelection = null;
        gameOver = board.allCardsMatched();
    }

    public boolean hasPendingMismatch() {
        return this.pendingMismatchA != null && this.pendingMismatchB != null;
    }

    public void hidePendingMismatch() {
        if (!hasPendingMismatch())
            return;

        pendingMismatchA.flipDown();
        pendingMismatchB.flipDown();
        pendingMismatchA = null;
        pendingMismatchB = null;
    }

    public List<Card> getCards() {
        return board.getCards();
    }

    public boolean isGameOver() {
        LOGGER.debug("Is Game Over: {}", gameOver);
        return gameOver;
    }
}
