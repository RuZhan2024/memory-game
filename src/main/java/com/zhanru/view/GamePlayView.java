package com.zhanru.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import com.zhanru.model.Card;
 import javafx.scene.control.Button;

public class GamePlayView {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private final GridPane board = new GridPane();
    private final Map<Card, Button> buttons = new HashMap<>();

    public GamePlayView(List<Card> cards) {
        LOGGER.debug("Creating gameplay view for {} cards", cards.size());

        int columns = 4;

        for (int index = 0; index < cards.size(); index++) {
                Card card = cards.get(index);
                Button button = new Button("?");
                button.setMinSize(640/4, 520 /(cards.size()/4));
                button.setStyle("-fx-font-size:36px;");
                buttons.put(card, button);
                board.add(button, index % columns, index / columns);
        }
    }

    public Parent getRoot() {
        return board;
    }

    public void setCardClickHandler(Consumer<Card> handler) {
        LOGGER.debug("Registering card click handlers");
        buttons.forEach((card, button) -> {
            button.setOnAction(_ -> handler.accept(card));
        });
    }

    public void refresh() {
        LOGGER.debug("Refreshing board view");
        buttons.forEach((card, button) -> {
            String text = card.isFaceUp() ? String.valueOf(card.getPairId()) : "?";
            button.setText(text);
        });
    }
}
