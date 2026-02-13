package com.clickergame.ui;

import java.io.IOException;

import javafx.scene.Node;
import com.clickergame.core.GameState;
import com.clickergame.persistence.SaveManager;
import com.clickergame.persistence.SaveData;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;



public class MainController {
    @FXML private StackPane contentPane;
    @FXML private VBox cookiePanel;
    @FXML private VBox buildingPanel;
    @FXML private ScrollPane upgradePanel;
    @FXML private VBox statsPanel;
    @FXML private VBox prestigePanel;

    @FXML private Button cookieTabButton;
    @FXML private Button buildingTabButton;
    @FXML private Button upgradeTabButton;
    @FXML private Button statsTabButton;
    @FXML private Button prestigeTabButton;

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

    @FXML private Label clickDesc1;
    @FXML private Label clickPrice1;
    @FXML private TilePane clickPane1;

    @FXML private Button cursorButton1;
    @FXML private Label cursorName1;
    @FXML private Label cursorDesc1;
    @FXML private Label cursorPrice1;
    @FXML private TilePane cursorPane1;
    @FXML private Label test;

    @FXML public Label coinsLabel;
    @FXML private Label coinsPerSecond;

    @FXML private Label overloadProgressLabel;
    @FXML private ProgressBar overloadBar;
    @FXML private Label overloadTimer;

    @FXML private Button upgradeButton;

    private Timeline gameLoop;
    private Timeline overloadLoop;
    private GameState gamestate;
    private SaveData saveData;
    private SaveManager saveManager;
    @FXML private UpgradesController upgradesController;
    @FXML private BuildingsController buildingsController;
    @FXML private StatsController statsController;
    @FXML private PrestigeController prestigeController;

    /** MAIN FUNCTIONS */
    @FXML
    public void initialize() {
        saveManager = new SaveManager();
        saveData = new SaveData();

        // Load data 
        SaveData loadedSave = (SaveData)saveManager.load();
        if (loadedSave != null) {
            gamestate = new GameState();
            gamestate.loadData(loadedSave);
        } else {
            gamestate = new GameState();
        }
        
        showCookiePanel();

        // Load other controllers
        try {
            FXMLLoader upgradesLoader = new FXMLLoader(
                getClass().getResource("/fxml/UpgradesView.fxml")
            );

            FXMLLoader buildingsLoader = new FXMLLoader(
                getClass().getResource("/fxml/BuildingsView.fxml")
            );

            FXMLLoader statsLoader = new FXMLLoader(
                getClass().getResource("/fxml/StatsView.fxml")
            );

            FXMLLoader prestigeLoader = new FXMLLoader(
                getClass().getResource("/fxml/PrestigeView.fxml")
            );

            Node upgradesRoot = upgradesLoader.load();
            Node buildingsRoot = buildingsLoader.load();
            Node statsRoot = statsLoader.load();
            Node prestigeRoot = prestigeLoader.load();

            // Get the respective controller
            upgradesController = upgradesLoader.getController();
            buildingsController = buildingsLoader.getController();
            statsController = statsLoader.getController();
            prestigeController = prestigeLoader.getController();

            // Initializing each controller
            upgradesController.initialize(gamestate);

            buildingsController.setMainController(this);
            buildingsController.setup(gamestate);

            statsController.setMainController(this);
            statsController.setup(gamestate, buildingsController, upgradesController);

            prestigeController.setMainController(this);
            prestigeController.setup(gamestate);
            
            // Getting the content of each controller
            statsPanel.getChildren().clear();
            statsPanel.getChildren().add(statsRoot);

            upgradePanel.setContent(upgradesRoot);

            buildingPanel.getChildren().clear();
            buildingPanel.getChildren().add(buildingsRoot);

            prestigePanel.getChildren().clear();
            prestigePanel.getChildren().add(prestigeRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load Main Controller elements
        clickButton.setText("Cookie Button!");
        coinsLabel.setText("" + gamestate.GetCoins());    
        coinsPerSecond.setText(gamestate.updatePassiveIncome() + " /s");
        overloadProgressLabel.setText(gamestate.clickOverloadCharge + "/" + gamestate.clickOverload);
        overloadBar.setProgress(Math.min(1.0, (double) gamestate.clickOverloadCharge / gamestate.clickOverload));

        startCursorLoop();
    }

    @FXML private void onCookie() {
        /**
         * This function is triggered every time the cookie button is clicked
         * It adds x amount of coins depending on the amount of cookies per click
         * It also charges the overload bar for each click
         */
        gamestate.cookieClick();

        // Charge overload
        gamestate.clickOverloadCharge++;
        overloadProgressLabel.setText(gamestate.clickOverloadCharge + "/" + gamestate.clickOverload);
        overloadBar.setProgress(Math.min(1.0, (double) gamestate.clickOverloadCharge / gamestate.clickOverload));

        coinsLabel.setText((int)gamestate.GetCoins() + " cookies");

        // Update buttons of other controllers (for example, to see if an user can now buy an upgrade/building)
        buildingsController.refreshAllButtons();
        upgradesController.refreshAllButtons();
    }

    private void startCursorLoop() {
    /*
    * Triggers timeline loops for the game features that require them
    */

    // Every 100ms -> updates the overload progress & overload bar
    overloadLoop = new Timeline(
        new KeyFrame(Duration.millis(100), e -> {

            // Verify if the click overload is going to be activated, if it is, calls the activeClickOverload() method
            if (gamestate.clickOverloadCharge >= gamestate.clickOverload) {
                gamestate.clickOverloadCharge = 0;
                overloadProgressLabel.setText(gamestate.clickOverloadCharge + "/" + gamestate.clickOverload);
                overloadBar.setProgress(Math.min(1.0, (double) gamestate.clickOverloadCharge / gamestate.clickOverload));
                gamestate.activeClickOverload();
            }

            // Check if the overlaod is active
            if (gamestate.overloadActive) {
                // If the timer is over, deactivate the overload
                if (System.currentTimeMillis() >= gamestate.overloadEndTime) gamestate.overloadActive = false;

                // While the overload is active, show the overload timer
                overloadTimer.setVisible(true);
                long timeLeft = gamestate.overloadEndTime - System.currentTimeMillis();
                overloadTimer.setText(String.format("OVERLOAD! (%.1fs left)", Math.max(0, timeLeft / 1000.0)));
            } else {
                overloadTimer.setVisible(false);
            } 

        })
    );

    // Every 1000ms -> update UI, and deposit coins based on the total passive income
    gameLoop = new Timeline(
        new KeyFrame(Duration.seconds(1), e -> {

            // Get the total amount of passive income, and update the amount of coins accordingly
            double income = gamestate.updatePassiveIncome();
            coinsLabel.setText((int)gamestate.GetCoins() + " cookies");
            coinsPerSecond.setText(income + " /s");

            // Tries to get the user a golden cookie! :O
            gamestate.goldenCookieChance();
            
            // Update UI
            buildingsController.refreshAllButtons();
            upgradesController.refreshAllButtons();

            // Refresh global stats
            saveManager.save(gamestate.toSaveData());
            prestigeController.loadUI();
            statsController.refreshStats();

        })
    );

    // Update loop cycles
    gameLoop.setCycleCount(Animation.INDEFINITE);
    gameLoop.play();

    overloadLoop.setCycleCount(Animation.INDEFINITE);
    overloadLoop.play();
    }

    public GameState getGameState() {
        return gamestate;
    }

    /** PANEL FUNCTIONS */
    @FXML private void showPanel(VBox panelToShow) {
        for (var node : contentPane.getChildren()) {
            node.setVisible(false);
            node.setManaged(false);
        }
        panelToShow.setVisible(true);
        panelToShow.setManaged(true);
    }

    @FXML private void showScroll(ScrollPane panelToShow) {
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
        showScroll(upgradePanel);
    }

    @FXML private void showStatsPanel() {
        showPanel(statsPanel);
    }

    @FXML private void showPrestigePanel() {
        showPanel(prestigePanel);
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

    @FXML private void onStatsTabClicked() {
        showStatsPanel();
    } 

    @FXML private void onPrestigeTabClicked() {
        showPrestigePanel();
    }

    public void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }
}

