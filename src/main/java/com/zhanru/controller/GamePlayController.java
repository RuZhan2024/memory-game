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
            refreshView();

            if (model.hasPendingMismatch()) {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(_ -> {
                    model.hidePendingMismatch();
                    refreshView();
                });
                pause.play();
            }
        });
        
        refreshView();
    }

    public Parent getView() {
        return view.getRoot();
    }

    private void refreshView() {
        view.refresh();
        view.updateStatus(
            model.getScore(),
            model.getMoves(),
            model.getMatchedPairs(),
            model.getTotalPairs(),
            model.isGameOver()
        );
    }
}
