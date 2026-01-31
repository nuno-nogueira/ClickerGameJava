package com.clickergame.ui;

import com.clickergame.core.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class MainController {
    @FXML
    private Button clickButton;

    @FXML
    private Button upgradeButton;

    @FXML
    private Button cursorButton;

    @FXML
    private Label cursorLabel;

    @FXML
    private Label coinsLabel;

    //private Label clicksLabel;
    private Timeline gameLoop;
    private GameState gamestate;

    @FXML
    public void initialize() {
        gamestate = new GameState();

        clickButton.setText("Cookie Button!");
        upgradeButton.setText(gamestate.GetUpgrades() + " coins");
        cursorButton.setText(gamestate.GetCursorUpgrades() + " coins");

        coinsLabel.setText("" + gamestate.GetCoins());
        cursorLabel.setText(gamestate.GetCursors() + " cursors");
    
        startCursorLoop();
    }

    @FXML
    private void onClickButton() {
        gamestate.cookieClick();
        coinsLabel.setText("" + gamestate.GetCoins());
    }

    @FXML
    private void onUpgradeButton() {
        gamestate.upgradeClicks();
        
        upgradeButton.setText(gamestate.GetUpgrades() + " coins");
        coinsLabel.setText("" + gamestate.GetCoins());
        System.out.println("Upgrade officially bought!");
    }

    @FXML
    private void onCursorUpgradeButton() {
        gamestate.upgradeCursors();
        cursorButton.setText(gamestate.GetCursorUpgrades() + " coins");

        cursorLabel.setText(gamestate.GetCursors() + " cursors");
        coinsLabel.setText("" + gamestate.GetCoins());
        System.out.println("Upgrade officially bought!");
    }

    private void startCursorLoop() {
    /*
    * Triggers a loop every seconds where it sums the amount of cursors to the coins
    */

    gameLoop = new Timeline(
        new KeyFrame(Duration.seconds(1), e -> {
            gamestate.cursorClick();
            coinsLabel.setText("" + gamestate.GetCoins());
        })
    );

    gameLoop.setCycleCount(Timeline.INDEFINITE); // updates UI
    gameLoop.play();
    }

    public void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }
}
