package com.zhanru.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import com.zhanru.model.Card;
import javafx.scene.control.Button;

public class GamePlayView {
    private static final Logger LOGGER = LogManager.getLogger();

    private final GridPane board = new GridPane();
    private final Map<Card, Button> buttons = new HashMap<>();

    private final Label status = new Label();
    private final Button restartButton = new Button("Restart");
    private final VBox root = new VBox(10, status, board, restartButton); 

    public GamePlayView(List<Card> cards) {
        int columns = 4;

        for (int index = 0; index < cards.size(); index++) {
            Card card = cards.get(index);
            Button button = new Button("?");
            button.setMinSize(640 / 4, 520 / (cards.size() / 4));
            button.setStyle("-fx-font-size:36px;");
            buttons.put(card, button);
            board.add(button, index % columns, index / columns);
        }
    }

    public Parent getRoot() {
        return root;
    }

    public void setCardClickHandler(Consumer<Card> handler) {
        buttons.forEach((card, button) -> {
            button.setOnAction(_ -> handler.accept(card));
        });
    }

    public void updateStatus(int score, int moves, int matchedPairs, int totalPairs, boolean gameOver) {
        String text = "Score: " + score + " | Moves: " + moves + " | Pairs: " + matchedPairs + " / " + totalPairs;
        status.setText(gameOver ? text + " | You won!" : text);
    }

    public void setRestartHandler(Runnable handler) {
        restartButton.setOnAction(_ -> handler.run());
    }

    public void refresh() {
        buttons.forEach((card, button) -> {
            String text = card.isFaceUp() ? String.valueOf(card.getPairId()) : "?";
            button.setText(text);
        });
    }
}
