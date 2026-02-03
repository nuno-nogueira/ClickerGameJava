package com.clickergame.ui;

import java.util.HashMap;
import java.util.Map;

import com.clickergame.core.GameState;
import com.clickergame.systems.Building;
import com.clickergame.systems.Upgrade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class MainController {
    @FXML private StackPane contentPane;
    @FXML private VBox cookiePanel;
    @FXML private VBox buildingPanel;
    @FXML private VBox upgradePanel;

    @FXML  private Button cookieTabButton;
    @FXML private Button buildingTabButton;
    @FXML private Button upgradeTabButton;

    @FXML private Button clickButton;
    @FXML private Button cursorButton;
    @FXML private Button grandmaButton;
    @FXML private Button farmButton;
    @FXML private Button mineButton;
    @FXML private Button wizardButton;
    @FXML private Button bankButton;
    @FXML private Button templeButton;

    @FXML private Label cursorLabel;
    @FXML private Label grandmaLabel;
    @FXML private Label farmLabel;
    @FXML private Label mineLabel;
    @FXML private Label wizardLabel;
    @FXML private Label bankLabel;
    @FXML private Label templeLabel;

    @FXML private Label cursorQuantity;
    @FXML private Label grandmaQuantity;
    @FXML private Label farmQuantity;
    @FXML private Label mineQuantity;
    @FXML private Label wizardQuantity;
    @FXML private Label bankQuantity;
    @FXML private Label templeQuantity;

    @FXML private TilePane cursorPane1;

    @FXML private Button clickButton1;
    @FXML private Label clickLabel1;
    @FXML private Label clickDesc1;
    @FXML private Label clickPrice1;
    @FXML private TilePane clickPane1;

    @FXML private Label coinsLabel;
    @FXML private Label coinsPerSecond;

    @FXML private Button upgradeButton;

    //private Label clicksLabel;
    private Timeline gameLoop;
    private GameState gamestate;

    // Map to associate buttons with building IDs
    private Map<Button, String> buildingButtonMap = new HashMap<>();
    private Map<String, Label> buildingLabelMap = new HashMap<>();
    private Map<String, Label> buildingQuantityMap = new HashMap<>();
    
    // Map to associate buttons with upgrade IDs
    private Map<Button, String> upgradeButtonMap = new HashMap<>();
    private Map<String, Label> upgradeLabelMap = new HashMap<>();
    private Map<String, Label> upgradePriceMap = new HashMap<>();
    private Map<String, TilePane> upgradeTileMap = new HashMap<>();

    @FXML
    public void initialize() {
        gamestate = new GameState();
        showCookiePanel();
        implementBuildings();
        implementUpgrades();

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

    @FXML private void showUpgradePanel() {
        showPanel(upgradePanel);
    }

    @FXML void onCookieTabClicked() {
        showCookiePanel();
    }

    @FXML private void onBuildingTabClicked() {
        showBuildingPanel();
    }

    @FXML private void onUpgradeTabClicked() {
        showUpgradePanel();
    } 

    @FXML private void onCookie() {
        gamestate.cookieClick();
        coinsLabel.setText(gamestate.GetCoins() + " cookies");
        refreshAllButtons();
    }

    @FXML private void buyBuilding(String id, Button button) {
        gamestate.buyUpgrade(id);

        updateButtons(button, "building", id);
        Label updateLabel = buildingLabelMap.get(id);
        if (gamestate.getBuilding(id).GetQuantity() == 1) {
            updateLabel.setText(gamestate.getBuilding(id).GetQuantity() + " " + id);
        } else {
            updateLabel.setText(gamestate.getBuilding(id).GetQuantity() + " " + id + "s");
        }  
        
        Label updatePrice = buildingQuantityMap.get(id);
        updatePrice.setText("" + gamestate.getBuilding(id).GetPrice());

        coinsLabel.setText(gamestate.GetCoins() + " cookies");
        System.out.println(id + " building officially bought!");
    }

    private void updateButtons(Button button, String buttonType, String id) {
        //gamestate.getUpgrade(entry.getValue())
        if (buttonType == "building") {
            Building building = gamestate.getBuilding(id);
            
            if (gamestate.GetCoins() >= building.GetPrice()) {
                button.setDisable(false);
                //button.setText(building.GetName() + " (" + building.GetPrice() + ")");
                button.setStyle("");
            } else {
                button.setDisable(true);
                button.setStyle("-fx-text-fill: red;");
            }
        } else if(buttonType == "upgrade") {
            Upgrade upgrade = gamestate.getUpgrade(id);

            if (gamestate.GetCoins() >= upgrade.GetPrice() && !upgrade.isPurchased()) {
                button.setDisable(false);
                //button.setText(upgrade.getName() + " (" + upgrade.GetPrice() + ")");
                button.setStyle("");
            } else {
                button.setDisable(true);
                button.setStyle("-fx-text-fill: red;");
            }
        }       
    }

    @FXML private void buyUpgrade(String id, Button button) {
        gamestate.applyUpgrade(id);
        updateButtons(button, "upgrade", id);
        upgradeTileMap.get(id).setVisible(false);

        coinsLabel.setText(gamestate.GetCoins() + " cookies");
        System.out.println(id + " upgrade officially bought!");
    }

    private void refreshAllButtons() {
        for (Map.Entry<Button, String> entry : buildingButtonMap.entrySet()) { //gamestate.getUpgrade(entry.getValue())
            updateButtons(entry.getKey(), "building", entry.getValue());
        }
        for (Map.Entry<Button, String> entry : upgradeButtonMap.entrySet()) { 
            updateButtons(entry.getKey(), "upgrade", entry.getValue());
        }
        
    }

    private void implementBuildings() {
        // Maps all buildings to its buttons & Labels
        buildingButtonMap.put(cursorButton, "cursor");
        buildingButtonMap.put(grandmaButton, "grandma");
        buildingButtonMap.put(farmButton, "farm");
        buildingButtonMap.put(mineButton, "mine");
        buildingButtonMap.put(wizardButton, "wizard");
        buildingButtonMap.put(bankButton, "bank");
        buildingButtonMap.put(templeButton, "temple");

        buildingLabelMap.put("cursor", cursorLabel);
        buildingLabelMap.put("grandma", grandmaLabel);
        buildingLabelMap.put("farm", farmLabel);
        buildingLabelMap.put("mine", mineLabel);
        buildingLabelMap.put("wizard", wizardLabel);
        buildingLabelMap.put("bank", bankLabel);
        buildingLabelMap.put("temple", templeLabel);

        buildingQuantityMap.put("cursor", cursorQuantity);
        buildingQuantityMap.put("grandma", grandmaQuantity);
        buildingQuantityMap.put("farm", farmQuantity);
        buildingQuantityMap.put("mine", mineQuantity);
        buildingQuantityMap.put("wizard", wizardQuantity);
        buildingQuantityMap.put("bank", bankQuantity);
        buildingQuantityMap.put("temple", templeQuantity);

        // Assign lambda handlers to all upgrade buttons
        for (Map.Entry<Button, String> entry : buildingButtonMap.entrySet()) {
            Button button = entry.getKey();
            String buildingId = entry.getValue();

            button.setOnAction(e -> buyBuilding(buildingId, button));
            updateButtons(button, "building", buildingId);
        }

        // Assign lambda handlers to all upgrade labels
        for (Map.Entry<String, Label> entry : buildingLabelMap.entrySet()) {
            Label label = entry.getValue();
            String buildingId = entry.getKey();
            Building building = gamestate.getBuilding(buildingId);

            label.setText(building.GetQuantity() + " " + buildingId + "s");
        }

        // Assign lambda handlers to all upgrade labels
        for (Map.Entry<String, Label> entry : buildingQuantityMap.entrySet()) {
            Label label = entry.getValue();
            String buildingId = entry.getKey();
            Building building = gamestate.getBuilding(buildingId);
            label.setText("" + building.GetPrice());
        }
    }

    private void implementUpgrades() {
        upgradeButtonMap.put(clickButton1, "click1");
        upgradeLabelMap.put("click1", clickLabel1);
        upgradeLabelMap.put("click1", clickDesc1);
        upgradePriceMap.put("click1", clickPrice1);
        upgradeTileMap.put("click1", clickPane1);

        for (Map.Entry<Button, String> entry : upgradeButtonMap.entrySet()) {
            Button button = entry.getKey();
            String upgradeId = entry.getValue();
            Label upgradePrice = upgradePriceMap.get(upgradeId);

            // Add the correct price tag 
            upgradePrice.setText(gamestate.getUpgrade(upgradeId).GetPrice() + "");
            
            button.setOnAction(e -> buyUpgrade(upgradeId, button));
            updateButtons(button, "upgrade", upgradeId);
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

            refreshAllButtons();
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

