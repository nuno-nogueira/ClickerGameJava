package com.clickergame.ui;

import java.util.HashMap;
import java.util.Map;

import com.clickergame.core.GameState;
import com.clickergame.core.Building;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class MainController {
    @FXML private StackPane contentPane;
    @FXML private VBox cookiePanel;
    @FXML private VBox buildingPanel;

    @FXML  private Button cookieTabButton;
    @FXML private Button buildingTabButton;

    @FXML private Button clickButton;
    @FXML private Button cursorButton;
    @FXML private Button grandmaButton;
    @FXML private Button farmButton;
    @FXML private Button mineButton;
    @FXML private Button factoryButton;
    @FXML private Button bankButton;
    @FXML private Button templeButton;

    @FXML private Label cursorLabel;
    @FXML private Label grandmaLabel;
    @FXML private Label farmLabel;
    @FXML private Label mineLabel;
    @FXML private Label factoryLabel;
    @FXML private Label bankLabel;
    @FXML private Label templeLabel;

    @FXML private Label coinsLabel;
    @FXML private Label coinsPerSecond;

    @FXML private Button upgradeButton;

    //private Label clicksLabel;
    private Timeline gameLoop;
    private GameState gamestate;

    // Map to associate buttons with upgrade IDs
    private Map<Button, String> upgradeButtonsMap = new HashMap<>();
    private Map<String, Label> upgradeLabelsMap = new HashMap<>();

    @FXML
    public void initialize() {
        gamestate = new GameState();
        showCookiePanel();

        // Maps all buildings to its buttons & Labels
        upgradeButtonsMap.put(cursorButton, "cursor");
        upgradeButtonsMap.put(grandmaButton, "grandma");
        upgradeButtonsMap.put(farmButton, "farm");
        upgradeButtonsMap.put(mineButton, "mine");
        upgradeButtonsMap.put(factoryButton, "factory");
        upgradeButtonsMap.put(bankButton, "bank");
        upgradeButtonsMap.put(templeButton, "temple");

        upgradeLabelsMap.put("cursor", cursorLabel);
        upgradeLabelsMap.put("grandma", grandmaLabel);
        upgradeLabelsMap.put("farm", farmLabel);
        upgradeLabelsMap.put("mine", mineLabel);
        upgradeLabelsMap.put("factory", factoryLabel);
        upgradeLabelsMap.put("bank", bankLabel);
        upgradeLabelsMap.put("temple", templeLabel);


        // Assign lambda handlers to all upgrade buttons
        for (Map.Entry<Button, String> entry : upgradeButtonsMap.entrySet()) {
            Button button = entry.getKey();
            String upgradeId = entry.getValue();

            button.setOnAction(e -> buyUpgrade(upgradeId, button));
            // Inicialmente atualizar o botão
            updateUpgradeButton(button, gamestate.getUpgrade(upgradeId));
        }

        // Assign lambda handlers to all upgrade labels
        for (Map.Entry<String, Label> entry : upgradeLabelsMap.entrySet()) {
            Label label = entry.getValue();
            String upgradeId = entry.getKey();
            Building upgrade = gamestate.getUpgrade(upgradeId);

            label.setText(upgrade.GetQuantity() + " " + upgradeId + "s");
        }

        clickButton.setText("Cookie Button!");
        coinsLabel.setText("" + gamestate.GetCoins());    
        coinsPerSecond.setText(gamestate.updatePassiveIncome() + " /s");

        startCursorLoop();
    }

    @FXML private void showPanel(VBox panelToShow) {
        for (var node : contentPane.getChildren()) {
            node.setVisible(false);
            node.setManaged(false);
        }
        panelToShow.setVisible(true);
        panelToShow.setManaged(true);
    }

    @FXML private void showCookiePanel() {
        showPanel(cookiePanel);
    }

    @FXML private void showBuildingPanel() {
        showPanel(buildingPanel);
    }

    @FXML void onCookieTabClicked() {
        showCookiePanel();
    }

    @ FXML private void onBuildingTabClicked() {
        showBuildingPanel();
    }

    @FXML private void onCookie() {
        gamestate.cookieClick();
        coinsLabel.setText(gamestate.GetCoins() + " cookies");
        refreshAllUpgradeButtons();
    }

    @FXML private void buyUpgrade(String id, Button button) {
        gamestate.buyUpgrade(id);
        updateUpgradeButton(button, gamestate.getUpgrade(id));
        
        Label updateLabel = upgradeLabelsMap.get(id);
        if (gamestate.getUpgrade(id).GetQuantity() == 1) {
            updateLabel.setText(gamestate.getUpgrade(id).GetQuantity() + " " + id);
        } else {
            updateLabel.setText(gamestate.getUpgrade(id).GetQuantity() + " " + id + "s");
        }  
        
        coinsLabel.setText("" + gamestate.GetCoins());
        System.out.println(id + " upgrade officially bought!");
    }

    private void updateUpgradeButton(Button button, Building upgrade) {
        boolean canBuy = gamestate.GetCoins() >= upgrade.GetPrice();

        button.setDisable(!canBuy);
        button.setText(upgrade.GetName() + " (" + upgrade.GetPrice() + ")");
    
        if (canBuy) {
            button.setStyle("");
        } else {
            button.setStyle("-fx-text-fill: red;");
        }
    }

    private void refreshAllUpgradeButtons() {
        for (Map.Entry<Button, String> entry : upgradeButtonsMap.entrySet()) {
            updateUpgradeButton(entry.getKey(), gamestate.getUpgrade(entry.getValue()));
        }
    }

    private void startCursorLoop() {
    /*
    * Triggers a loop every seconds where it sums the amount of cursors to the coins
    */

    gameLoop = new Timeline(
        new KeyFrame(Duration.seconds(1), e -> {
            int income = gamestate.updatePassiveIncome();
            coinsLabel.setText(gamestate.GetCoins() + " cookies");
            coinsPerSecond.setText(income + " /s");

            refreshAllUpgradeButtons();
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

