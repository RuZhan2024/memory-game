package com.zhanru.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GameModel {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Board board;

    public GameModel(Board board) {
        this.board = board;
       
    }

    public void selectCard(Card card) {
        LOGGER.debug("Selecting card from pair {}", card.getPairId());

        card.flipUp();
    }

     public List<Card> getCards() {
            return board.getCards();
        }
}
