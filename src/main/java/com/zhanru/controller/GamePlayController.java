package com.zhanru.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zhanru.model.Board;
import com.zhanru.model.GameModel;
import com.zhanru.view.GamePlayView;

import javafx.scene.Parent;

public class GamePlayController {
    private static final Logger LOGGER = LogManager.getLogger();

    private final GameModel model;
    private final GamePlayView view;
    
    public GamePlayController() {
        LOGGER.debug("Creating gameplay controller");
        model = new GameModel(new Board(6));
        view = new GamePlayView(model.getCards());

        view.setCardClickHandler(card -> {
            LOGGER.debug("Card {} clicked", card.getPairId());
            model.selectCard(card);
            view.refresh();
        });
    }

    public Parent getView() {
        return view.getRoot();
    }
}
