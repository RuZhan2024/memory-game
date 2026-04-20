package com.zhanru.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zhanru.model.Board;
import com.zhanru.model.GameModel;
import com.zhanru.view.GamePlayView;

import javafx.animation.PauseTransition;
import javafx.scene.Parent;
import javafx.util.Duration;

public class GamePlayController {
    private static final Logger LOGGER = LogManager.getLogger();

    private final GameModel model;
    private final GamePlayView view;
    
    public GamePlayController() {
        model = new GameModel(new Board(6));
        view = new GamePlayView(model.getCards());

        view.setCardClickHandler(card -> {
            model.selectCard(card);
            view.refresh();

            if (model.hasPendingMismatch()) {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(_ -> {
                    model.hidePendingMismatch();
                    view.refresh();
                });
                pause.play();
            }
        });
        
        view.refresh();
    }

    public Parent getView() {
        return view.getRoot();
    }
}
