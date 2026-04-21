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

    private final ScoringStrategy scoring = new MoveBasedScoring(2, 1);
    private int moves;

    public GameModel(Board board) {
        this.board = board;

    }

    public void selectCard(Card card) {

        if (this.gameOver || hasPendingMismatch() || card.isMatched() || card.isFaceUp()) {
            return;
        }
        card.flipUp();

        if (this.firstSelection == null) {
            this.firstSelection = card;
            return;
        }

        boolean matched = this.firstSelection.matches(card);
        this.moves ++;
        scoring.updateScore(matched);
        if (matched) {
            this.firstSelection.markMatched();
            card.markMatched();
        } else {
            this.pendingMismatchA = this.firstSelection;
            this.pendingMismatchB = card;
        }

        this.firstSelection = null;
        this.gameOver = board.allCardsMatched();
    }

    public boolean hasPendingMismatch() {
        return this.pendingMismatchA != null && this.pendingMismatchB != null;
    }

    public void hidePendingMismatch() {
        if (!hasPendingMismatch())
            return;

        this.pendingMismatchA.flipDown();
        this.pendingMismatchB.flipDown();
        this.pendingMismatchA = null;
        this.pendingMismatchB = null;
    }

    public List<Card> getCards() {
        return board.getCards();
    }

    public int getScore() {
        return scoring.getScore();
    }

    public int getMoves() {
        return this.moves;
    }

    public int getMatchedPairs() {
        return (int)board.getCards().stream().filter(Card::isMatched).count() / 2;
    }

    public int getTotalPairs() {
        return board.getCards().size() / 2;
    }

    public boolean isGameOver() {
        LOGGER.debug("Is Game Over: {}", this.gameOver);
        return this.gameOver;
    }
}
