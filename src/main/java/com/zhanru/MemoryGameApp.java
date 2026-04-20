package com.zhanru;

import com.zhanru.controller.GamePlayController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */

public class MemoryGameApp extends Application {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void start(Stage stage) {
        LOGGER.debug("Start Memory Game");
        GamePlayController controller = new GamePlayController();
        stage.setTitle("Memory Game");
        stage.setScene(new Scene(controller.getView(), 640, 520));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
