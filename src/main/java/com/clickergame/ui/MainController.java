package com.clickergame.ui;

import java.io.IOException;
import javafx.scene.Node;
import com.clickergame.core.GameState;
import com.clickergame.persistence.SaveManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    @FXML private ScrollPane upgradePanel;
    @FXML private VBox statsPanel;

    @FXML  private Button cookieTabButton;
    @FXML private Button buildingTabButton;
    @FXML private Button upgradeTabButton;
    @FXML private Button statsTabButton;

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

    @FXML private Button upgradeButton;

    private Timeline gameLoop;
    private GameState gamestate;
    private SaveManager saveData;
    private Object obj;
    @FXML private UpgradesController upgradesController;
    @FXML private BuildingsController buildingsController;
    @FXML private StatsController statsController;

    @FXML
    public void initialize() {
        gamestate = new GameState();
        saveData = new SaveManager();
        saveData.save("test");
        obj = saveData.load();
        System.out.println(obj + "2");
        showCookiePanel();
        //implementBuildings();

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

            Node upgradesRoot = upgradesLoader.load();
            Node buildingsRoot = buildingsLoader.load();
            Node statsRoot = statsLoader.load();

            // Get the respective controller
            upgradesController = upgradesLoader.getController();
            buildingsController = buildingsLoader.getController();
            statsController = statsLoader.getController();

            // Initializing each controller
            upgradesController.initialize(gamestate);

            statsController.setMainController(this);
            statsController.setup(gamestate);

            buildingsController.setMainController(this);
            buildingsController.setup(gamestate);
            
            // Getting the content of each controller
            statsPanel.getChildren().clear();
            statsPanel.getChildren().add(statsRoot);

            upgradePanel.setContent(upgradesRoot);

            buildingPanel.getChildren().clear();
            buildingPanel.getChildren().add(buildingsRoot);
        } catch (IOException e) {
            e.printStackTrace();
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

    /*********** */

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

    @FXML private void onCookie() {
        gamestate.cookieClick();
        coinsLabel.setText((int)gamestate.GetCoins() + " cookies");
        buildingsController.refreshAllButtons();
        upgradesController.refreshAllButtons();
    }

    private void startCursorLoop() {
    /*
    * Triggers a loop every seconds where it sums the amount of cursors to the coins
    */

    gameLoop = new Timeline(
        new KeyFrame(Duration.seconds(1), e -> {
            double income = gamestate.updatePassiveIncome();
            coinsLabel.setText((int)gamestate.GetCoins() + " cookies");
            coinsPerSecond.setText(income + " /s");
            gamestate.goldenCookieChance();
            buildingsController.refreshAllButtons();
            test.setText(obj + "");

            // Refresh global stats to display the info
            statsController.refreshStats();
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

